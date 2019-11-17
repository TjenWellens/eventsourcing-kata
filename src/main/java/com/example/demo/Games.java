package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Games {
    private final EventStorage eventStorage;

    public Games(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    public void inputCommand(StartGame command) {
        if (eventStorage.streamById(command.getGameId()).stream().anyMatch(event -> event.getClass() == GameStarted.class)) {
            throw new IllegalStateException();
        }

        eventStorage.appendToStream(command.getGameId(), Arrays.asList(
                new GameStarted(command.getFirstPlayer(), command.getSecondPlayer(), command.getGameId()),
                new PlayerSwitched(command.getFirstPlayer(), command.getGameId())
        ));
    }

    public void inputCommand(TurnCard command) {
        if (!isMyTurn(command.getPlayerName(), command.getGameId())) {
            throw new IllegalStateException();
        }

        eventStorage.appendToStream(command.getGameId(), Arrays.asList(
                new CardTurned(command.getGameId(), 1)
        ));
    }

    private boolean isMyTurn(String playerName, String gameId) {
        final List<Event> collect = eventStorage.streamById(gameId).stream()
                .filter(event -> event.getClass() == PlayerSwitched.class)
                .collect(Collectors.toList());
        final PlayerSwitched playerSwitched = (PlayerSwitched) collect.get(collect.size() - 1);

        return playerSwitched.getActivePlayerName().equals(playerName);
    }
}
