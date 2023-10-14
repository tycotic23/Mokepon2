package com.mokepon.mokepon.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cookie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private long id;

    private String animIdle, animMove;
    private String name, description;
   @ElementCollection
   @Column(name = "animAttack")
    private List<String> animAttacks=new ArrayList<>();
    @ElementCollection
    @Column(name = "attack")
    private List<String> attacks=new ArrayList<>();
    private int health;

    public Cookie() {
    }

    public Cookie(String animIdle, String animMove, String name, String description, List<String> animAttacks, List<String> attacks, int health) {
        this.animIdle = animIdle;
        this.animMove = animMove;
        this.name = name;
        this.description = description;
        this.animAttacks = animAttacks;
        this.attacks = attacks;
        this.health = health;
    }

    public Cookie(String name, String description, int health) {
        this.name = name;
        this.description = description;
        this.health = health;
    }

    public long getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
