package at.fhtw.mse.awt.twentyone.interfaces;

import at.fhtw.mse.awt.twentyone.dtos.gameResult.GameResultDto;

/**
 * Service interface for retrieving game results.
 */
public interface GameResultService {

    /**
     * Returns the result for a given game session.
     *
     * @param sessionId ID of the game session
     * @return Game result as DTO
     */
    GameResultDto getGameResultBySessionId(Long sessionId);
}
