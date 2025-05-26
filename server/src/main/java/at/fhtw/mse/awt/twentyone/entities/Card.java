package at.fhtw.mse.awt.twentyone.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_session_id")
    private GameSession gameSession;

    @Column(nullable = false)
    private String rank;

    @Column(nullable = false)
    private String suit;

    @Column(nullable = false)
    private int position;
}