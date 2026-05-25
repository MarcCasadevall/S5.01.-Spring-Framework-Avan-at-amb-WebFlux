package com.blackjack.blackjack_api.controller;

import com.blackjack.blackjack_api.dto.PlayerDTO;
import com.blackjack.blackjack_api.model.Player;
import com.blackjack.blackjack_api.model.Ranking;
import com.blackjack.blackjack_api.service.PlayerService;
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
import java.util.List;

@RestController
@RequestMapping("/player")
@AllArgsConstructor
@Tag(name = "Player", description = "Endpoints for managing players")
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    @Operation(summary = "Create a new player", description = "Creates a new player with initial balance")
    @ApiResponse(responseCode = "201", description = "Player created successfully")
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody CreatePlayerRequest request) {
        Player player = playerService.createPlayer(request.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(player));
    }

    @GetMapping("/{playerId}")
    @Operation(summary = "Get player by ID", description = "Retrieves a specific player by their ID")
    @ApiResponse(responseCode = "200", description = "Player found")
    @ApiResponse(responseCode = "404", description = "Player not found")
    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable @Parameter(description = "Player ID") Long playerId) {
        Player player = playerService.getPlayerById(playerId);
        return ResponseEntity.ok(convertToDTO(player));
    }

    @PutMapping("/{playerId}")
    @Operation(summary = "Update player username", description = "Updates the username of an existing player")
    @ApiResponse(responseCode = "200", description = "Player updated successfully")
    @ApiResponse(responseCode = "404", description = "Player not found")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable @Parameter(description = "Player ID") Long playerId,
                                                  @RequestBody UpdatePlayerRequest request) {
        Player player = playerService.updatePlayer(playerId, request.getNewUsername());
        return ResponseEntity.ok(convertToDTO(player));
    }

    @GetMapping("/ranking")
    @Operation(summary = "Get player ranking", description = "Retrieves all players ranked by their wins and games played")
    @ApiResponse(responseCode = "200", description = "Ranking retrieved successfully")
    public ResponseEntity<List<Ranking>> getRanking() {
        List<Ranking> rankings = playerService.getRanking();
        return ResponseEntity.ok(rankings);
    }

    private PlayerDTO convertToDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setUsername(player.getUsername());
        dto.setBalance(player.getBalance());
        dto.setTotalGamesPlayed(player.getTotalGamesPlayed());
        dto.setTotalWins(player.getTotalWins());
        return dto;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatePlayerRequest {
        private String username;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdatePlayerRequest {
        private String newUsername;
    }
}