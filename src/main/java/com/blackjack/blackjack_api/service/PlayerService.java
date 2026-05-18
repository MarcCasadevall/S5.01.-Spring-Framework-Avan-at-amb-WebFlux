package com.blackjack.blackjack_api.service;

import com.blackjack.blackjack_api.exception.PlayerNotFoundException;
import com.blackjack.blackjack_api.model.Player;
import com.blackjack.blackjack_api.model.Ranking;
import com.blackjack.blackjack_api.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player createPlayer(String username) {
        Player player = new Player();
        player.setUsername(username);
        player.setBalance(1000.0);
        player.setTotalGamesPlayed(0);
        player.setTotalWins(0);
        return playerRepository.save(player);
    }

    public Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    public Player getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username)
                .orElseThrow(() -> new PlayerNotFoundException(username));
    }

    public Player updatePlayer(Long playerId, String newUsername) {
        Player player = getPlayerById(playerId);
        player.setUsername(newUsername);
        return playerRepository.save(player);
    }

    public List<Ranking> getRanking() {
        return playerRepository.findAllOrderedByRanking()
                .stream()
                .map(Ranking::new)
                .collect(Collectors.toList());
    }

    public void deletePlayer(Long playerId) {
        Player player = getPlayerById(playerId);
        playerRepository.delete(player);
    }

    public void incrementWins(Long playerId) {
        Player player = getPlayerById(playerId);
        player.setTotalWins(player.getTotalWins() + 1);
        player.setTotalGamesPlayed(player.getTotalGamesPlayed() + 1);
        playerRepository.save(player);
    }

    public void incrementGamesPlayed(Long playerId) {
        Player player = getPlayerById(playerId);
        player.setTotalGamesPlayed(player.getTotalGamesPlayed() + 1);
        playerRepository.save(player);
    }
}