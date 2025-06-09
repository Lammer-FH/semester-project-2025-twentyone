package at.fhtw.mse.awt.twentyone.repositories;

import at.fhtw.mse.awt.twentyone.entities.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void shouldFindPlayerByPlayerId() {
        Player player = new Player();
        player.setUserName("jack21");
        player.setName("Jack Black");
        player.setPassword("secureHash123");
        player.setPlayerId(123L);
        playerRepository.save(player);

        Optional<Player> found = playerRepository.findByPlayerId(123L);

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Jack Black");
        assertThat(found.get().getPassword()).isEqualTo("secureHash123");
    }

    @Test
    void shouldReturnEmptyWhenPlayerIdNotFound() {
        Optional<Player> result = playerRepository.findByPlayerId(123456L);
        assertThat(result).isEmpty();
    }
}
