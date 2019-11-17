package com.example.demo;

import lombok.Getter;

@Getter
public class TurnCard extends Command {
    private final String playerName;
    private final String gameId;
    private final int cardIndex;

    public TurnCard(String playerName, String gameId, int cardIndex) {
        super();
        this.playerName = playerName;
        this.gameId = gameId;
        this.cardIndex = cardIndex;
    }
}
