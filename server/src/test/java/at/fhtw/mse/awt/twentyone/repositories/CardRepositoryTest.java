package at.fhtw.mse.awt.twentyone.repositories;

import at.fhtw.mse.awt.twentyone.entities.Card;
import at.fhtw.mse.awt.twentyone.entities.GameSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Test
    void shouldReturnCardsByGameSessionId() {
        GameSession session = new GameSession();
        session.setStartTime(LocalDateTime.now());
        session = gameSessionRepository.save(session);

        Card card = new Card();
        card.setGameSession(session);
        card.setRank("A");
        card.setSuit("HEARTS");
        card.setPosition(1);
        cardRepository.save(card);

        List<Card> result = cardRepository.findByGameSession_GameSessionId(session.getGameSessionId());

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getRank()).isEqualTo("A");
        assertThat(result.get(0).getSuit()).isEqualTo("HEARTS");
    }
}
