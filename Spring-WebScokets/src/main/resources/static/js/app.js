let stompClient = null;
let boardModal = null;
let cardModal = null;
let sortables = new Map();

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/boards', function(message) {
            const response = JSON.parse(message.body);
            handleWebSocketMessage(response);
        });
        stompClient.subscribe('/topic/cards', function(message) {
            const response = JSON.parse(message.body);
            handleCardWebSocketMessage(response);
        });
        // Request initial data
        requestInitialData();
    });
}

function requestInitialData() {
    // Request boards data
    stompClient.send("/app/boards.get", {}, JSON.stringify({
        type: 'GET_BOARDS'
    }));
}

function handleWebSocketMessage(message) {
    switch(message.type) {
        case 'BOARD_CREATED':
            addBoardToUI(message.payload);
            break;
        case 'BOARD_UPDATED':
            updateBoardInUI(message.payload);
            break;
        case 'BOARD_DELETED':
            removeBoardFromUI(message.payload);
            break;
        case 'BOARDS_LOADED':
            // Handle initial boards data
            const boardsContainer = document.getElementById('boards');
            boardsContainer.innerHTML = '';
            message.payload.forEach(board => {
                addBoardToUI(board);
                // Request cards for this board
                stompClient.send("/app/cards.get", {}, JSON.stringify({
                    type: 'GET_CARDS',
                    payload: {
                        boardId: board.id
                    }
                }));
            });
            break;
    }
}

function handleCardWebSocketMessage(message) {
    switch(message.type) {
        case 'CARD_CREATED':
            addCardToUI(message.payload);
            break;
        case 'CARD_MOVED':
            updateCardPosition(message.payload);
            break;
        case 'CARD_UPDATED':
            updateCardInUI(message.payload);
            break;
        case 'CARD_DELETED':
            removeCardFromUI(message.payload);
            break;
        case 'CARDS_LOADED':
            // Handle initial cards data
            message.payload.forEach(card => addCardToUI(card));
            break;
    }
}

function showCreateBoardModal() {
    if (!boardModal) {
        boardModal = new bootstrap.Modal(document.getElementById('createBoardModal'));
    }
    boardModal.show();
}

function showCreateCardModal(boardId) {
    if (!cardModal) {
        cardModal = new bootstrap.Modal(document.getElementById('createCardModal'));
    }
    document.getElementById('boardId').value = boardId;
    cardModal.show();
}

function createBoard() {
    const title = document.getElementById('boardTitle').value;
    const description = document.getElementById('boardDescription').value;
    
    const message = {
        type: 'CREATE_BOARD',
        payload: {
            title: title,
            description: description
        }
    };
    
    stompClient.send("/app/board.create", {}, JSON.stringify(message));
    boardModal.hide();
    document.getElementById('createBoardForm').reset();
}

function createCard() {
    const boardId = document.getElementById('boardId').value;
    const title = document.getElementById('cardTitle').value;
    const description = document.getElementById('cardDescription').value;
    const boardElement = document.getElementById(`board-${boardId}`);
    const position = boardElement ? boardElement.querySelectorAll('.card').length : 0;
    
    const message = {
        type: 'CREATE_CARD',
        payload: {
            boardId: parseInt(boardId),
            title: title,
            description: description,
            position: position
        }
    };
    
    stompClient.send("/app/card.create", {}, JSON.stringify(message));
    cardModal.hide();
    document.getElementById('createCardForm').reset();
}

function addBoardToUI(board) {
    const boardsContainer = document.getElementById('boards');
    if (!document.getElementById(`board-${board.id}`)) {
        const boardElement = document.createElement('div');
        boardElement.className = 'board-container';
        boardElement.id = `board-${board.id}`;
        
        boardElement.innerHTML = `
            <div class="board-content">
                <div class="d-flex justify-content-between align-items-center">
                    <h3 class="mb-0">${board.title}</h3>
                    <button class="btn btn-primary btn-sm" onclick="showCreateCardModal(${board.id})">
                        + Add Card
                    </button>
                </div>
                <div class="board-cards" id="board-cards-${board.id}"></div>
            </div>
        `;
        
        boardsContainer.appendChild(boardElement);
        
        // Initialize Sortable on the board
        const boardCardsElement = boardElement.querySelector('.board-cards');
        const sortable = new Sortable(boardCardsElement, {
            group: 'cards',
            animation: 150,
            ghostClass: 'sortable-ghost',
            onEnd: function(evt) {
                const cardId = parseInt(evt.item.getAttribute('data-card-id'));
                const newBoardId = parseInt(evt.to.id.split('-')[2]);
                const newPosition = evt.newIndex;
                
                const message = {
                    type: 'MOVE_CARD',
                    payload: {
                        cardId: cardId,
                        newBoardId: newBoardId,
                        newPosition: newPosition
                    }
                };
                stompClient.send("/app/card.move", {}, JSON.stringify(message));
            }
        });
        sortables.set(board.id, sortable);
    }
}

function addCardToUI(card) {
    const boardCards = document.getElementById(`board-cards-${card.boardId}`);
    if (boardCards) {
        const cardElement = document.createElement('div');
        cardElement.className = 'card';
        cardElement.id = `card-${card.id}`;
        cardElement.setAttribute('data-card-id', card.id);
        cardElement.setAttribute('data-board-id', card.boardId);
        cardElement.setAttribute('data-position', card.position);
        
        cardElement.innerHTML = `
            <h6 class="card-title">${card.title}</h6>
            <p class="card-text small text-muted">${card.description || ''}</p>
        `;
        boardCards.appendChild(cardElement);
    }
}

function updateCardPosition(card) {
    const cardElement = document.getElementById(`card-${card.id}`);
    if (!cardElement) return;
    
    const newBoardCards = document.getElementById(`board-cards-${card.boardId}`);
    if (!newBoardCards) return;
    
    cardElement.setAttribute('data-board-id', card.boardId);
    cardElement.setAttribute('data-position', card.position);
    
    const cards = Array.from(newBoardCards.children);
    if (card.position >= cards.length) {
        newBoardCards.appendChild(cardElement);
    } else {
        newBoardCards.insertBefore(cardElement, cards[card.position]);
    }
}

function updateBoardInUI(board) {
    const boardElement = document.getElementById(`board-${board.id}`);
    if (boardElement) {
        const existingCards = boardElement.querySelectorAll('.card');
        existingCards.forEach(card => {
            if (card.querySelector('.card-title').textContent === board.title) {
                card.querySelector('.card-title').textContent = board.title;
            }
        });
    }
}

// Connect when the page loads
window.onload = connect; 