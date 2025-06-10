package at.fhtw.mse.awt.twentyone.dtos.move;

import at.fhtw.mse.awt.twentyone.enums.MoveType;
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
    private MoveType moveType;
    private LocalDateTime occuredAt;
}
