package co.smarttechie.service;

import co.smarttechie.exception.ResourceNotFoundException;
import co.smarttechie.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<co.smarttechie.model.Board> getAllBoards() {
        return boardRepository.findAll().stream()
            .map(co.smarttechie.model.Board::fromEntity)
            .toList();
    }

    public co.smarttechie.model.Board createBoard(String title, String description) {
        co.smarttechie.entity.Board board = new co.smarttechie.entity.Board(title, description);
        return co.smarttechie.model.Board.fromEntity(boardRepository.save(board));
    }

    public co.smarttechie.model.Board updateBoard(Long id, String title, String description) {
        co.smarttechie.entity.Board existingBoard = boardRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + id));
        
        co.smarttechie.entity.Board updatedBoard = new co.smarttechie.entity.Board(
            existingBoard.getId(),
            title != null ? title : existingBoard.getTitle(),
            description != null ? description : existingBoard.getDescription(),
            existingBoard.getCreatedAt(),
            LocalDateTime.now()
        );
        
        return co.smarttechie.model.Board.fromEntity(boardRepository.save(updatedBoard));
    }

    public void deleteBoard(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new ResourceNotFoundException("Board not found with id: " + id);
        }
        boardRepository.deleteById(id);
    }
} 