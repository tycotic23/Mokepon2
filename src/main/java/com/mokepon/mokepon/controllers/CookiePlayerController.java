package com.mokepon.mokepon.controllers;

import com.mokepon.mokepon.dtos.CookieFigthDTO;
import com.mokepon.mokepon.services.implement.CookiePlayerServiceImplement;
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
public class CookiePlayerController {

    @Autowired
    private CookiePlayerServiceImplement cookiePlayerService;

    @Autowired
    private PlayerServiceImplement playerService;

    //conseguir monstro del jugador
    @GetMapping("/player/{name}/cookie")
    public ResponseEntity<Object> getCookiePlayer(@PathVariable String name){
        if(!cookiePlayerService.existsByPlayer_name(name)){
            return new ResponseEntity<>("You haven't a Cookie Monster yet. You need first select one.", HttpStatus.FORBIDDEN);
        }
        /*CookieFigthDTO monster=playerService.getPlayerFigthDTOByName(name).getMonster();
        if(monster==null){
            return new ResponseEntity<>("You haven't a Cookie Monster yet. You need first select one.", HttpStatus.FORBIDDEN);
        }
        //return new ResponseEntity<>(cookiePlayerService.getCookieFigthDTOFromPlayerByName(name), HttpStatus.ACCEPTED);
        return new ResponseEntity<>(monster, HttpStatus.ACCEPTED);*/
        return new ResponseEntity<>(cookiePlayerService.getCookieFigthDTOFromPlayerByName(name),HttpStatus.ACCEPTED);
    }
}
