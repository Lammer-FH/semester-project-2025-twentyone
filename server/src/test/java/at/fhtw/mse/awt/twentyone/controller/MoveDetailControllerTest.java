package at.fhtw.mse.awt.twentyone.controller;

import at.fhtw.mse.awt.twentyone.dtos.move.MoveDto;
import at.fhtw.mse.awt.twentyone.enums.MoveType;
import at.fhtw.mse.awt.twentyone.interfaces.MoveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MoveDetailController.class)
class MoveDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MoveService moveService;

    @Test
    void shouldUpdateMove() throws Exception {
        // Arrange
        Long id = 1L;
        MoveDto moveDto = new MoveDto(id, 2L, MoveType.HIT, LocalDateTime.of(2025, 6, 11, 16, 0));
        when(moveService.updateMove(eq(id), eq(moveDto))).thenReturn(moveDto);

        // Act & Assert
        mockMvc.perform(put("/moves/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(moveDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.gameSessionId").value(2))
                .andExpect(jsonPath("$.moveType").value(MoveType.HIT.toString()));
    }

    @Test
    void shouldDeleteMove_returnInternalServerError() throws Exception {
        mockMvc.perform(delete("/moves/{id}", 1L))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Request method 'DELETE' is not supported"));
    }
}
