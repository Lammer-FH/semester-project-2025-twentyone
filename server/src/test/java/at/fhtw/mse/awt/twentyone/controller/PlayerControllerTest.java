package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.PlayerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetPlayer() throws Exception {
        mockMvc.perform(get("/players/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userName", is("player123")))
                .andExpect(jsonPath("$.name", is("John Doe")));
    }

    @Test
    void shouldCreatePlayer() throws Exception {
        PlayerDto playerDto = new PlayerDto(1L, "player456", "Jane Smith");

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
    }
}
