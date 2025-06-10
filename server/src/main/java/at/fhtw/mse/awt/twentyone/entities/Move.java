package at.fhtw.mse.awt.twentyone.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "moves")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moveId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_session_id", referencedColumnName = "id", nullable = false)
    private GameSession gameSession;

    @Column(nullable = false)
    private String moveType;

    @Column(nullable = false)
    private LocalDateTime occuredAt;
}