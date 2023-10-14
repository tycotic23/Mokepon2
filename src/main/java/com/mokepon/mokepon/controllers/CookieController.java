package com.mokepon.mokepon.controllers;

import com.mokepon.mokepon.models.Cookie;
import com.mokepon.mokepon.services.implement.CookieServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/cookie/{id}")
    public ResponseEntity<Object> DeleteCookieById(@PathVariable long id){
        cookieService.deleteCookieById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/cookie")
    public ResponseEntity<Object> AddCookie(@RequestBody Cookie cookie){
        cookieService.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
