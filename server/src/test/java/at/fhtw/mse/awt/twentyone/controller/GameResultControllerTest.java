package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.gameResult.GameResultDto;
import at.fhtw.mse.awt.twentyone.dtos.gameResult.GameResultOverviewDto;
import at.fhtw.mse.awt.twentyone.enums.Outcome;
import at.fhtw.mse.awt.twentyone.interfaces.GameResultService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameResultController.class)
class GameResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GameResultService gameResultService;

    @Test
    @DisplayName("GET /game-results/session/{sessionId} → 200 + vollständiges GameResultDto JSON")
    void shouldReturnGameResultForSession() throws Exception {
        Long sessionId = 1L;
        GameResultDto dto = new GameResultDto(
                10L,
                sessionId,
                "WIN",
                new BigDecimal("150.00")
        );

        when(gameResultService.getGameResultBySessionId(sessionId)).thenReturn(dto);

        mockMvc.perform(get("/game-results/session/{sessionId}", sessionId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    @DisplayName("GET /game-results/player/{playerId} → 200 + JSON-Liste GameResultOverviewDto")
    void shouldReturnResultsForPlayer() throws Exception {
        Long playerId = 5L;
        GameResultOverviewDto overview1 = new GameResultOverviewDto(
                20L,
                Outcome.WIN,
                LocalDateTime.of(2025, 6, 1, 10, 15, 0)
        );
        GameResultOverviewDto overview2 = new GameResultOverviewDto(
                21L,
                Outcome.LOSS,
                LocalDateTime.of(2025, 6, 2, 11, 30, 0)
        );
        List<GameResultOverviewDto> list = Arrays.asList(overview1, overview2);

        when(gameResultService.getResultsForPlayer(playerId)).thenReturn(list);

        mockMvc.perform(get("/game-results/player/{playerId}", playerId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }
}
