package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.gameResult.GameResultDto;
import at.fhtw.mse.awt.twentyone.interfaces.GameResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import java.util.List; // No longer returning a list

@RestController
@RequestMapping("/game-sessions/{sessionId}/game-results") // Path kept as per spec
@Tag(name = "Game Results", description = "Result for a completed game session")
public class GameResultController {

    private final GameResultService gameResultService;

    public GameResultController(GameResultService gameResultService) {
        this.gameResultService = gameResultService;
    }

    @Operation(summary = "Get the game result for a specific game session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game result retrieved successfully.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GameResultDto.class))), // Singular DTO
            @ApiResponse(responseCode = "404", description = "Game session or game result not found")
    })
    @GetMapping
    public GameResultDto getGameResultForSession(@PathVariable Long sessionId) {

        return gameResultService.getGameResultBySessionId(sessionId);

    }
}