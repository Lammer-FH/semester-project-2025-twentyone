package at.fhtw.mse.awt.twentyone.services;

import at.fhtw.mse.awt.twentyone.dtos.GameSession.GameSessionDto;
import at.fhtw.mse.awt.twentyone.entities.GameSession;
import at.fhtw.mse.awt.twentyone.repositories.GameSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameSessionServiceImplTest {

    @Mock
    private GameSessionRepository gameSessionRepository;

    @InjectMocks
    private GameSessionServiceImpl gameSessionService;

    @Test
    void shouldReturnGameSessionDto_whenSessionExists() {
        Long sessionId = 1L;

        GameSession session = new GameSession();
        session.setGameSessionId(sessionId);
        session.setStartTime(LocalDateTime.of(2025, 5, 29, 12, 0));
        session.setEndTime(LocalDateTime.of(2025, 5, 29, 12, 30));

        when(gameSessionRepository.findById(sessionId)).thenReturn(Optional.of(session));

        GameSessionDto dto = gameSessionService.getGameSessionState(sessionId);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(sessionId);
        assertThat(dto.getStartTime()).isEqualTo(session.getStartTime());
        assertThat(dto.getEndTime()).isEqualTo(session.getEndTime());
    }

    @Test
    void shouldThrowException_whenSessionNotFound() {
        Long sessionId = 999L;

        when(gameSessionRepository.findById(sessionId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> gameSessionService.getGameSessionState(sessionId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("GameSession not found");
    }
}
