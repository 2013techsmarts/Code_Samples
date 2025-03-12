package co.smarttechie.websocket;

import co.smarttechie.model.Card;
import co.smarttechie.model.WebSocketMessage;
import co.smarttechie.service.CardService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class CardWebSocketController {
    private final CardService cardService;

    public CardWebSocketController(CardService cardService) {
        this.cardService = cardService;
    }

    @MessageMapping("/cards.get")
    @SendTo("/topic/cards")
    public WebSocketMessage getCards(WebSocketMessage message) {
        if (message.payload() instanceof Map<?, ?> payload) {
            Long boardId = Long.valueOf(payload.get("boardId").toString());
            List<Card> cards = cardService.getCardsByBoardId(boardId);
            return new WebSocketMessage("CARDS_LOADED", cards);
        }
        throw new IllegalArgumentException("Invalid message payload");
    }

    @MessageMapping("/card.create")
    @SendTo("/topic/cards")
    public WebSocketMessage createCard(WebSocketMessage message) {
        if (message.payload() instanceof Map<?, ?> payload) {
            Long boardId = Long.valueOf(payload.get("boardId").toString());
            String title = (String) payload.get("title");
            String description = (String) payload.get("description");
            Integer position = (Integer) payload.get("position");
            
            Card createdCard = cardService.createCard(boardId, title, description, position);
            return new WebSocketMessage("CARD_CREATED", createdCard);
        }
        throw new IllegalArgumentException("Invalid message payload");
    }

    @MessageMapping("/card.move")
    @SendTo("/topic/cards")
    public WebSocketMessage moveCard(WebSocketMessage message) {
        if (message.payload() instanceof Map<?, ?> payload) {
            Long cardId = Long.valueOf(payload.get("cardId").toString());
            Long newBoardId = Long.valueOf(payload.get("newBoardId").toString());
            Integer newPosition = (Integer) payload.get("newPosition");
            
            Card movedCard = cardService.moveCard(cardId, newBoardId, newPosition);
            return new WebSocketMessage("CARD_MOVED", movedCard);
        }
        throw new IllegalArgumentException("Invalid message payload");
    }

    @MessageMapping("/card.update")
    @SendTo("/topic/cards")
    public WebSocketMessage updateCard(WebSocketMessage message) {
        if (message.payload() instanceof Map<?, ?> payload) {
            Long cardId = Long.valueOf(payload.get("cardId").toString());
            String title = (String) payload.get("title");
            String description = (String) payload.get("description");
            
            Card updatedCard = cardService.updateCard(cardId, title, description);
            return new WebSocketMessage("CARD_UPDATED", updatedCard);
        }
        throw new IllegalArgumentException("Invalid message payload");
    }

    @MessageMapping("/card.delete")
    @SendTo("/topic/cards")
    public WebSocketMessage deleteCard(WebSocketMessage message) {
        if (message.payload() instanceof Long cardId) {
            cardService.deleteCard(cardId);
            return new WebSocketMessage("CARD_DELETED", cardId);
        }
        throw new IllegalArgumentException("Invalid message payload");
    }
} 