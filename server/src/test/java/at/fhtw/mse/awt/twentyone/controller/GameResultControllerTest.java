package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.GameResultDto;
import at.fhtw.mse.awt.twentyone.interfaces.GameResultService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameResultController.class)
class GameResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameResultService gameResultService;

    @Test
    void shouldReturnGameResultForSession() throws Exception {
        Long sessionId = 1L;

        GameResultDto dto = new GameResultDto(
                10L,
                sessionId,
                "WIN",
                BigDecimal.valueOf(42.0)
        );

        Mockito.when(gameResultService.getGameResultBySessionId(sessionId)).thenReturn(dto);

        mockMvc.perform(get("/game-sessions/{sessionId}/game-results", sessionId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.gameSessionId").value(1))
                .andExpect(jsonPath("$.outcome").value("WIN"))
                .andExpect(jsonPath("$.payout").value(42.0));
    }
}