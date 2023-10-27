package com.mokepon.mokepon.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private long id;
    private int points=0;
    private String name;

    @OneToOne(mappedBy = "player")
    private CookiePlayer monster;
    @OneToOne(mappedBy = "player")
    private AttackPlayer attack;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "battle_id")
    private Battle battle;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public CookiePlayer getMonster() {
        return monster;
    }

    public void setMonster(CookiePlayer monster) {
        monster.setPlayer(this);
        this.monster = monster;
    }

    public AttackPlayer getAttack() {
        return attack;
    }

    public void setAttack(AttackPlayer attack) {
        attack.setPlayer(this);
        this.attack = attack;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }
}
