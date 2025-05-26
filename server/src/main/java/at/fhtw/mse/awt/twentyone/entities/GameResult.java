package at.fhtw.mse.awt.twentyone.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "game_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameResultId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_session_id")
    private GameSession gameSession;

    @Column(nullable = false)
    private String outcome;

    @Column(nullable = false)
    private BigDecimal payout;
}