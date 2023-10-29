package com.mokepon.mokepon.dtos;

import com.mokepon.mokepon.models.Battle;

import java.util.Set;
import java.util.stream.Collectors;

public class BattleDTO {

    private long id;
    private int round;

    private Set<PlayerFigthDTO> fighters;

    public BattleDTO() {
    }

    public BattleDTO(Battle battle) {
        this.fighters = battle.getFighters().stream().map(PlayerFigthDTO::new).collect(Collectors.toSet());
        this.id = battle.getId();
        this.round = battle.getRoundNumber();
    }

    public Set<PlayerFigthDTO> getFighters() {
        return fighters;
    }

    public long getId() {
        return id;
    }

    public int getRound() {
        return round;
    }
}
