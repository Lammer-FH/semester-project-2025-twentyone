package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.move.MoveCreationRequestDto;
import at.fhtw.mse.awt.twentyone.dtos.move.MoveDto;
import at.fhtw.mse.awt.twentyone.interfaces.MoveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-sessions/{sessionId}/moves")
@Tag(name = "Moves", description = "Actions made by players in a session")
public class MoveController {

    private final MoveService moveService;

    public MoveController(MoveService moveService) {
        this.moveService = moveService;
    }

    @Operation(summary = "Get all moves for a session")
    @ApiResponse(responseCode = "200", description = "Moves retrieved")
    @GetMapping
    public List<MoveDto> getMoves(@PathVariable Long sessionId) {
        return moveService.getMovesForSession(sessionId);
    }

    @Operation(summary = "Create a new move for a session")
    @ApiResponse(responseCode = "201", description = "Move created")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MoveDto createMove(@PathVariable Long sessionId, @RequestBody MoveCreationRequestDto requestDto) {
        return moveService.createMove(sessionId, requestDto);
    }
}