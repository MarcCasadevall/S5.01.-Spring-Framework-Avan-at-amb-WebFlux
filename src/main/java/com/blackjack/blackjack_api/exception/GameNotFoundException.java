package com.blackjack.blackjack_api.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String gameId) {
        super("Game with id " + gameId + " not found");
    }
}