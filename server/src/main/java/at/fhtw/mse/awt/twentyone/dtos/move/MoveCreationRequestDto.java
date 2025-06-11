package at.fhtw.mse.awt.twentyone.dtos.move;

import at.fhtw.mse.awt.twentyone.enums.MoveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveCreationRequestDto {
    private MoveType moveType;
}