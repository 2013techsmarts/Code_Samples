package co.smarttechie.websocket;

import co.smarttechie.model.Board;
import co.smarttechie.model.WebSocketMessage;
import co.smarttechie.service.BoardService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class BoardWebSocketController {
    private final BoardService boardService;

    public BoardWebSocketController(BoardService boardService) {
        this.boardService = boardService;
    }

    @MessageMapping("/boards.get")
    @SendTo("/topic/boards")
    public WebSocketMessage getBoards(WebSocketMessage message) {
        List<Board> boards = boardService.getAllBoards();
        return new WebSocketMessage("BOARDS_LOADED", boards);
    }

    @MessageMapping("/board.create")
    @SendTo("/topic/boards")
    public WebSocketMessage createBoard(WebSocketMessage message) {
        if (message.payload() instanceof Map<?, ?> payload) {
            String title = (String) payload.get("title");
            String description = (String) payload.get("description");
            Board createdBoard = boardService.createBoard(title, description);
            return new WebSocketMessage("BOARD_CREATED", createdBoard);
        }
        throw new IllegalArgumentException("Invalid message payload");
    }

    @MessageMapping("/board.update")
    @SendTo("/topic/boards")
    public WebSocketMessage updateBoard(WebSocketMessage message) {
        if (message.payload() instanceof Map<?, ?> payload) {
            Long id = Long.valueOf(payload.get("id").toString());
            String title = (String) payload.get("title");
            String description = (String) payload.get("description");
            Board updatedBoard = boardService.updateBoard(id, title, description);
            return new WebSocketMessage("BOARD_UPDATED", updatedBoard);
        }
        throw new IllegalArgumentException("Invalid message payload");
    }

    @MessageMapping("/board.delete")
    @SendTo("/topic/boards")
    public WebSocketMessage deleteBoard(WebSocketMessage message) {
        if (message.payload() instanceof Long boardId) {
            boardService.deleteBoard(boardId);
            return new WebSocketMessage("BOARD_DELETED", boardId);
        }
        throw new IllegalArgumentException("Invalid message payload");
    }
} 