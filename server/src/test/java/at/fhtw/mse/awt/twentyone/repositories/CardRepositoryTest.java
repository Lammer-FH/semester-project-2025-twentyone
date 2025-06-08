package at.fhtw.mse.awt.twentyone.repositories;

import at.fhtw.mse.awt.twentyone.entities.Card;
import at.fhtw.mse.awt.twentyone.entities.GameSession;
import at.fhtw.mse.awt.twentyone.entities.Player; // Import Player
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

    @Autowired // Add this
    private PlayerRepository playerRepository;

    @Test
    void shouldReturnCardsByGameSessionId() {
        // --- FIX: Step 1 - Create and save a Player first ---
        Player player = new Player(null, "testplayer", "Test Player", "hash");
        player = playerRepository.save(player);

        // --- Original Code, with fixes applied ---
        GameSession session = new GameSession();
        session.setStartTime(LocalDateTime.now());

        // --- FIX: Step 2 - Set the required fields on the session ---
        session.setPlayer(player);
        session.setStatus("ACTIVE");

        // Now this save will succeed
        session = gameSessionRepository.save(session);

        Card card = new Card();
        card.setGameSession(session);
        card.setRank("A");
        card.setSuit("HEARTS");
        card.setPosition(1);
        cardRepository.save(card);

        // --- ACT ---
        List<Card> result = cardRepository.findByGameSession_Id(session.getId());

        // --- ASSERT ---
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getRank()).isEqualTo("A"); // Using get(0) as you had
        assertThat(result.get(0).getSuit()).isEqualTo("HEARTS");
    }
}