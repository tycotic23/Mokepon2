package com.mokepon.mokepon.services;

import com.mokepon.mokepon.models.AttackPlayer;
import com.mokepon.mokepon.models.Battle;
import com.mokepon.mokepon.models.Player;

public interface BattleService {
    Battle createBattleRoom();
    void addPlayer(long id, Player player);

    void destroyBattleRoom(long id);
    void destroyBattleRoom(Battle battle);

    boolean existsById(long id);

    Battle getBattleRoomById(long id);

    void updateBattleRoom(Battle battle);

    boolean wasPlayerAttacked(Player player);

    void deletePlayerAttack(Player player);

    //void sendAttack(AttackPlayer attackPlayer, Battle battle);
}
