package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.gameSession.GameSessionCreationRequestDto;
import at.fhtw.mse.awt.twentyone.dtos.gameSession.GameSessionDto;
import at.fhtw.mse.awt.twentyone.dtos.gameSession.GameSessionUpdateRequestDto;
import at.fhtw.mse.awt.twentyone.enums.GameSessionStatus;
import at.fhtw.mse.awt.twentyone.interfaces.GameSessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameSessionController.class)
class GameSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GameSessionService gameSessionService;

    @Test
    void testGetGameSession_shouldReturnGameSession_whenExists() throws Exception {
        // Arrange
        Long sessionId = 1L;
        Long playerId = 10L;
        GameSessionDto dto = new GameSessionDto(sessionId, playerId, GameSessionStatus.ACTIVE, LocalDateTime.of(2025, 1, 1, 10, 0), null);

        Mockito.when(gameSessionService.getGameSession(sessionId)).thenReturn(dto);

        // Act & Assert
        mockMvc.perform(get("/game-sessions/{id}", sessionId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sessionId))
                .andExpect(jsonPath("$.playerId").value(playerId))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(jsonPath("$.startTime").value("2025-01-01T10:00:00"));
    }

    @Test
    void testGetGameSession_shouldReturnNotFound_whenNotExists() throws Exception {
        // Arrange
        Long sessionId = 999L;
        Mockito.when(gameSessionService.getGameSession(sessionId)).thenThrow(new EntityNotFoundException("Game session not found"));

        // Act & Assert
        mockMvc.perform(get("/game-sessions/{id}", sessionId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateGameSession_shouldReturnCreated() throws Exception {
        // Arrange
        GameSessionCreationRequestDto requestDto = new GameSessionCreationRequestDto(10L);
        GameSessionDto responseDto = new GameSessionDto(1L, 10L, GameSessionStatus.ACTIVE, LocalDateTime.now(), null);

        Mockito.when(gameSessionService.createGameSession(any(GameSessionCreationRequestDto.class))).thenReturn(responseDto);

        // Act & Assert
        mockMvc.perform(post("/game-sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.playerId").value(10L))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    void testUpdateGameSession_shouldReturnEnded() throws Exception {
        // Arrange
        Long sessionId = 1L;
        GameSessionUpdateRequestDto requestDto = new GameSessionUpdateRequestDto(GameSessionStatus.ENDED);
        GameSessionDto responseDto = new GameSessionDto(sessionId, 10L, GameSessionStatus.ENDED, LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(gameSessionService.updateGameSession(eq(sessionId), any(GameSessionUpdateRequestDto.class))).thenReturn(responseDto);

        // Act & Assert
        mockMvc.perform(put("/game-sessions/{id}", sessionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ENDED"));
    }

    @Test
    void testDeleteGameSession_shouldReturnNoContent() throws Exception {
        // Arrange
        Long sessionId = 1L;
        Mockito.doNothing().when(gameSessionService).deleteGameSession(sessionId);

        // Act & Assert
        mockMvc.perform(delete("/game-sessions/{id}", sessionId))
                .andExpect(status().isNoContent());
    }
}