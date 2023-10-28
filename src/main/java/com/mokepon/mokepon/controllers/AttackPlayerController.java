package com.mokepon.mokepon.controllers;

import com.mokepon.mokepon.services.implement.AttackPlayerServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class AttackPlayerController {
    @Autowired
    private AttackPlayerServiceImplement attackPlayerService;


}
