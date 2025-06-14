package at.fhtw.mse.awt.twentyone.repositories;

import at.fhtw.mse.awt.twentyone.entities.GameResult;
import at.fhtw.mse.awt.twentyone.enums.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameResultRepository extends JpaRepository<GameResult, Long> {
    List<GameResult> findByOutcome(Outcome outcome);
    Optional<GameResult> findByGameSession_Id(Long gameSessionId);
    void deleteByGameSession_Player_PlayerId(Long playerId);
}