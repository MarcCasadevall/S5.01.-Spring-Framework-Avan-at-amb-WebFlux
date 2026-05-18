package com.blackjack.blackjack_api.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long playerId) {
        super("Player with id " + playerId + " not found");
    }

    public PlayerNotFoundException(String username) {
        super("Player with username " + username + " not found");
    }
}