package at.fhtw.mse.awt.twentyone.dtos.gameSession;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @Builder // <-- REMOVED
public class GameSessionDto {
    private Long id;
    private Long playerId;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}