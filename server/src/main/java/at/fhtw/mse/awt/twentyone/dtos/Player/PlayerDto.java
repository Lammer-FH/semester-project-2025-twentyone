package at.fhtw.mse.awt.twentyone.dtos.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Long id;
    private String userName;
    private String name;
}