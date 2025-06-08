package at.fhtw.mse.awt.twentyone.interfaces;

import at.fhtw.mse.awt.twentyone.dtos.GameSession.GameSessionCreationRequestDto;
import at.fhtw.mse.awt.twentyone.dtos.GameSession.GameSessionDto;
import at.fhtw.mse.awt.twentyone.dtos.GameSession.GameSessionUpdateRequestDto;

/**
 * Service interface for managing game sessions.
 */
public interface GameSessionService {

    /**
     * Starts a new game session for a given player.
     *
     * @param requestDto DTO containing the ID of the player starting the game.
     * @return DTO of the newly created game session.
     */
    GameSessionDto createGameSession(GameSessionCreationRequestDto requestDto);

    /**
     * Retrieves the current state of a game session.
     *
     * @param id The ID of the game session.
     * @return DTO with the game session's current state.
     */
    GameSessionDto getGameSession(Long id);

    /**
     * Updates a game session's activity state.
     *
     * @param id The ID of the game session to update.
     * @param requestDto DTO containing the new status.
     * @return DTO of the updated game session.
     */
    GameSessionDto updateGameSession(Long id, GameSessionUpdateRequestDto requestDto);

    /**
     * Ends a game session and clears its state from the system.
     *
     * @param id The ID of the game session to end.
     */
    void deleteGameSession(Long id);
}