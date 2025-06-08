package at.fhtw.mse.awt.twentyone.services;

import at.fhtw.mse.awt.twentyone.dtos.gameResult.GameResultDto;
import at.fhtw.mse.awt.twentyone.entities.GameResult;
import at.fhtw.mse.awt.twentyone.entities.GameSession;
import at.fhtw.mse.awt.twentyone.repositories.GameResultRepository;
import at.fhtw.mse.awt.twentyone.repositories.GameSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameResultServiceImplTest {

    @Mock
    private GameResultRepository gameResultRepository;

    @Mock
    private GameSessionRepository gameSessionRepository;

    @InjectMocks
    private GameResultServiceImpl gameResultService;

    @Test
    void shouldReturnGameResultDto_whenGameSessionExists() {
        Long sessionId = 1L;

        GameSession session = new GameSession();
        session.setId(sessionId);

        GameResult result = new GameResult();
        result.setGameResultId(10L);
        result.setGameSession(session);
        result.setOutcome(at.fhtw.mse.awt.twentyone.entities.Outcome.WIN);
        result.setPayout(BigDecimal.valueOf(50.0));

        when(gameSessionRepository.existsById(sessionId)).thenReturn(true);
        when(gameResultRepository.findByGameSession_Id(sessionId)).thenReturn(Optional.of(result));

        GameResultDto dto = gameResultService.getGameResultBySessionId(sessionId);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(10L);
        assertThat(dto.getGameSessionId()).isEqualTo(sessionId);
        assertThat(dto.getOutcome()).isEqualTo("WIN");
        assertThat(dto.getPayout()).isEqualTo(BigDecimal.valueOf(50.0));
    }

    @Test
    void shouldThrowException_whenGameSessionNotFound() {
        Long sessionId = 999L;

        when(gameSessionRepository.existsById(sessionId)).thenReturn(false);

        assertThatThrownBy(() -> gameResultService.getGameResultBySessionId(sessionId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("GameSession not found with id: " + sessionId);
    }

}
