package com.mokepon.mokepon.controllers;

import com.mokepon.mokepon.dtos.PlayerFigthDTO;
import com.mokepon.mokepon.models.*;
import com.mokepon.mokepon.services.implement.AttackPlayerServiceImplement;
import com.mokepon.mokepon.services.implement.BattleServiceImplement;
import com.mokepon.mokepon.services.implement.CookiePlayerServiceImplement;
import com.mokepon.mokepon.services.implement.PlayerServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server")
public class BattleController {
    @Autowired
    private BattleServiceImplement battleService;

    @Autowired
    private PlayerServiceImplement playerService;

   @Autowired
   private AttackPlayerServiceImplement attackPlayerService;

   @Autowired
   private CookiePlayerServiceImplement cookiePlayerService;

   /*
   * Devolver un objeto BattleDTO con toda la info necesaria para actualizar las pantallas
   * durante la batalla entre dos jugadores
   * */
    @GetMapping("/battle/{id}")
    public ResponseEntity<Object> getBattleDTO(@PathVariable long id){
        return new ResponseEntity<>(battleService.getBattleRoomDTOById(id),HttpStatus.ACCEPTED);
    }


    /*
    * Crear la sala para la batalla entre dos jugadores. el primero en entrar la crea, generando una conexion
    * unica entre ambos. Cuando un jugador entra revisa que no exista la misma conexion
    * */
    @Transactional
    @PostMapping("/player/{id}/addtobattle/{id_enemy}")
    public ResponseEntity<Object> createBattleRoom(@PathVariable long id, @PathVariable long id_enemy){
        //el jugador no puede ser el mismo
        if(id==id_enemy){
            return new ResponseEntity<>("You can't fight with you",HttpStatus.FORBIDDEN);
        }
        //revisar si existen los jugadores
        Player player1=playerService.getPlayerById(id);
        Player player2=playerService.getPlayerById(id_enemy);
        if(player1==null){
            return new ResponseEntity<>("player not found",HttpStatus.FORBIDDEN);
        }
        if(player2==null){
            return new ResponseEntity<>("enemy not found",HttpStatus.FORBIDDEN);
        }

        //revisa si existe una battleroom con esos dos id, en caso de no existir la crea
        if(playerService.checkBattleRoomBothPlayers(id,id_enemy)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        //limpiar battlerooms anteriores (si quedaron)
        if(player1.getBattle()!=null){
            battleService.destroyBattleRoom(player1.getBattle());
        }
        if(player2.getBattle()!=null){
            battleService.destroyBattleRoom(player2.getBattle());
        }
        //crear battleroom
        Battle battle= battleService.createBattleRoom();
        //y a ambos jugadores le añade esa battle
        battle.addFighter(player1);
        battle.addFighter(player2);
        //guardar los jugadores con la nueva informacion
        playerService.addPlayer(player1);
        playerService.addPlayer(player2);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    * borrar una sala de batalla y quitar a los jugadores de la conexion
    * */
    @Transactional
    @DeleteMapping("/battle/{id}")
    public ResponseEntity<Object> deleteBattleRoom(@PathVariable long id){
        if(!battleService.existsById(id)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        battleService.destroyBattleRoom(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /*
     * desconectar jugador. Si no quedan otos jugadores entonces borrar la sala
     * */
    @Transactional
    @PostMapping("/player/{idPlayer}/disconnectBattle")
    public ResponseEntity<Object> disconnectPlayerFromBattleRoom(@PathVariable long idPlayer){
        //revisar que exista el jugador
        if(!playerService.existsById(idPlayer))
        {
            return new ResponseEntity<>("Player not found",HttpStatus.FORBIDDEN);
        }
        Player player=playerService.getPlayerById(idPlayer);
        //debe estar conectado a una batalla
        if(player.getBattle()==null){
            return new ResponseEntity<>("Player must be in a battle room first",HttpStatus.FORBIDDEN);
        }
        Battle battle=player.getBattle();
        //revisar si hay otros jugadores
        int fighters=battle.getFighters().size();

        //si era el unico jugador borrar la sala
        if(fighters<=1){
            battleService.destroyBattleRoom(battle);
        }else{
            //quitar jugador de la batalla
            player.setBattle(null);
        }
        //guardar modificacion del player
        playerService.addPlayer(player);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /*
    * enviar un ataque del jugador a su sala de batalla. por lo general se da al tocar
    * uno de los botones de ataque
    * */

    @Transactional
    @PostMapping("/player/{idPlayer}/sendAttack/{attack}")
    public ResponseEntity<Object> sendAttack(@PathVariable long idPlayer, @PathVariable AttackElement attack){
        //el jugador debe existir
        if(!playerService.existsById(idPlayer)){
            return new ResponseEntity<>("Player not found",HttpStatus.FORBIDDEN);
        }
        Player player =playerService.getPlayerById(idPlayer);
        //revisar que el jugador este en una batalla valida con otro jugador
        if(player.getBattle()==null){
            return new ResponseEntity<>("You must to be in a battle room first",HttpStatus.FORBIDDEN);
        }
        //revisar si ya existia un ataque de ese jugador en la battleroom (estan relacionados por el AttackPlayer)
        boolean resuelve=false;
        if(battleService.wasPlayerAttacked(player)){
            attackPlayerService.deleteAttackPlayerById(player.getAttack().getId());
            //si ya habia atacado habia una bandera preexistente
            resuelve=player.getBattle().getAttacks().size()>=2;
        }
        else{
            //si el jugador no habia atacado y no estaba vacio entonces ya es la 2da bandera de ataque
            resuelve=!player.getBattle().getAttacks().isEmpty();
        }
        //crear un attackplayer y enlazarlo al jugador y a la battleroom
        AttackPlayer attackPlayer=new AttackPlayer(attack,1);
        player.setAttack(attackPlayer);
        player.getBattle().addAttacks(attackPlayer);
        //guardar attackplayer en bdd
        attackPlayerService.addAttackPlayer(attackPlayer);
        //devolver un booleano: false si no hay otros ataques, true si ya se puede resolver
        //resolver (si es posible)
        if(resuelve){
            Battle battleUpdated=battleService.initFightSimulation(player.getBattle());
            //actualizar los monstruos de los  jugadores en la bdd
            //actualizar players en la base de datos
            for(Player fighter:battleUpdated.getFighters()){
                cookiePlayerService.updateCookiePlayer(fighter.getMonster());
                //playerService.updatePlayer(fighter);
            }
            //resetear ataques
            //battleUpdated.resetAttacks();
            attackPlayerService.resetAttackList(battleUpdated);
        }
        return new ResponseEntity<>(resuelve,HttpStatus.ACCEPTED);

    }

    /*
    * conseguir la cantidad de banderas de ataque
    * */
    @GetMapping("/battle/{idBattle}/flags")
    public ResponseEntity<Object> getBattleFlags(@PathVariable long idBattle){
        //revisar si existe la battle room
        if(!battleService.existsById(idBattle)){
            return new ResponseEntity<>("Battle Room not found",HttpStatus.FORBIDDEN);
        }
        //conseguir las banderas de ataque
        return new ResponseEntity<>(battleService.getBattleRoomById(idBattle).getAttacks().size(),HttpStatus.ACCEPTED);
    }

    /*
     * conseguir el numero de ronda de ataques
     * */
    @GetMapping("/battle/{idBattle}/round")
    public ResponseEntity<Object> getBattleRound(@PathVariable long idBattle){
        //revisar si existe la battle room
        if(!battleService.existsById(idBattle)){
            return new ResponseEntity<>("Battle Room not found",HttpStatus.FORBIDDEN);
        }
        //conseguir el número de ronda, comienza en la 1 (sin ataques resueltos)
        return new ResponseEntity<>(battleService.getBattleRoomById(idBattle).getRoundNumber(),HttpStatus.ACCEPTED);
    }

    /*
    * Le da la respuesta de la batalla al 2do jugador y luego resetea los ataques
    * */

    /*@Transactional
    @PostMapping("/player/{id}/getFightSolution")
    public ResponseEntity<Object> getFightSolution(@PathVariable long id){
        //revisar que el jugador exista
        if(!playerService.existsById(id)){
            return new ResponseEntity<>("Player not found",HttpStatus.FORBIDDEN);
        }
        Player player =playerService.getPlayerById(id);
        //revisar que este en una batalla valida con otro jugador
        //revisar que exista la lista de resultados de la batalla y que no sea vacia
        //devolver el ultimo elemento agregado a la lista de ataques resueltos de la battleRoom
        int rounds=player.getBattle().getFightsResults().size();
        return new ResponseEntity<>(player.getBattle().getFightsResults().get(rounds-1),HttpStatus.ACCEPTED);
    }*/

    /*
    * se dispara por lo general cuando se dan las dos banderas de ataque
    * */

    /*@Transactional
    @PostMapping("/player/{id}/resolveAttack")
    public ResponseEntity<Object> resolveAttack(@PathVariable long id){
        //revisar que el jugador este en una batalla valida con otro jugador
        //obtener la battle del jugador
       //si aun no fue resuelto se resuelve y se envia la respuesta, se almacena para el siguiente jugador
        //si ya fue resuelto, entregar la respuesta almacenada y borrarla
        /*Battle battle=playerService.getPlayerById(id).getBattle();
        //resetear la lista de ataques
        battle.setFlags(0);
        battleService.updateBattleRoom(battle);
        //comparar ataques
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }*/

}
