package at.fhtw.mse.awt.twentyone.services;

import at.fhtw.mse.awt.twentyone.dtos.GameSessionDto;
import at.fhtw.mse.awt.twentyone.entities.GameSession;
import at.fhtw.mse.awt.twentyone.repositories.GameSessionRepository;
import jakarta.persistence.EntityNotFoundException; // Or a custom exception
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Good practice for read-only operations

import java.time.LocalDateTime;

@Service
public class GameSessionService {

    private final GameSessionRepository gameSessionRepository;

    // Constructor injection for the repository
    public GameSessionService(GameSessionRepository gameSessionRepository) {
        this.gameSessionRepository = gameSessionRepository;
    }

    @Transactional(readOnly = true)
    public GameSessionDto getGameSessionState(Long sessionId) {
        GameSession gameSession = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("GameSession not found with id: " + sessionId));
        return mapToDto(gameSession);
    }

    private GameSessionDto mapToDto(GameSession gameSession) {
        if (gameSession == null) {
            return null;
        }
        return new GameSessionDto(
                gameSession.getGameSessionId(),
                gameSession.getStartTime(),
                gameSession.getEndTime()
        );
    }


}