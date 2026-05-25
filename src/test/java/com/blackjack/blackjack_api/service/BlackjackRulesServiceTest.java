package com.blackjack.blackjack_api.service;

import com.blackjack.blackjack_api.model.Card;
import com.blackjack.blackjack_api.model.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BlackjackRulesServiceTest {
    private BlackjackRulesService rulesService;

    @BeforeEach
    void setUp() {
        rulesService = new BlackjackRulesService();
    }

    @Test
    void testDealCard() {
        Hand hand = new Hand(1L);
        rulesService.dealCard(hand);

        assertEquals(1, hand.getCards().size());
        assertNotNull(hand.getScore());
    }

    @Test
    void testIsBlackjack() {
        Hand hand = new Hand(1L);
        hand.addCard(Card.ACE_HEARTS);
        hand.addCard(Card.KING_HEARTS);

        assertTrue(rulesService.isBlackjack(hand));
    }

    @Test
    void testIsBust() {
        Hand hand = new Hand(1L);
        hand.addCard(Card.KING_HEARTS);
        hand.addCard(Card.QUEEN_HEARTS);
        hand.addCard(Card.TEN_HEARTS);

        assertTrue(rulesService.isBust(hand));
    }

    @Test
    void testDetermineWinnerPlayerWins() {
        Hand playerHand = new Hand(1L);
        playerHand.addCard(Card.KING_HEARTS);
        playerHand.addCard(Card.QUEEN_HEARTS);

        Hand dealerHand = new Hand(0L);
        dealerHand.addCard(Card.KING_HEARTS);
        dealerHand.addCard(Card.NINE_HEARTS);

        String result = rulesService.determineWinner(playerHand, dealerHand);
        assertEquals("PLAYER_WINS", result);
    }

    @Test
    void testDetermineWinnerDealerWins() {
        Hand playerHand = new Hand(1L);
        playerHand.addCard(Card.KING_HEARTS);
        playerHand.addCard(Card.FIVE_HEARTS);

        Hand dealerHand = new Hand(0L);
        dealerHand.addCard(Card.KING_HEARTS);
        dealerHand.addCard(Card.NINE_HEARTS);

        String result = rulesService.determineWinner(playerHand, dealerHand);
        assertEquals("DEALER_WINS", result);
    }

    @Test
    void testDealerShouldHit() {
        Hand dealerHand = new Hand(0L);
        dealerHand.addCard(Card.SIX_HEARTS);
        dealerHand.addCard(Card.FIVE_HEARTS);

        assertTrue(rulesService.dealerShouldHit(dealerHand));
    }

    @Test
    void testDealerShouldNotHit() {
        Hand dealerHand = new Hand(0L);
        dealerHand.addCard(Card.KING_HEARTS);
        dealerHand.addCard(Card.NINE_HEARTS);

        assertFalse(rulesService.dealerShouldHit(dealerHand));
    }
}