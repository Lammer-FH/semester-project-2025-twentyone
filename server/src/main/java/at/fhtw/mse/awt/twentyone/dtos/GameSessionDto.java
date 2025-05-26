package at.fhtw.mse.awt.twentyone.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}