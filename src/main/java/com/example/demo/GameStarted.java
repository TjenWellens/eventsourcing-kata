package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class GameStarted extends Event {
    private final String firstPlayer;
    private final String secondPlayer;
    private final String gameId;

    public GameStarted(String firstPlayer, String secondPlayer, String gameId) {
        super();
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.gameId = gameId;
    }
}
