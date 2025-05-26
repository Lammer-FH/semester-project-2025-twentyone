package at.fhtw.mse.awt.twentyone.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Long id;
    private Long gameSessionId;
    private String rank;
    private String suit;
    private int position;
}