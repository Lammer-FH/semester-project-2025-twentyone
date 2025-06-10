package at.fhtw.mse.awt.twentyone.services;

import at.fhtw.mse.awt.twentyone.dtos.gameResult.GameResultDto;
import at.fhtw.mse.awt.twentyone.entities.GameResult;
import at.fhtw.mse.awt.twentyone.interfaces.GameResultService;
import at.fhtw.mse.awt.twentyone.repositories.GameResultRepository;
import at.fhtw.mse.awt.twentyone.repositories.GameSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameResultServiceImpl implements GameResultService {

    private final GameResultRepository gameResultRepository;
    private final GameSessionRepository gameSessionRepository;

    public GameResultServiceImpl(GameResultRepository gameResultRepository,
                                 GameSessionRepository gameSessionRepository) {
        this.gameResultRepository = gameResultRepository;
        this.gameSessionRepository = gameSessionRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public GameResultDto getGameResultBySessionId(Long sessionId) {
        if (!gameSessionRepository.existsById(sessionId)) {
            throw new EntityNotFoundException("GameSession not found with id: " + sessionId);
        }
        GameResult gameResult = gameResultRepository.findByGameSession_Id(sessionId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "GameResult not found for GameSession id: " + sessionId
                ));
        return mapToDto(gameResult);
    }

    private GameResultDto mapToDto(GameResult gameResult) {
        if (gameResult == null) {
            return null;
        }
        return new GameResultDto(
                gameResult.getGameResultId(),
                gameResult.getGameSession().getId(),
                gameResult.getOutcome().name(),
                gameResult.getPayout()
        );
    }
}