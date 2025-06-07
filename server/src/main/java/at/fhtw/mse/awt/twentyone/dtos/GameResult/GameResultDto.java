package at.fhtw.mse.awt.twentyone.dtos.GameResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResultDto {
    private Long id;
    private Long gameSessionId;
    private String outcome;
    private BigDecimal payout;
}