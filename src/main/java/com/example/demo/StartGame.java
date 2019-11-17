package com.example.demo;

import lombok.Getter;

@Getter
public class StartGame extends Command {
    private final String firstPlayer;
    private final String secondPlayer;
    private final String gameId;

    public StartGame(String firstPlayer, String secondPlayer, String gameId) {
        super();
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.gameId = gameId;
    }
}
