package com.mokepon.mokepon.services.implement;

import com.mokepon.mokepon.models.Player;
import com.mokepon.mokepon.repositories.PlayerRepository;
import com.mokepon.mokepon.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImplement implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(long id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePlayerById(long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public void addPlayer(Player player) {
        playerRepository.save(player);
    }
}
