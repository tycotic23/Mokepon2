package com.mokepon.mokepon.services;

import com.mokepon.mokepon.dtos.PlayerFigthDTO;
import com.mokepon.mokepon.models.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();
    Player getPlayerById(long id);

    List<PlayerFigthDTO> getAllPlayersFigthDTO();
    PlayerFigthDTO getPlayerFigthDTOById(long id);

    void deletePlayerById(long id);
    void addPlayer(Player player);
}
