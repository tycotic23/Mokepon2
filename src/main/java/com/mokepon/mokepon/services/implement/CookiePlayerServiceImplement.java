package com.mokepon.mokepon.services.implement;

import com.mokepon.mokepon.dtos.CookieFigthDTO;
import com.mokepon.mokepon.models.CookiePlayer;
import com.mokepon.mokepon.repositories.CookiePlayerRepository;
import com.mokepon.mokepon.services.CookiePlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookiePlayerServiceImplement implements CookiePlayerService {

    @Autowired
    private CookiePlayerRepository cookiePlayerRepository;

    @Override
    public List<CookiePlayer> getAllCookies() {
        return cookiePlayerRepository.findAll();
    }

    @Override
    public CookiePlayer getCookieById(long id) {
        return cookiePlayerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCookieById(long id) {
        cookiePlayerRepository.deleteById(id);
    }

    @Override
    public void addCookie(CookiePlayer cookie) {
        cookiePlayerRepository.save(cookie);
    }

    @Override
    public void updateCookiePlayer(CookiePlayer cookie) {
        addCookie(cookie);
    }

    @Override
    public CookieFigthDTO getCookieFigthDTOFromPlayerByName(String name) {
        return new CookieFigthDTO(cookiePlayerRepository.findByPlayer_name(name));
    }

    @Override
    public boolean existsByPlayer_name(String name) {
        return cookiePlayerRepository.existsByPlayer_name(name);
    }
}
