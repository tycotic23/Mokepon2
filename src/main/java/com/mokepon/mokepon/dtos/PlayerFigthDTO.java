package com.mokepon.mokepon.dtos;


import com.mokepon.mokepon.models.Player;

public class PlayerFigthDTO {
    private long id;
    private int points;
    private String name;
    private CookieFigthDTO monster;

    public PlayerFigthDTO() {
    }

    public PlayerFigthDTO(Player player) {
        this.id = player.getId();
        this.points = player.getPoints();
        this.name = player.getName();
        this.monster = (player.getMonster()==null)? null:new CookieFigthDTO(player.getMonster());
    }

    public long getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public CookieFigthDTO getMonster() {
        return monster;
    }
}
