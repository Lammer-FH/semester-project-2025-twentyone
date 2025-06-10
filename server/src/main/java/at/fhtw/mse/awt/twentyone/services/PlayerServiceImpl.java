package at.fhtw.mse.awt.twentyone.services;

import at.fhtw.mse.awt.twentyone.dtos.player.PlayerDto;
import at.fhtw.mse.awt.twentyone.dtos.player.PlayerRequestDto;
import at.fhtw.mse.awt.twentyone.entities.Player;
import at.fhtw.mse.awt.twentyone.interfaces.PlayerService;
import at.fhtw.mse.awt.twentyone.repositories.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    // Constructor injection for the repository
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public PlayerDto getPlayer(@NotNull Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + playerId));
        return mapToDto(player);
    }

    @Transactional
    @Override
    public PlayerDto login(@NotNull String username, @NotNull String password) {
        Player player = playerRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with username: " + username));
        if(Objects.equals(player.getPassword(), password)){
            return mapToDto(player);
        }
        throw new EntityNotFoundException("Wrong password");
    }

    @Override
    public PlayerDto createPlayer(@NotNull PlayerRequestDto playerRequestDto) {
        Player playerToCreate = mapToEntity(playerRequestDto);
        Player player = playerRepository.save(playerToCreate);
        return mapToDto(player);
    }

    @Override
    public PlayerDto updatePlayer(Long playerId, @NotNull PlayerRequestDto playerRequestDto) {
        Player playerToCreate = mapToEntity(playerRequestDto);
        Player player = playerRepository.save(playerToCreate);
        return mapToDto(player);
    }

    @Transactional
    @Override
    public void deletePlayer(@NotNull Long playerId) {
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