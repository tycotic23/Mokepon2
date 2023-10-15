package com.mokepon.mokepon.dtos;

import com.mokepon.mokepon.models.CookiePlayer;

import java.util.ArrayList;
import java.util.List;

public class CookieFigthDTO {
    private long id;
    private String animIdle, animMove;
    private String cookieType;
    private List<String> animAttacks=new ArrayList<>();
    private List<String> attacks=new ArrayList<>();
    private int health;

    public CookieFigthDTO() {
    }

    public CookieFigthDTO(CookiePlayer cookiePlayer) {
        this.id = cookiePlayer.getId();
        this.animIdle = cookiePlayer.getAnimIdle();
        this.animMove = cookiePlayer.getAnimMove();
        this.cookieType = cookiePlayer.getCookieType();
        this.animAttacks = cookiePlayer.getAnimAttacks();
        this.attacks = cookiePlayer.getAttacks();
        this.health = cookiePlayer.getHealth();
    }

    public long getId() {
        return id;
    }

    public String getAnimIdle() {
        return animIdle;
    }

    public String getAnimMove() {
        return animMove;
    }

    public String getCookieType() {
        return cookieType;
    }

    public List<String> getAnimAttacks() {
        return animAttacks;
    }

    public List<String> getAttacks() {
        return attacks;
    }

    public int getHealth() {
        return health;
    }
}
