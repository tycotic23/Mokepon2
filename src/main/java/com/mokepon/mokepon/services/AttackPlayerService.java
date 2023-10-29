package com.mokepon.mokepon.services;

import com.mokepon.mokepon.models.AttackPlayer;
import com.mokepon.mokepon.models.Battle;

import java.util.List;

public interface AttackPlayerService {
    List<AttackPlayer> getAllAttacksPlayers();
    AttackPlayer getAttackPlayerById(long id);

    void deleteAttackPlayerById(long id);
    void addAttackPlayer(AttackPlayer attackPlayer);

    void resetAttackList(Battle battle);
}
