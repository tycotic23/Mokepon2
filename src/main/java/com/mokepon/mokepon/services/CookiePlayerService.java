package com.mokepon.mokepon.services;

import com.mokepon.mokepon.models.CookiePlayer;

import java.util.List;

public interface CookiePlayerService {

    List<CookiePlayer> getAllCookies();
    CookiePlayer getCookieById(long id);

    void deleteCookieById(long id);
    void addCookie(CookiePlayer cookie);
}
