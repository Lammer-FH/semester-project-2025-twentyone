package at.fhtw.mse.awt.twentyone.interfaces;

import at.fhtw.mse.awt.twentyone.dtos.player.PlayerDto;
import at.fhtw.mse.awt.twentyone.dtos.player.PlayerRequestDto;

/**
 * Service interface for retrieving player profile.
 */
public interface PlayerService {

    /**
     * Retrieves the player profile.
     *
     * @param playerId of the player
     * @return DTO with player data
     */
    PlayerDto getPlayer(Long playerId);

    /**
     * Login for player.
     *
     * @param username of the player
     * @param password of the player
     * @return DTO with player data
     */
    PlayerDto login(String username, String password);

    /**
     * Creates a new player profile.
     *
     * @param playerRequestDto Request DTO with player data
     * @return DTO of the created player with generated ID
     */
    PlayerDto createPlayer(PlayerRequestDto playerRequestDto);

    /**
     * Updates an existing player profile.
     *
     * @param playerId ID of the player to update
     * @param playerDto DTO with updated player data
     * @return DTO of the updated player
     */
    PlayerDto updatePlayer(Long playerId, PlayerRequestDto playerDto);

    /**
     * Deletes a player profile.
     *
     * @param playerId ID of the player to delete
     */
    void deletePlayer(Long playerId);
}
