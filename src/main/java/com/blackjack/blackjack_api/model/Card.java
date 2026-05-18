package com.blackjack.blackjack_api.model;

public enum Card {
    ACE_HEARTS(1, "A♥", 11),
    TWO_HEARTS(2, "2♥", 2),
    THREE_HEARTS(3, "3♥", 3),
    FOUR_HEARTS(4, "4♥", 4),
    FIVE_HEARTS(5, "5♥", 5),
    SIX_HEARTS(6, "6♥", 6),
    SEVEN_HEARTS(7, "7♥", 7),
    EIGHT_HEARTS(8, "8♥", 8),
    NINE_HEARTS(9, "9♥", 9),
    TEN_HEARTS(10, "10♥", 10),
    JACK_HEARTS(11, "J♥", 10),
    QUEEN_HEARTS(12, "Q♥", 10),
    KING_HEARTS(13, "K♥", 10),

    ACE_DIAMONDS(1, "A♦", 11),
    TWO_DIAMONDS(2, "2♦", 2),
    THREE_DIAMONDS(3, "3♦", 3),
    FOUR_DIAMONDS(4, "4♦", 4),
    FIVE_DIAMONDS(5, "5♦", 5),
    SIX_DIAMONDS(6, "6♦", 6),
    SEVEN_DIAMONDS(7, "7♦", 7),
    EIGHT_DIAMONDS(8, "8♦", 8),
    NINE_DIAMONDS(9, "9♦", 9),
    TEN_DIAMONDS(10, "10♦", 10),
    JACK_DIAMONDS(11, "J♦", 10),
    QUEEN_DIAMONDS(12, "Q♦", 10),
    KING_DIAMONDS(13, "K♦", 10),

    ACE_CLUBS(1, "A♣", 11),
    TWO_CLUBS(2, "2♣", 2),
    THREE_CLUBS(3, "3♣", 3),
    FOUR_CLUBS(4, "4♣", 4),
    FIVE_CLUBS(5, "5♣", 5),
    SIX_CLUBS(6, "6♣", 6),
    SEVEN_CLUBS(7, "7♣", 7),
    EIGHT_CLUBS(8, "8♣", 8),
    NINE_CLUBS(9, "9♣", 9),
    TEN_CLUBS(10, "10♣", 10),
    JACK_CLUBS(11, "J♣", 10),
    QUEEN_CLUBS(12, "Q♣", 10),
    KING_CLUBS(13, "K♣", 10),

    ACE_SPADES(1, "A♠", 11),
    TWO_SPADES(2, "2♠", 2),
    THREE_SPADES(3, "3♠", 3),
    FOUR_SPADES(4, "4♠", 4),
    FIVE_SPADES(5, "5♠", 5),
    SIX_SPADES(6, "6♠", 6),
    SEVEN_SPADES(7, "7♠", 7),
    EIGHT_SPADES(8, "8♠", 8),
    NINE_SPADES(9, "9♠", 9),
    TEN_SPADES(10, "10♠", 10),
    JACK_SPADES(11, "J♠", 10),
    QUEEN_SPADES(12, "Q♠", 10),
    KING_SPADES(13, "K♠", 10);

    private final int rank;
    private final String display;
    private final int value;

    Card(int rank, String display, int value) {
        this.rank = rank;
        this.display = display;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getDisplay() {
        return display;
    }

    public int getRank() {
        return rank;
    }
}