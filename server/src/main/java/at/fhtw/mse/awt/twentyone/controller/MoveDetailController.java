package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.move.MoveDto;
import at.fhtw.mse.awt.twentyone.interfaces.MoveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/moves")
@Tag(name = "Move Details", description = "Edit individual moves")
public class MoveDetailController {

    private final MoveService moveService;

    public MoveDetailController(MoveService moveService) {
        this.moveService = moveService;
    }

    @Operation(summary = "Update a move")
    @ApiResponse(responseCode = "200", description = "Move updated")
    @PutMapping("/{id}")
    public MoveDto updateMove(@PathVariable Long id, @RequestBody MoveDto moveDto) {
        return moveService.updateMove(id, moveDto);
    }
}