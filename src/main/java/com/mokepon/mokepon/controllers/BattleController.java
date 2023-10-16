package com.mokepon.mokepon.controllers;

import com.mokepon.mokepon.services.implement.BattleServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class BattleController {
    @Autowired
    private BattleServiceImplement battleService;

    @GetMapping("/player/{id}/addtobattle/{id_enemy}")
    public ResponseEntity<Object> createBattleRoom(){
        //revisa si existe una battleroom con esos dos id, en caso de no existir la crea
        //y a ambos jugadores le añade esa battle
        //en caso de que exista es porque fue creada por el otro jugador
        //return new ResponseEntity<>(cookieService.getAllCookies(), HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
