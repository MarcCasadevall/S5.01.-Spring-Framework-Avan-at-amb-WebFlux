package com.blackjack.blackjack_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {

    private Long playerId;

    private String username;

    private Integer totalWins;

    private Integer totalGamesPlayed;

    private Double winRate;

    public Ranking(Player player) {
        this.playerId = player.getId();
        this.username = player.getUsername();
        this.totalWins = player.getTotalWins();
        this.totalGamesPlayed = player.getTotalGamesPlayed();
        this.winRate = calculateWinRate();
    }

    private Double calculateWinRate() {
        if (totalGamesPlayed == 0) {
            return 0.0;
        }
        return (double) totalWins / totalGamesPlayed * 100;
    }
}