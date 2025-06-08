package at.fhtw.mse.awt.twentyone.repositories;

import at.fhtw.mse.awt.twentyone.entities.GameSession;
import at.fhtw.mse.awt.twentyone.entities.Move;
import at.fhtw.mse.awt.twentyone.entities.Player; // 1. Import Player
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource; // 2. Import TestPropertySource

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.sql.init.mode=never")
class MoveRepositoryTest {

    @Autowired
    private MoveRepository moveRepository;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void shouldFindMovesByGameSessionId() {

        Player player = new Player(null, "testplayer", "Test Player", "hash");
        player = playerRepository.save(player);


        GameSession session = new GameSession();
        session.setStartTime(LocalDateTime.now());
        session.setPlayer(player); // Set the required Player
        session.setStatus("ACTIVE"); // Set the required status
        session = gameSessionRepository.save(session);


        Move move = new Move();
        move.setGameSession(session);
        move.setMoveType("hit");
        move.setOccuredAt(LocalDateTime.now());
        moveRepository.save(move);

        List<Move> result = moveRepository.findByGameSession_Id(session.getId());

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMoveType()).isEqualTo("hit"); // Using get(0) as you had
        assertThat(result.get(0).getGameSession().getId()).isEqualTo(session.getId());
    }
}