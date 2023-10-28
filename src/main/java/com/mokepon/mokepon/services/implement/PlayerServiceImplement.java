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
    public Player getPlayerByName(String name) {
        return playerRepository.findByName(name);
    }

    @Override
    public PlayerFigthDTO getPlayerFigthDTOByName(String name) {
        return new PlayerFigthDTO(getPlayerByName(name));
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

    @Override
    public boolean checkBattleRoomBothPlayers(long idPlayer1, long idPlayer2) {
        //revisa si dos jugadores estan correctamente en la misma sala
        //si un jugador no existe o no tiene sala, es porque no estan en la misma y falta crearla
        Player player1=getPlayerById(idPlayer1);
        Player player2=getPlayerById(idPlayer2);
        if(player1==null || player2==null){
            return false;
        }
        if(player1.getBattle()==null || player2.getBattle()==null){
            return false;
        }
        return player1.getBattle().getId()==player2.getBattle().getId();
    }

    @Override
    public boolean existsById(long id) {
        return playerRepository.existsById(id);
    }
}
