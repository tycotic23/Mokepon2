package com.mokepon.mokepon.services;

import com.mokepon.mokepon.models.Player;

public interface BattleService {
    long createBattleRoom();
    void addPlayer(long id, Player player);

    void destroyBattleRoom(long id);
}
