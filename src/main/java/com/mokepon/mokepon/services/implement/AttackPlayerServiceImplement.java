package com.mokepon.mokepon.services.implement;

import com.mokepon.mokepon.models.AttackPlayer;
import com.mokepon.mokepon.repositories.AttackPlayerRepository;
import com.mokepon.mokepon.services.AttackPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttackPlayerServiceImplement implements AttackPlayerService {
    @Autowired
    private AttackPlayerRepository attackPlayerRepository;

    @Override
    public List<AttackPlayer> getAllAttacksPlayers() {
        return attackPlayerRepository.findAll();
    }

    @Override
    public AttackPlayer getAttackPlayerById(long id) {
        return attackPlayerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAttackPlayerById(long id) {
        attackPlayerRepository.deleteById(id);
    }

    @Override
    public void addAttackPlayer(AttackPlayer attackPlayer) {
        attackPlayerRepository.save(attackPlayer);
    }
}
