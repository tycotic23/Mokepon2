package com.mokepon.mokepon.services.implement;

import com.mokepon.mokepon.dtos.PlayerFigthDTO;
import com.mokepon.mokepon.models.Player;
import com.mokepon.mokepon.repositories.PlayerRepository;
import com.mokepon.mokepon.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<PlayerFigthDTO> getAllPlayersFigthDTO() {
        return getAllPlayers().stream().map(PlayerFigthDTO::new).collect(Collectors.toList());
    }

    @Override
    public PlayerFigthDTO getPlayerFigthDTOById(long id) {
        return new PlayerFigthDTO(getPlayerById(id));
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
