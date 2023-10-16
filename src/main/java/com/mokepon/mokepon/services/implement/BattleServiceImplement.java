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
    public long createBattleRoom() {
        Battle battle=new Battle();
        battleRepository.save(battle);
        return battle.getId();
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
}
