package com.mokepon.mokepon.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CookiePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    private String animIdle, animMove;
    private String cookieType, description;
    @ElementCollection
    @Column(name = "animAttack")
    private List<String> animAttacks=new ArrayList<>();
    @ElementCollection
    @Column(name = "attack")
    private List<String> attacks=new ArrayList<>();
    private int health;

    //map


    public CookiePlayer() {
    }

    public CookiePlayer(Cookie cookie,Player player) {
        this.animIdle= cookie.getAnimIdle();
        this.animMove=cookie.getAnimMove();
        this.animAttacks=cookie.getAnimAttacks();
        this.cookieType=cookie.getName();
        this.description=cookie.getDescription();
        this.attacks=cookie.getAttacks();
        this.health= cookie.getHealth();
        player.setMonster(this);
    }

    public long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public String getAnimIdle() {
        return animIdle;
    }

    public void setAnimIdle(String animIdle) {
        this.animIdle = animIdle;
    }

    public String getAnimMove() {
        return animMove;
    }

    public void setAnimMove(String animMove) {
        this.animMove = animMove;
    }

    public String getCookieType() {
        return cookieType;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAnimAttacks() {
        return animAttacks;
    }

    public void setAnimAttacks(List<String> animAttacks) {
        this.animAttacks = animAttacks;
    }

    public List<String> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<String> attacks) {
        this.attacks = attacks;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
