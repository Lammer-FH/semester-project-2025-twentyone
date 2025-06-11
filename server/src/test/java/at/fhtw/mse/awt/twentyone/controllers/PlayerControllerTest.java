package at.fhtw.mse.awt.twentyone.controllers;

import at.fhtw.mse.awt.twentyone.dtos.player.PlayerDto;
import at.fhtw.mse.awt.twentyone.dtos.player.PlayerRequestDto;
import at.fhtw.mse.awt.twentyone.interfaces.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    @Test
    void shouldGetPlayer() throws Exception {
        PlayerDto player = new PlayerDto(1L, "player123", "John Doe");
        when(playerService.getPlayer(1L)).thenReturn(player);

        mockMvc.perform(get("/players/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userName", is("player123")))
                .andExpect(jsonPath("$.name", is("John Doe")));
    }

    @Test
    void shouldCreatePlayer() throws Exception {
        PlayerDto playerDto = new PlayerDto(1L, "player456", "Jane Smith");
        when(playerService.createPlayer(any(PlayerRequestDto.class))).thenReturn(playerDto);

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userName", is("player456")))
                .andExpect(jsonPath("$.name", is("Jane Smith")));
    }

    @Test
    void shouldUpdatePlayer() throws Exception {
        PlayerDto playerDto = new PlayerDto(1L, "player789", "Max Mustermann");
        when(playerService.updatePlayer(eq(1L), any(PlayerRequestDto.class))).thenReturn(playerDto);

        mockMvc.perform(put("/players/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userName", is("player789")))
                .andExpect(jsonPath("$.name", is("Max Mustermann")));
    }

    @Test
    void shouldDeletePlayer() throws Exception {
        mockMvc.perform(delete("/players/1"))
                .andExpect(status().isOk());

        verify(playerService).deletePlayer(1L);
    }
}
