package com.blackjack.blackjack_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    private String id;

    private List<Long> playerIds;

    private Long dealerId;

    private GameStatus gameStatus;

    private Map<Long, Hand> playerHands;

    private Hand dealerHand;

    private Map<Long, String> results;

    private LocalDateTime createdAt;

    private LocalDateTime finishedAt;

    public Game(List<Long> playerIds, Long dealerId) {
        this.playerIds = playerIds;
        this.dealerId = dealerId;
        this.gameStatus = GameStatus.ACTIVE;
        this.playerHands = new HashMap<>();
        this.results = new HashMap<>();
        this.createdAt = LocalDateTime.now();
    }

    public void finishGame() {
        this.gameStatus = GameStatus.FINISHED;
        this.finishedAt = LocalDateTime.now();
    }

    public enum GameStatus {
        ACTIVE,
        FINISHED,
        ABANDONED
    }
}