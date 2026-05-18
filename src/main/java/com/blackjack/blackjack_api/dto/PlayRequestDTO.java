package com.blackjack.blackjack_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayRequestDTO {
    private Long playerId;
    private String action;
}