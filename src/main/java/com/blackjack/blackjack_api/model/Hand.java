package com.blackjack.blackjack_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hand {
    private Long playerId;
    private List<Card> cards;
    private Integer score;

    public Hand(Long playerId) {
        this.playerId = playerId;
        this.cards = new ArrayList<>();
        this.score = 0;
    }

    public void addCard(Card card) {
        this.cards.add(card);
        calculateScore();
    }

    public void calculateScore() {
        int total = 0;
        int aces = 0;

        for (Card card : cards) {
            total += card.getValue();
            if (card.getRank() == 1) {
                aces++;
            }
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        this.score = total;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && score == 21;
    }

    public boolean isBust() {
        return score > 21;
    }
}