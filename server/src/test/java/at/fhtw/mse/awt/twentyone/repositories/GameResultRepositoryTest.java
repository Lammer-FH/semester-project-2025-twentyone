package at.fhtw.mse.awt.twentyone.repositories;

import at.fhtw.mse.awt.twentyone.entities.GameResult;
import at.fhtw.mse.awt.twentyone.entities.GameSession;
import at.fhtw.mse.awt.twentyone.entities.Player;
import at.fhtw.mse.awt.twentyone.enums.GameSessionStatus;
import at.fhtw.mse.awt.twentyone.enums.Outcome;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.sql.init.mode=never")
class GameResultRepositoryTest {

    @Autowired
    private GameResultRepository gameResultRepository;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void shouldFindByOutcome() {
        Player player = new Player(null, "player1", "Test Player One", "hash1");
        player = playerRepository.save(player);

        GameSession session = new GameSession();
        session.setStartTime(LocalDateTime.now());
        session.setPlayer(player);
        session.setStatus(GameSessionStatus.ENDED);
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
        Player player = new Player(null, "player2", "Test Player Two", "hash2");
        player = playerRepository.save(player);

        GameSession session = new GameSession();
        session.setStartTime(LocalDateTime.now());
        session.setPlayer(player);
        session.setStatus(GameSessionStatus.ENDED);
        session = gameSessionRepository.save(session);

        GameResult result = new GameResult();
        result.setGameSession(session);
        result.setOutcome(Outcome.LOSS);
        result.setPayout(BigDecimal.ZERO);
        gameResultRepository.save(result);

        Optional<GameResult> optional = gameResultRepository.findByGameSession_Id(session.getId());

        assertThat(optional).isPresent();
        assertThat(optional.get().getOutcome()).isEqualTo(Outcome.LOSS);
    }
}