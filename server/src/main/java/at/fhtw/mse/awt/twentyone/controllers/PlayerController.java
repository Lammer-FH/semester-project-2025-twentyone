package at.fhtw.mse.awt.twentyone.controllers;

import at.fhtw.mse.awt.twentyone.dtos.player.LoginRequestDto;
import at.fhtw.mse.awt.twentyone.dtos.player.PlayerDto;
import at.fhtw.mse.awt.twentyone.dtos.player.PlayerRequestDto;
import at.fhtw.mse.awt.twentyone.interfaces.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
@Tag(name = "Player", description = "Player profile management")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Operation(summary = "Get player by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Player found"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    @GetMapping("/{id}")
    public PlayerDto getPlayer(@PathVariable Long id) {

        return playerService.getPlayer(id);
    }

    @Operation(summary = "Login player with username and password")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public PlayerDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return playerService.login(loginRequestDto.getUserName(), loginRequestDto.getPassword());
    }

    @Operation(summary = "Create new player")
    @ApiResponse(responseCode = "201", description = "Player created")
    @PostMapping
    public PlayerDto createPlayer(@RequestBody PlayerRequestDto playerRequestDto) {
        return playerService.createPlayer(playerRequestDto);
    }

    @Operation(summary = "Update player by ID")
    @ApiResponse(responseCode = "200", description = "Player updated")
    @PutMapping("/{id}")
    public PlayerDto updatePlayer(@PathVariable Long id, @RequestBody PlayerRequestDto playerRequestDto) {
        return playerService.updatePlayer(id, playerRequestDto);
    }

    @Operation(summary = "Delete player by ID")
    @ApiResponse(responseCode = "204", description = "Player deleted")
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
    }

}
