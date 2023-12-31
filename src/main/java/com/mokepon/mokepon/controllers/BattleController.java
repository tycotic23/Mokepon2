package com.mokepon.mokepon.controllers;

import com.mokepon.mokepon.dtos.PlayerFigthDTO;
import com.mokepon.mokepon.models.AttackElement;
import com.mokepon.mokepon.models.AttackPlayer;
import com.mokepon.mokepon.models.Battle;
import com.mokepon.mokepon.models.Player;
import com.mokepon.mokepon.services.implement.AttackPlayerServiceImplement;
import com.mokepon.mokepon.services.implement.BattleServiceImplement;
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
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
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

    //de prueba
    @PostMapping("/player/{idPlayer}/testAttack/{attack}")
    public ResponseEntity<Object> testAttack(@PathVariable long idPlayer, @PathVariable AttackElement attack){
        Player player =playerService.getPlayerById(idPlayer);
        return new ResponseEntity<>(player.getBattle().getAttacks().size(),HttpStatus.ACCEPTED);

    }

    /*
    * se dispara por lo general cuando se dan las dos banderas de ataque
    * */

    @Transactional
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
        //comparar ataques*/
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
