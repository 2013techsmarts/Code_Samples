package co.smarttechie.service;

import co.smarttechie.exception.ResourceNotFoundException;
import co.smarttechie.repository.BoardRepository;
import co.smarttechie.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CardService {
    private final CardRepository cardRepository;
    private final BoardRepository boardRepository;

    public CardService(CardRepository cardRepository, BoardRepository boardRepository) {
        this.cardRepository = cardRepository;
        this.boardRepository = boardRepository;
    }

    public List<co.smarttechie.model.Card> getCardsByBoardId(Long boardId) {
        if (!boardRepository.existsById(boardId)) {
            throw new ResourceNotFoundException("Board not found with id: " + boardId);
        }
        return cardRepository.findByBoardIdOrderByPosition(boardId).stream()
            .map(co.smarttechie.model.Card::fromEntity)
            .toList();
    }

    public co.smarttechie.model.Card createCard(Long boardId, String title, String description, Integer position) {
        co.smarttechie.entity.Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));
        
        co.smarttechie.entity.Card card = new co.smarttechie.entity.Card(title, description, position, board);
        return co.smarttechie.model.Card.fromEntity(cardRepository.save(card));
    }

    public co.smarttechie.model.Card updateCard(Long id, String title, String description) {
        co.smarttechie.entity.Card existingCard = cardRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + id));
        
        co.smarttechie.entity.Card updatedCard = new co.smarttechie.entity.Card(
            existingCard.getId(),
            title != null ? title : existingCard.getTitle(),
            description != null ? description : existingCard.getDescription(),
            existingCard.getPosition(),
            existingCard.getBoard(),
            existingCard.getCreatedAt(),
            LocalDateTime.now()
        );
        
        return co.smarttechie.model.Card.fromEntity(cardRepository.save(updatedCard));
    }

    public co.smarttechie.model.Card moveCard(Long cardId, Long newBoardId, Integer newPosition) {
        co.smarttechie.entity.Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + cardId));
        co.smarttechie.entity.Board newBoard = boardRepository.findById(newBoardId)
            .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + newBoardId));

        co.smarttechie.entity.Card movedCard = new co.smarttechie.entity.Card(
            card.getId(),
            card.getTitle(),
            card.getDescription(),
            newPosition,
            newBoard,
            card.getCreatedAt(),
            LocalDateTime.now()
        );
        
        return co.smarttechie.model.Card.fromEntity(cardRepository.save(movedCard));
    }

    public void deleteCard(Long id) {
        if (!cardRepository.existsById(id)) {
            throw new ResourceNotFoundException("Card not found with id: " + id);
        }
        cardRepository.deleteById(id);
    }
} 