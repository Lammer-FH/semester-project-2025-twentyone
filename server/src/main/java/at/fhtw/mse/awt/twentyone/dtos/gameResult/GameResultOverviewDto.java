package at.fhtw.mse.awt.twentyone.dtos.gameResult;

import at.fhtw.mse.awt.twentyone.enums.Outcome;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResultOverviewDto {

    private Long gameSessionId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Outcome outcome;

    private LocalDateTime startTime;
}

