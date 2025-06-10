package at.fhtw.mse.awt.twentyone.services;

import at.fhtw.mse.awt.twentyone.dtos.move.MoveCreationRequestDto;
import at.fhtw.mse.awt.twentyone.dtos.move.MoveDto;
import at.fhtw.mse.awt.twentyone.entities.GameSession;
import at.fhtw.mse.awt.twentyone.entities.Move;
import at.fhtw.mse.awt.twentyone.interfaces.MoveService;
import at.fhtw.mse.awt.twentyone.repositories.GameSessionRepository;
import at.fhtw.mse.awt.twentyone.repositories.MoveRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoveServiceImpl implements MoveService {

    private final MoveRepository moveRepository;
    private final GameSessionRepository gameSessionRepository;

    public MoveServiceImpl(MoveRepository moveRepository, GameSessionRepository gameSessionRepository) {
        this.moveRepository = moveRepository;
        this.gameSessionRepository = gameSessionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MoveDto> getMovesForSession(Long sessionId) {
        if (!gameSessionRepository.existsById(sessionId)) {
            throw new EntityNotFoundException("GameSession not found with id: " + sessionId);
        }
        return moveRepository.findByGameSession_Id(sessionId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MoveDto createMove(Long sessionId, MoveCreationRequestDto requestDto) {
        GameSession gameSession = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("GameSession not found with id: " + sessionId));

        Move newMove = new Move(
                null,
                gameSession,
                requestDto.getMoveType(),
                LocalDateTime.now()
        );

        Move savedMove = moveRepository.save(newMove);
        return mapToDto(savedMove);
    }

    @Override
    @Transactional
    public MoveDto updateMove(Long moveId, MoveDto moveDto) {
        Move existingMove = moveRepository.findById(moveId)
                .orElseThrow(() -> new EntityNotFoundException("Move not found with id: " + moveId));

        existingMove.setMoveType(moveDto.getMoveType());
        Move updatedMove = moveRepository.save(existingMove);
        return mapToDto(updatedMove);
    }


    private MoveDto mapToDto(Move move) {
        if (move == null) {
            return null;
        }

        return new MoveDto(
                move.getMoveId(),
                move.getGameSession().getId(),
                move.getMoveType(),
                move.getOccuredAt()
        );
    }
}