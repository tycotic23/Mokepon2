package com.mokepon.mokepon.controllers;

import com.mokepon.mokepon.dtos.PlayerFigthDTO;
import com.mokepon.mokepon.models.Battle;
import com.mokepon.mokepon.models.Player;
import com.mokepon.mokepon.services.implement.BattleServiceImplement;
import com.mokepon.mokepon.services.implement.PlayerServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class BattleController {
    @Autowired
    private BattleServiceImplement battleService;

    @Autowired
    private PlayerServiceImplement playerService;

    @GetMapping("/player/{id}/addtobattle/{id_enemy}")
    public ResponseEntity<Object> createBattleRoom(@PathVariable long id, @PathVariable long id_enemy){
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
        //crear battleroom
        Battle battle= battleService.createBattleRoom();
        //y a ambos jugadores le añade esa battle
        //battleService.addPlayer(,);
        battle.addFighter(player1);
        battle.addFighter(player2);
        //guardar los jugadores con la nueva informacion
        playerService.addPlayer(player1);
        playerService.addPlayer(player2);
        //return new ResponseEntity<>(cookieService.getAllCookies(), HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
