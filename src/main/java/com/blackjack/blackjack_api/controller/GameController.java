package com.blackjack.blackjack_api.controller;

import com.blackjack.blackjack_api.dto.GameDTO;
import com.blackjack.blackjack_api.dto.PlayRequestDTO;
import com.blackjack.blackjack_api.model.Game;
import com.blackjack.blackjack_api.service.GameService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/new")
    public Mono<ResponseEntity<GameDTO>> createGame(@RequestBody CreateGameRequest request) {
        return gameService.createGame(request.getPlayerIds(), request.getDealerId())
                .map(this::convertToDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<GameDTO>> getGame(@PathVariable String id) {
        return gameService.getGameById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/{id}/play")
    public Mono<ResponseEntity<GameDTO>> playTurn(@PathVariable String id,
                                                  @RequestBody PlayRequestDTO request) {
        return gameService.playTurn(id, request.getPlayerId(), request.getAction())
                .map(this::convertToDTO)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}/delete")
    public Mono<ResponseEntity<Void>> deleteGame(@PathVariable String id) {
        return gameService.deleteGame(id)
                .map(v -> ResponseEntity.noContent().<Void>build());
    }

    private GameDTO convertToDTO(Game game) {
        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setPlayerIds(game.getPlayerIds());
        dto.setDealerId(game.getDealerId());
        dto.setGameStatus(game.getGameStatus().toString());
        dto.setPlayerScores(game.getPlayerHands().entrySet().stream()
                .collect(java.util.stream.Collectors.toMap(
                        java.util.Map.Entry::getKey,
                        e -> e.getValue().getScore()
                )));
        dto.setDealerScore(game.getDealerHand().getScore());
        dto.setResults(game.getResults());
        dto.setCreatedAt(game.getCreatedAt());
        dto.setFinishedAt(game.getFinishedAt());
        return dto;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateGameRequest {
        private List<Long> playerIds;
        private Long dealerId;
    }
}