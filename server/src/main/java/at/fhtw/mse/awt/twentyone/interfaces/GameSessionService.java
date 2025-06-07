package at.fhtw.mse.awt.twentyone.interfaces;

import at.fhtw.mse.awt.twentyone.dtos.GameSession.GameSessionDto;

/**
 * Service interface for retrieving game session state.
 */
public interface GameSessionService {

    /**
     * Retrieves the state of a game session.
     *
     * @param sessionId ID of the game session
     * @return DTO with game session data
     */
    GameSessionDto getGameSessionState(Long sessionId);
}
