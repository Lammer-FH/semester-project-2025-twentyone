package at.fhtw.mse.awt.twentyone.interfaces;

import at.fhtw.mse.awt.twentyone.dtos.move.MoveCreationRequestDto;
import at.fhtw.mse.awt.twentyone.dtos.move.MoveDto;

import java.util.List;

public interface MoveService {
    List<MoveDto> getMovesForSession(Long sessionId);
    MoveDto createMove(Long sessionId, MoveCreationRequestDto requestDto);
    MoveDto updateMove(Long moveId, MoveDto moveDto);
}