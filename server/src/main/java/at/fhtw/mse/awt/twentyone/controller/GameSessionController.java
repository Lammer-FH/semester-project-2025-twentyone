package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.GameSession.GameSessionCreationRequestDto;
import at.fhtw.mse.awt.twentyone.dtos.GameSession.GameSessionDto;
import at.fhtw.mse.awt.twentyone.dtos.GameSession.GameSessionUpdateRequestDto;
import at.fhtw.mse.awt.twentyone.interfaces.GameSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game-sessions")
@Tag(name = "Game Session", description = "Game session management")
public class GameSessionController {

    private final GameSessionService gameSessionService;

    public GameSessionController(GameSessionService gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    @Operation(summary = "Start a new game session")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Game session created"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    @PostMapping
    public ResponseEntity<GameSessionDto> createGameSession(@RequestBody GameSessionCreationRequestDto requestDto) {
        GameSessionDto newGameSession = gameSessionService.createGameSession(requestDto);
        return new ResponseEntity<>(newGameSession, HttpStatus.CREATED);
    }

    @Operation(summary = "Get game session by ID (View Game Status)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Game session found"),
            @ApiResponse(responseCode = "404", description = "Game session not found")
    })
    @GetMapping("/{id}")
    public GameSessionDto getGameSession(@PathVariable Long id) {
        return gameSessionService.getGameSession(id);
    }

    @Operation(summary = "Modify game-session details (e.g., change activity state)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Game session updated"),
            @ApiResponse(responseCode = "404", description = "Game session not found")
    })
    @PutMapping("/{id}")
    public GameSessionDto updateGameSession(@PathVariable Long id, @RequestBody GameSessionUpdateRequestDto requestDto) {
        return gameSessionService.updateGameSession(id, requestDto);
    }

    @Operation(summary = "End a game session and clear its state")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Game session deleted"),
            @ApiResponse(responseCode = "404", description = "Game session not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameSession(@PathVariable Long id) {
        gameSessionService.deleteGameSession(id);
    }
}