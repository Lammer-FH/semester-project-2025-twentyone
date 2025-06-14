package at.fhtw.mse.awt.twentyone.repositories;

import at.fhtw.mse.awt.twentyone.entities.Card;
import at.fhtw.mse.awt.twentyone.entities.GameSession;
import at.fhtw.mse.awt.twentyone.entities.Player;
import at.fhtw.mse.awt.twentyone.enums.GameSessionStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(properties = "spring.sql.init.mode=never")
@DataJpaTest
class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void shouldReturnCardsByGameSessionId() {
        Player player = new Player(null, "testplayer", "Test Player", "hash");
        player = playerRepository.save(player);

        GameSession session = new GameSession();
        session.setStartTime(LocalDateTime.now());

        session.setPlayer(player);
        session.setStatus(GameSessionStatus.ACTIVE);

        session = gameSessionRepository.save(session);

        Card card = new Card();
        card.setGameSession(session);
        card.setRank("A");
        card.setSuit("HEARTS");
        card.setPosition(1);
        cardRepository.save(card);

        List<Card> result = cardRepository.findByGameSession_Id(session.getId());

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getRank()).isEqualTo("A");
        assertThat(result.get(0).getSuit()).isEqualTo("HEARTS");
    }
}