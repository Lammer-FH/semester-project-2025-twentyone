package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.move.MoveDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/game-sessions/{sessionId}/moves")
@Tag(name = "Moves", description = "Actions made by players in a session")
public class MoveController {

    @Operation(summary = "Get all moves for a session")
    @ApiResponse(responseCode = "200", description = "Moves retrieved")
    @GetMapping
    public List<MoveDto> getMoves(@PathVariable Long sessionId) {
        return List.of(new MoveDto(1L, sessionId, "hit", LocalDateTime.now()));
    }

    @Operation(summary = "Create a new move")
    @ApiResponse(responseCode = "201", description = "Move created")
    @PostMapping
    public MoveDto createMove(@PathVariable Long sessionId, @RequestBody MoveDto moveDto) {
        return moveDto;
    }
}
