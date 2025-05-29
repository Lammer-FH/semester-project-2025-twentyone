package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.GameSessionDto;
import at.fhtw.mse.awt.twentyone.interfaces.GameSessionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameSessionController.class)
class GameSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameSessionService gameSessionService;

    @Test
    void shouldReturnGameSessionState() throws Exception {
        Long sessionId = 1L;
        GameSessionDto dto = new GameSessionDto(
                sessionId,
                LocalDateTime.of(2025, 1, 1, 10, 0),
                null
        );

        Mockito.when(gameSessionService.getGameSessionState(sessionId)).thenReturn(dto);

        mockMvc.perform(get("/game-sessions/{id}", sessionId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sessionId))
                .andExpect(jsonPath("$.startTime").value("2025-01-01T10:00:00"))
                .andExpect(jsonPath("$.endTime").doesNotExist());
    }
}
