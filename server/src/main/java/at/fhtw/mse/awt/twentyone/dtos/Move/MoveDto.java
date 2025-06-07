package at.fhtw.mse.awt.twentyone.dtos.Move;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveDto {
    private Long id;
    private Long gameSessionId;
    private String moveType;
    private LocalDateTime occuredAt;
}
