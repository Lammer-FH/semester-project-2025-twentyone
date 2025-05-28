package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.GameSessionDto;
import at.fhtw.mse.awt.twentyone.services.GameSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game-sessions")
@Tag(name = "Game Sessions", description = "Manage and view game sessions")
public class GameSessionController {

    private final GameSessionService gameSessionService;

    public GameSessionController(GameSessionService gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    @Operation(summary = "View the current state of the game session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game session status retrieved successfully",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = GameSessionDto.class))),
            @ApiResponse(responseCode = "404", description = "Game session not found")
    })
    @GetMapping("/{id}")
    public GameSessionDto getGameStatus(@PathVariable Long id) {
        return gameSessionService.getGameSessionState(id);
    }
}
