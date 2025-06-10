package at.fhtw.mse.awt.twentyone.interfaces;

import at.fhtw.mse.awt.twentyone.dtos.gameResult.GameResultDto;
import at.fhtw.mse.awt.twentyone.dtos.gameResult.GameResultOverviewDto;

import java.util.List;

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


    /**
     * Returns a List of Results for a given playerId
     * @param playerId ID of the Player
     * @return GameResult Overview with all gamestats
     */
    List<GameResultOverviewDto> getResultsForPlayer(Long playerId);

}
