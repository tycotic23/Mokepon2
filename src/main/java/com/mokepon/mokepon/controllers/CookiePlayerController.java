package com.mokepon.mokepon.controllers;

import com.mokepon.mokepon.services.implement.CookiePlayerServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class CookiePlayerController {

    @Autowired
    CookiePlayerServiceImplement cookiePlayerService;
}
