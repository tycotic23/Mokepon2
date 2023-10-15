package com.mokepon.mokepon.controllers;

import com.mokepon.mokepon.models.Player;
import com.mokepon.mokepon.services.implement.PlayerServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server")
public class PlayerController {

    @Autowired
    PlayerServiceImplement playerService;

    @GetMapping("/player")
    public ResponseEntity<Object> getAllPlayers(){
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Object> getPlayerById(@PathVariable long id){
        return new ResponseEntity<>(playerService.getPlayerById(id),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<Object> DeletePlayerById(@PathVariable long id){
        playerService.deletePlayerById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/player")
    public ResponseEntity<Object> AddPlayer(@RequestBody Player player){
        playerService.addPlayer(player);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //modificar puntos del jugador segun su id (/player?points=34)

    //añadir el monstruo al jugador (POST/player/id/cookie)

    //quitar un monstruo al jugador (DELETE/player/id/cookie)

    //agregar o quitar vidas al monstruo del jugador (POST/player/id/cookie/losthealth/{h})
}
