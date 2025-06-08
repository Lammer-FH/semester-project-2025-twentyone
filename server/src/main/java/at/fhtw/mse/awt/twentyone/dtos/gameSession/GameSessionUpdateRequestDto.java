package at.fhtw.mse.awt.twentyone.dtos.gameSession;

import at.fhtw.mse.awt.twentyone.enums.GameSessionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionUpdateRequestDto {
    private GameSessionStatus status;
}