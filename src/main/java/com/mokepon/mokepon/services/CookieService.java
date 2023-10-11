package com.mokepon.mokepon.services;

import com.mokepon.mokepon.models.Cookie;

import java.util.List;

public interface CookieService {
    List<Cookie> getAllCookies();
    Cookie getCookieById(long id);
}
