package com.blackjack.blackjack_api.controller;

import com.blackjack.blackjack_api.dto.PlayerDTO;
import com.blackjack.blackjack_api.model.Player;
import com.blackjack.blackjack_api.model.Ranking;
import com.blackjack.blackjack_api.service.PlayerService;
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
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody CreatePlayerRequest request) {
        Player player = playerService.createPlayer(request.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(player));
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable Long playerId) {
        Player player = playerService.getPlayerById(playerId);
        return ResponseEntity.ok(convertToDTO(player));
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long playerId,
                                                  @RequestBody UpdatePlayerRequest request) {
        Player player = playerService.updatePlayer(playerId, request.getNewUsername());
        return ResponseEntity.ok(convertToDTO(player));
    }

    @GetMapping("/ranking")
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