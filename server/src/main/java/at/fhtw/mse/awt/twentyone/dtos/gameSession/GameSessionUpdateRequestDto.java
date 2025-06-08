package at.fhtw.mse.awt.twentyone.dtos.gameSession;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionUpdateRequestDto {
    private String status;
}