package at.fhtw.mse.awt.twentyone.repositories;

import at.fhtw.mse.awt.twentyone.entities.GameResult;
import at.fhtw.mse.awt.twentyone.entities.GameSession;
import at.fhtw.mse.awt.twentyone.entities.Outcome;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class GameResultRepositoryTest {

    @Autowired
    private GameResultRepository gameResultRepository;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Test
    void shouldFindByOutcome() {
        GameSession session = new GameSession();
        session.setStartTime(LocalDateTime.now());
        gameSessionRepository.save(session);

        GameResult result = new GameResult();
        result.setGameSession(session);
        result.setOutcome(Outcome.WIN);
        result.setPayout(BigDecimal.valueOf(100));
        gameResultRepository.save(result);

        List<GameResult> results = gameResultRepository.findByOutcome(Outcome.WIN);

        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getOutcome()).isEqualTo(Outcome.WIN);
    }

    @Test
    void shouldFindByGameSessionId() {
        GameSession session = new GameSession();
        session.setStartTime(LocalDateTime.now());
        session = gameSessionRepository.save(session);

        GameResult result = new GameResult();
        result.setGameSession(session);
        result.setOutcome(Outcome.LOSS);
        result.setPayout(BigDecimal.ZERO);
        gameResultRepository.save(result);

        Optional<GameResult> optional = gameResultRepository.findByGameSession_GameSessionId(session.getGameSessionId());

        assertThat(optional).isPresent();
        assertThat(optional.get().getOutcome()).isEqualTo(Outcome.LOSS);
    }
}
