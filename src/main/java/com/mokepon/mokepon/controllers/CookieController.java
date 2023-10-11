package com.mokepon.mokepon.controllers;

import com.mokepon.mokepon.services.implement.CookieServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class CookieController {
    @Autowired
    private CookieServiceImplement cookieService;

    @GetMapping("/cookie")
    public ResponseEntity<Object> getAllCookies(){
        return new ResponseEntity<>(cookieService.getAllCookies(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/cookie/{id}")
    public ResponseEntity<Object> getCookieById(@PathVariable long id){
        return new ResponseEntity<>(cookieService.getCookieById(id),HttpStatus.ACCEPTED);
    }
}
