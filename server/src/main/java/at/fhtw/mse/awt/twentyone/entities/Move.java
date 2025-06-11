package at.fhtw.mse.awt.twentyone.entities;

import at.fhtw.mse.awt.twentyone.enums.MoveType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MoveType moveType;

    @Column(nullable = false)
    private LocalDateTime occuredAt;
}