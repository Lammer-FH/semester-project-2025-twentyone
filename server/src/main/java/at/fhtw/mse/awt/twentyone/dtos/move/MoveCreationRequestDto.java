package at.fhtw.mse.awt.twentyone.dtos.move;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveCreationRequestDto {
    private String moveType;
}