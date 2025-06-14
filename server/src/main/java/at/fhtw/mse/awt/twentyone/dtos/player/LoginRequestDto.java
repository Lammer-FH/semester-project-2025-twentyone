package at.fhtw.mse.awt.twentyone.dtos.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto
{
    private String userName;
    private String password;
}