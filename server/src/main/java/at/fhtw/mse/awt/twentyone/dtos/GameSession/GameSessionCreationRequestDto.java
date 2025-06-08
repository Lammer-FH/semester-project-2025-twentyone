package at.fhtw.mse.awt.twentyone.dtos.GameSession;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionCreationRequestDto {
    private Long playerId;
}