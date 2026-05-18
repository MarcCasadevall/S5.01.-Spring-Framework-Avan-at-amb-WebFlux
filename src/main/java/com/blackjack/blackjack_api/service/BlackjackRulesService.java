package com.blackjack.blackjack_api.service;

import com.blackjack.blackjack_api.model.Card;
import com.blackjack.blackjack_api.model.Hand;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class BlackjackRulesService {
    private static final List<Card> DECK = Arrays.asList(Card.values());
    private final Random random = new Random();

    public Card getRandomCard() {
        return DECK.get(random.nextInt(DECK.size()));
    }

    public void dealCard(Hand hand) {
        Card card = getRandomCard();
        hand.addCard(card);
    }

    public boolean isBlackjack(Hand hand) {
        return hand.isBlackjack();
    }

    public boolean isBust(Hand hand) {
        return hand.isBust();
    }

    public int getHandScore(Hand hand) {
        return hand.getScore();
    }

    public String determineWinner(Hand playerHand, Hand dealerHand) {
        int playerScore = playerHand.getScore();
        int dealerScore = dealerHand.getScore();

        if (playerHand.isBust()) {
            return "DEALER_WINS";
        }

        if (dealerHand.isBust()) {
            return "PLAYER_WINS";
        }

        if (playerScore > dealerScore) {
            return "PLAYER_WINS";
        } else if (dealerScore > playerScore) {
            return "DEALER_WINS";
        } else {
            return "PUSH";
        }
    }

    public boolean dealerShouldHit(Hand dealerHand) {
        return dealerHand.getScore() < 17;
    }
}