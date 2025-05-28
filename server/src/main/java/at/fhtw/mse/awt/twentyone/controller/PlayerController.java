package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.PlayerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@Tag(name = "Player", description = "Player profile management")
public class PlayerController {

    @Operation(summary = "Get player by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Player found"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    @GetMapping("/{id}")
    public PlayerDto getPlayer(@PathVariable Long id) {
        return new PlayerDto(id, "player123", "John Doe");
    }

    @Operation(summary = "Create new player")
    @ApiResponse(responseCode = "201", description = "Player created")
    @PostMapping
    public PlayerDto createPlayer(@RequestBody PlayerDto playerDto) {
        return playerDto;
    }

    @Operation(summary = "Update player by ID")
    @ApiResponse(responseCode = "200", description = "Player updated")
    @PutMapping("/{id}")
    public PlayerDto updatePlayer(@PathVariable Long id, @RequestBody PlayerDto playerDto) {
        return new PlayerDto(id, playerDto.getUserName(), playerDto.getName());
    }

    @Operation(summary = "Delete player by ID")
    @ApiResponse(responseCode = "204", description = "Player deleted")
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        // delete logic
    }
}
