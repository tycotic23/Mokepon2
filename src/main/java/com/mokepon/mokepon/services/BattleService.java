package com.mokepon.mokepon.services;

import com.mokepon.mokepon.models.Battle;
import com.mokepon.mokepon.models.Player;

public interface BattleService {
    Battle createBattleRoom();
    void addPlayer(long id, Player player);

    void destroyBattleRoom(long id);

    boolean existsById(long id);

    Battle getBattleRoomById(long id);
}
