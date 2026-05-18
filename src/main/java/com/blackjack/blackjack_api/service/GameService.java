package com.blackjack.blackjack_api.service;

import com.blackjack.blackjack_api.model.Game;
import com.blackjack.blackjack_api.model.Hand;
import com.blackjack.blackjack_api.repository.GameReactiveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
@AllArgsConstructor
public class GameService {
    private final GameReactiveRepository gameRepository;
    private final BlackjackRulesService rulesService;

    public Mono<Game> createGame(List<Long> playerIds, Long dealerId) {
        Game newGame = new Game(playerIds, dealerId);

        newGame.getPlayerHands().clear();
        for (Long playerId : playerIds) {
            Hand hand = new Hand(playerId);
            rulesService.dealCard(hand);
            rulesService.dealCard(hand);
            newGame.getPlayerHands().put(playerId, hand);
        }

        Hand dealerHand = new Hand(dealerId);
        rulesService.dealCard(dealerHand);
        newGame.setDealerHand(dealerHand);

        return gameRepository.save(newGame);
    }

    public Mono<Game> getGameById(String gameId) {
        return gameRepository.findById(gameId);
    }

    public Mono<Game> playTurn(String gameId, Long playerId, String action) {
        return gameRepository.findById(gameId)
                .flatMap(game -> {
                    Hand playerHand = game.getPlayerHands().get(playerId);

                    if ("HIT".equalsIgnoreCase(action)) {
                        rulesService.dealCard(playerHand);
                    } else if ("STAND".equalsIgnoreCase(action)) {
                        game.getPlayerHands().put(playerId, playerHand);
                    }

                    return gameRepository.save(game);
                });
    }

    public Mono<Game> finishGame(String gameId) {
        return gameRepository.findById(gameId)
                .flatMap(game -> {
                    Hand dealerHand = game.getDealerHand();

                    while (rulesService.dealerShouldHit(dealerHand)) {
                        rulesService.dealCard(dealerHand);
                    }

                    for (Long playerId : game.getPlayerIds()) {
                        Hand playerHand = game.getPlayerHands().get(playerId);
                        String result = rulesService.determineWinner(playerHand, dealerHand);
                        game.getResults().put(playerId, result);
                    }

                    game.finishGame();
                    return gameRepository.save(game);
                });
    }

    public Mono<Void> deleteGame(String gameId) {
        return gameRepository.deleteById(gameId);
    }
}