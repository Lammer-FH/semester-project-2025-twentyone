package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.move.MoveCreationRequestDto;
import at.fhtw.mse.awt.twentyone.dtos.move.MoveDto;
import at.fhtw.mse.awt.twentyone.enums.MoveType;
import at.fhtw.mse.awt.twentyone.interfaces.MoveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MoveController.class)
class MoveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoveService moveService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /game-sessions/{sessionId}/moves → 200 + JSON-Liste mit allen Feldern")
    void whenGetMoves_thenReturnsMoveList() throws Exception {
        // Arrange
        Long sessionId = 42L;
        LocalDateTime time1 = LocalDateTime.of(2025, 6, 1, 12, 0);
        LocalDateTime time2 = LocalDateTime.of(2025, 6, 1, 12, 5);

        MoveDto m1 = new MoveDto(1L, sessionId, MoveType.HIT,   time1);
        MoveDto m2 = new MoveDto(2L, sessionId, MoveType.STAND, time2);
        List<MoveDto> mockList = Arrays.asList(m1, m2);

        when(moveService.getMovesForSession(sessionId)).thenReturn(mockList);

        // Act & Assert
        DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        mockMvc.perform(get("/game-sessions/{sessionId}/moves", sessionId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(mockList.size()))
                .andExpect(jsonPath("$[0].id").value(m1.getId()))
                .andExpect(jsonPath("$[0].gameSessionId").value(m1.getGameSessionId()))
                .andExpect(jsonPath("$[0].moveType").value(m1.getMoveType().toString()))
                .andExpect(jsonPath("$[0].occuredAt").value(ISO.format(time1)))
                .andExpect(jsonPath("$[1].id").value(m2.getId()))
                .andExpect(jsonPath("$[1].gameSessionId").value(m2.getGameSessionId()))
                .andExpect(jsonPath("$[1].moveType").value(m2.getMoveType().toString()))
                .andExpect(jsonPath("$[1].occuredAt").value(ISO.format(time2)));

        verify(moveService, times(1)).getMovesForSession(sessionId);
    }

    @Test
    @DisplayName("POST /game-sessions/{sessionId}/moves → 201 + JSON-Objekt mit allen Feldern")
    void whenCreateMove_thenReturnsCreatedMove() throws Exception {
        // Arrange
        Long sessionId = 42L;
        LocalDateTime now = LocalDateTime.of(2025, 6, 11, 15, 30);

        MoveCreationRequestDto reqDto = new MoveCreationRequestDto(MoveType.HIT);
        MoveDto created = new MoveDto(99L, sessionId, MoveType.HIT, now);

        when(moveService.createMove(eq(sessionId), any(MoveCreationRequestDto.class)))
                .thenReturn(created);

        // Act & Assert
        DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        mockMvc.perform(post("/game-sessions/{sessionId}/moves", sessionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.gameSessionId").value(created.getGameSessionId()))
                .andExpect(jsonPath("$.moveType").value(created.getMoveType().toString()))
                .andExpect(jsonPath("$.occuredAt").value(ISO.format(now)));

        verify(moveService, times(1)).createMove(eq(sessionId), any(MoveCreationRequestDto.class));
    }
}
