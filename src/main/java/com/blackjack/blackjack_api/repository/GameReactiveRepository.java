package com.blackjack.blackjack_api.repository;

import com.blackjack.blackjack_api.model.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface GameReactiveRepository extends ReactiveMongoRepository<Game, String> {
    Mono<Game> findById(String id);
    Flux<Game> findByPlayerIdsContaining(Long playerId);
}