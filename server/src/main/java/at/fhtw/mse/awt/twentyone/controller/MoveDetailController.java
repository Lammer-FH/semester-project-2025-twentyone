package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.Move.MoveDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/moves")
@Tag(name = "Move Details", description = "Edit or delete individual moves")
public class MoveDetailController {

    @Operation(summary = "Update a move")
    @ApiResponse(responseCode = "200", description = "Move updated")
    @PutMapping("/{id}")
    public MoveDto updateMove(@PathVariable Long id, @RequestBody MoveDto moveDto) {
        return new MoveDto(id, moveDto.getGameSessionId(), moveDto.getMoveType(), moveDto.getOccuredAt());
    }

    @Operation(summary = "Delete a move")
    @ApiResponse(responseCode = "204", description = "Move deleted")
    @DeleteMapping("/{id}")
    public void deleteMove(@PathVariable Long id) {
        // deletion logic
    }
}
