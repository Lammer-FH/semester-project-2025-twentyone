package at.fhtw.mse.awt.twentyone.dtos.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequestDto {
    private Long id;
    private String userName;
    private String name;
    private String passwordHash;
}