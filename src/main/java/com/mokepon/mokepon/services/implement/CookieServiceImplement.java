package com.mokepon.mokepon.services.implement;

import com.mokepon.mokepon.models.Cookie;
import com.mokepon.mokepon.repositories.CookieRepository;
import com.mokepon.mokepon.services.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookieServiceImplement implements CookieService {
    @Autowired
    private CookieRepository cookieRepository;

    @Override
    public List<Cookie> getAllCookies() {
        return cookieRepository.findAll();
    }

    @Override
    public Cookie getCookieById(long id) {
        return cookieRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCookieById(long id) {
        cookieRepository.deleteById(id);
    }

    @Override
    public void addCookie(Cookie cookie) {
        cookieRepository.save(cookie);
    }
}
