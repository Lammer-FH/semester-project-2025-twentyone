package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.GameSessionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/game-sessions")
public class GameSessionController {

    @Operation(summary = "Zeigt den aktuellen Spielstatus an")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spielstatus erfolgreich geladen"),
            @ApiResponse(responseCode = "404", description = "Spielsession nicht gefunden")
    })
    @GetMapping("/{id}")
    public GameSessionDto getGameStatus(@PathVariable Long id) {
        LocalDateTime now = LocalDateTime.now();
        return new GameSessionDto(
                id,
                now,
                now.plusMinutes(5)
        );
    }
}