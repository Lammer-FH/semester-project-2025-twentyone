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
    void shouldFindPlayerByUserName() {
        Player player = new Player();
        player.setUserName("jack21");
        player.setName("Jack Black");
        player.setPasswordHash("secureHash123");
        playerRepository.save(player);

        Optional<Player> found = playerRepository.findByUserName("jack21");

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Jack Black");
        assertThat(found.get().getPasswordHash()).isEqualTo("secureHash123");
    }

    @Test
    void shouldReturnEmptyWhenUserNameNotFound() {
        Optional<Player> result = playerRepository.findByUserName("nonexistent");
        assertThat(result).isEmpty();
    }
}
