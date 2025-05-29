package at.fhtw.mse.awt.twentyone.repositories;

import at.fhtw.mse.awt.twentyone.entities.GameSession;
import at.fhtw.mse.awt.twentyone.entities.Move;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MoveRepositoryTest {

    @Autowired
    private MoveRepository moveRepository;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Test
    void shouldFindMovesByGameSessionId() {
        GameSession session = new GameSession();
        session.setStartTime(LocalDateTime.now());
        session = gameSessionRepository.save(session);

        Move move = new Move();
        move.setGameSession(session);
        move.setMoveType("hit");
        move.setOccuredAt(LocalDateTime.now());
        moveRepository.save(move);

        List<Move> result = moveRepository.findByGameSession_GameSessionId(session.getGameSessionId());

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMoveType()).isEqualTo("hit");
        assertThat(result.get(0).getGameSession().getGameSessionId()).isEqualTo(session.getGameSessionId());
    }
}
