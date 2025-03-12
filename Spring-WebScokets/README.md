# Trello-like Board with WebSockets

A real-time collaborative board application built with Spring Boot and WebSockets, allowing users to create boards and manage cards with drag-and-drop functionality.

## Features

- Real-time updates using WebSocket communication
- Create and manage boards
- Add and manage cards within boards
- Drag and drop cards between boards
- In-memory H2 database for data persistence
- Modern and responsive UI

## Prerequisites

- Java 23 
- Maven 3.6 or higher

## Technology Stack

### Backend
- Spring Boot
- Spring WebSocket
- Spring Data JPA
- H2 Database
- STOMP Protocol

### Frontend
- HTML5
- CSS3
- JavaScript
- Bootstrap 5
- SockJS
- STOMP.js
- Sortable.js

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/2013techsmarts/Code_Samples.git
   cd Spring-WebSockets
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

   Or run the JAR file directly:
   ```bash
   java -jar target/Spring-WebSockets-0.0.1-SNAPSHOT.jar
   ```

4. Access the application:
   - Main application: http://localhost:8380
   - H2 Database Console: http://localhost:8380/h2-console


## Application Structure

```
src/
├── main/
│   ├── java/
│   │   └── co/smarttechie/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── websocket/
│   └── resources/
│       ├── static/
│       │   ├── css/
│       │   │   └── styles.css
│       │   ├── js/
│       │   │   └── app.js
│       │   └── index.html
│       └── application.yml
```

## WebSocket Endpoints

- `/ws` - WebSocket connection endpoint
- `/topic/boards` - Topic for board-related messages
- `/topic/cards` - Topic for card-related messages
- `/app/boards.get` - Get all boards
- `/app/board.create` - Create a new board
- `/app/cards.get` - Get cards for a board
- `/app/card.create` - Create a new card
- `/app/card.move` - Move a card between boards

## Features in Detail

1. **Board Management**
   - Create new boards with title and description
   - Real-time updates when boards are created or modified

2. **Card Management**
   - Create cards within boards
   - Add title and description to cards
   - Drag and drop cards between boards
   - Real-time synchronization of card movements

3. **Real-time Collaboration**
   - All changes are immediately reflected for all connected users
   - WebSocket communication ensures low-latency updates
   - Automatic reconnection handling