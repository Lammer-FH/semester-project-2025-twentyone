package at.fhtw.mse.awt.twentyone.services;

import at.fhtw.mse.awt.twentyone.dtos.Player.PlayerDto;
import at.fhtw.mse.awt.twentyone.dtos.Player.PlayerRequestDto;
import at.fhtw.mse.awt.twentyone.entities.Player;
import at.fhtw.mse.awt.twentyone.interfaces.PlayerService;
import at.fhtw.mse.awt.twentyone.repositories.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    // Constructor injection for the repository
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public PlayerDto getPlayer(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + playerId));
        return mapToDto(player);
    }

    @Override
    public PlayerDto createPlayer(PlayerRequestDto playerRequestDto) {
        Player playerToCreate = mapToEntity(playerRequestDto);
        Player player = playerRepository.save(playerToCreate);
        return mapToDto(player);
    }

    @Override
    public PlayerDto updatePlayer(Long playerId, PlayerRequestDto playerRequestDto) {
        Player playerToCreate = mapToEntity(playerRequestDto);
        Player player = playerRepository.save(playerToCreate);
        return mapToDto(player);
    }

    @Transactional
    @Override
    public void deletePlayer(Long playerId) {
        playerRepository.removeByPlayerId(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + playerId));
    }

    private PlayerDto mapToDto(Player player) {
        if (player == null) {
            return null;
        }
        return new PlayerDto(
                player.getPlayerId(),
                player.getUserName(),
                player.getName()
        );
    }

    private Player mapToEntity(PlayerRequestDto player) {
        if (player == null) {
            return null;
        }
        return new Player(
                player.getId(),
                player.getUserName(),
                player.getName(),
                player.getPasswordHash()
        );
    }


}