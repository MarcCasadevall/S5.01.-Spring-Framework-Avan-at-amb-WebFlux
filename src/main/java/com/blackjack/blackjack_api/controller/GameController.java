package com.blackjack.blackjack_api.controller;

import com.blackjack.blackjack_api.dto.GameDTO;
import com.blackjack.blackjack_api.dto.PlayRequestDTO;
import com.blackjack.blackjack_api.model.Game;
import com.blackjack.blackjack_api.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Game", description = "Endpoints for managing Blackjack games")
public class GameController {
    private final GameService gameService;

    @PostMapping("/new")
    @Operation(summary = "Create a new game", description = "Creates a new Blackjack game with specified players")
    @ApiResponse(responseCode = "201", description = "Game created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    public Mono<ResponseEntity<GameDTO>> createGame(@RequestBody CreateGameRequest request) {
        return gameService.createGame(request.getPlayerIds(), request.getDealerId())
                .map(this::convertToDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get game by ID", description = "Retrieves a specific game by its ID")
    @ApiResponse(responseCode = "200", description = "Game found")
    @ApiResponse(responseCode = "404", description = "Game not found")
    public Mono<ResponseEntity<GameDTO>> getGame(@PathVariable @Parameter(description = "Game ID") String id) {
        return gameService.getGameById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/{id}/play")
    @Operation(summary = "Execute a play action", description = "Executes a play action (HIT or STAND) in an active game")
    @ApiResponse(responseCode = "200", description = "Action executed successfully")
    @ApiResponse(responseCode = "404", description = "Game not found")
    public Mono<ResponseEntity<GameDTO>> playTurn(@PathVariable @Parameter(description = "Game ID") String id,
                                                  @RequestBody PlayRequestDTO request) {
        return gameService.playTurn(id, request.getPlayerId(), request.getAction())
                .map(this::convertToDTO)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete a game", description = "Deletes a game by its ID")
    @ApiResponse(responseCode = "204", description = "Game deleted successfully")
    @ApiResponse(responseCode = "404", description = "Game not found")
    public Mono<ResponseEntity<Void>> deleteGame(@PathVariable @Parameter(description = "Game ID") String id) {
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