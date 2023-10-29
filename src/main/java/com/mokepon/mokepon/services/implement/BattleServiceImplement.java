package com.mokepon.mokepon.services.implement;

import com.mokepon.mokepon.dtos.BattleDTO;
import com.mokepon.mokepon.models.AttackPlayer;
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
        destroyBattleRoom(getBattleRoomById(id));
    }

    @Override
    public void destroyBattleRoom(Battle battle) {
        //antes de eliminarlo desvincularlo de los jugadores
        //quitarlo de ambos jugadores
        for(Player p: battle.getFighters()){
            if(p!=null) {
                p.setBattle(null);
            }
        }
        //borrar el battle room
        battleRepository.deleteById(battle.getId());
    }

    @Override
    public boolean existsById(long id) {
        return battleRepository.existsById(id);
    }

    @Override
    public Battle getBattleRoomById(long id) {
        return battleRepository.findById(id).orElse(null);
    }

    @Override
    public BattleDTO getBattleRoomDTOById(long id) {
        return new BattleDTO(getBattleRoomById(id));
    }

    @Override
    public void updateBattleRoom(Battle battle) {
        battleRepository.save(battle);
    }

    @Override
    public boolean wasPlayerAttacked(Player player) {
        return player.getBattle().getAttacks().contains(player.getAttack());
    }

    @Override
    public void addFightResult(Battle battle, long playerWinner, int lostHealth) {
        battle.addFightResult(playerWinner);
    }

    @Override
    public Battle initFightSimulation(Battle battle) {
        //obtengo los jugadores
        Player player1=(Player)battle.getFighters().toArray()[0];
        Player player2=(Player)battle.getFighters().toArray()[1];
        long winner=-1;
        long loser=-1;
        //obtengo el jugador que pierde vida (si lo hay)
        if(player1.getAttack().winTo(player2.getAttack())){
            loser= player2.getId();
            player2.applyDamage(10);
        }else if(player2.getAttack().winTo(player1.getAttack())){
            loser= player1.getId();
            player1.applyDamage(10);
        }
        //sumo el ganador a la lista
        addFightResult(battle,loser,-10);
        //devuelve el jugador que perdio
        return battle;
    }
}
