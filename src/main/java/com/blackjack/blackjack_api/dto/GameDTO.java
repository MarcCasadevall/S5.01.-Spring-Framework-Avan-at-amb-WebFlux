package com.blackjack.blackjack_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private String id;
    private List<Long> playerIds;
    private Long dealerId;
    private String gameStatus;
    private Map<Long, Integer> playerScores;
    private Integer dealerScore;
    private Map<Long, String> results;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
}