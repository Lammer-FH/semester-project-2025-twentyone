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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_session_id", referencedColumnName = "Id", unique = true) // Ensure unique FK
    private GameSession gameSession;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Outcome outcome;

    @Column(nullable = false)
    private BigDecimal payout;
}