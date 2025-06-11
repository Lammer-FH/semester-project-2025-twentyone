package at.fhtw.mse.awt.twentyone.services;

import at.fhtw.mse.awt.twentyone.dtos.gameSession.GameSessionCreationRequestDto;
import at.fhtw.mse.awt.twentyone.dtos.gameSession.GameSessionDto;
import at.fhtw.mse.awt.twentyone.dtos.gameSession.GameSessionUpdateRequestDto;
import at.fhtw.mse.awt.twentyone.entities.GameSession;
import at.fhtw.mse.awt.twentyone.entities.Player;
import at.fhtw.mse.awt.twentyone.enums.GameSessionStatus;
import at.fhtw.mse.awt.twentyone.repositories.GameSessionRepository;
import at.fhtw.mse.awt.twentyone.repositories.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameSessionServiceImplTest {

    @Mock
    private GameSessionRepository gameSessionRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private GameSessionServiceImpl gameSessionService;

    private Player testPlayer;
    private GameSession testGameSession;

    @BeforeEach
    void setUp() {
        testPlayer = new Player(10L, "tester", "Test Player", "hash");
        testGameSession = new GameSession(1L, testPlayer, GameSessionStatus.ACTIVE, LocalDateTime.now(), null, null);
    }

    @Test
    void testGetGameSession_shouldReturnDto_whenSessionExists() {
        // Arrange
        when(gameSessionRepository.findById(1L)).thenReturn(Optional.of(testGameSession));

        // Act
        GameSessionDto dto = gameSessionService.getGameSession(1L);

        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getPlayerId()).isEqualTo(testPlayer.getPlayerId());
        assertThat(dto.getStatus()).isEqualTo(GameSessionStatus.ACTIVE);
    }

    @Test
    void testGetGameSession_shouldThrowException_whenSessionNotFound() {
        // Arrange
        when(gameSessionRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> gameSessionService.getGameSession(999L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("GameSession not found with id: 999");
    }

    @Test
    void testCreateGameSession_shouldCreateAndReturnDto() {
        // Arrange
        GameSessionCreationRequestDto requestDto = new GameSessionCreationRequestDto(testPlayer.getPlayerId());
        when(playerRepository.findById(testPlayer.getPlayerId())).thenReturn(Optional.of(testPlayer));
        when(gameSessionRepository.save(any(GameSession.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        GameSessionDto createdDto = gameSessionService.createGameSession(requestDto);

        // Assert
        assertThat(createdDto).isNotNull();
        assertThat(createdDto.getPlayerId()).isEqualTo(testPlayer.getPlayerId());
        assertThat(createdDto.getStatus()).isEqualTo(GameSessionStatus.ACTIVE);
        verify(gameSessionRepository, times(1)).save(any(GameSession.class));
    }

    @Test
    void testCreateGameSession_shouldThrowException_whenPlayerNotFound() {
        // Arrange
        GameSessionCreationRequestDto requestDto = new GameSessionCreationRequestDto(999L);
        when(playerRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> gameSessionService.createGameSession(requestDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Player not found with id: 999");
        verify(gameSessionRepository, never()).save(any());
    }

    @Test
    void testUpdateGameSession_shouldUpdateStatusAndReturnDto() {
        // Arrange
        GameSessionUpdateRequestDto requestDto = new GameSessionUpdateRequestDto(GameSessionStatus.ENDED);
        when(gameSessionRepository.findById(1L)).thenReturn(Optional.of(testGameSession));
        when(gameSessionRepository.save(any(GameSession.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        GameSessionDto updatedDto = gameSessionService.updateGameSession(1L, requestDto);

        // Assert
        assertThat(updatedDto).isNotNull();
        assertThat(updatedDto.getStatus()).isEqualTo(GameSessionStatus.ENDED);
        assertThat(updatedDto.getEndTime()).isNotNull();
        verify(gameSessionRepository, times(1)).save(testGameSession);
    }

    @Test
    void testDeleteGameSession_shouldCallDeleteById_whenSessionExists() {
        // Arrange
        when(gameSessionRepository.existsById(1L)).thenReturn(true);
        doNothing().when(gameSessionRepository).deleteById(1L);

        // Act
        gameSessionService.deleteGameSession(1L);

        // Assert
        verify(gameSessionRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteGameSession_shouldThrowException_whenSessionNotFound() {
        // Arrange
        when(gameSessionRepository.existsById(999L)).thenReturn(false);

        // Act & Assert
        assertThatThrownBy(() -> gameSessionService.deleteGameSession(999L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("GameSession not found with id: 999");
        verify(gameSessionRepository, never()).deleteById(any());
    }
}
