package at.fhtw.mse.awt.twentyone.dtos.gameSession;

import at.fhtw.mse.awt.twentyone.enums.GameSessionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GameSessionDto {
    private Long id;
    private Long playerId;
    private GameSessionStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}