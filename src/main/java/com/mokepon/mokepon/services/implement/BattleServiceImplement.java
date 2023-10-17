package com.mokepon.mokepon.services.implement;

import com.mokepon.mokepon.models.Battle;
import com.mokepon.mokepon.models.Player;
import com.mokepon.mokepon.repositories.BattleRepository;
import com.mokepon.mokepon.services.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BattleServiceImplement implements BattleService {
    @Autowired
    BattleRepository battleRepository;
    @Override
    public Battle createBattleRoom() {
        Battle battle=new Battle();
        battleRepository.save(battle);
        return battle;
    }

    @Override
    public void addPlayer(long id, Player player) {
        Battle battle=battleRepository.findById(id).orElse(null);
        battle.addFighter(player);
        battleRepository.save(battle);
    }

    @Override
    public void destroyBattleRoom(long id) {
        battleRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return battleRepository.existsById(id);
    }

    @Override
    public Battle getBattleRoomById(long id) {
        return battleRepository.findById(id).orElse(null);
    }
}
