package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DemoApplicationTests {

    private EventStorage eventStorage = new EventStorage();

    private Games games;

    private List<Event> givenEvents;

    @BeforeEach
    public void init() {
        games = new Games(eventStorage);
    }

    @Test
    void startGameSucceeds() {
        given("1", emptyEventStore());
        games.inputCommand(new StartGame("Thomas", "Michel", "1"));
        then(Arrays.asList(
                new GameStarted("Thomas", "Michel", "1"),
                new PlayerSwitched("Thomas", "1")
        ));
    }

    @Test
    void startGameFails() {
        given("1", Arrays.asList(new GameStarted("Thomas", "Michel", "1")));
        assertThrows(IllegalStateException.class, () -> {
            games.inputCommand(new StartGame("Thomas", "Michel", "1"));
        });
    }

    @Test
    void turnCardFailsBecauseNotYourTurnMichel() {
        given("1", Arrays.asList(new GameStarted("Thomas", "Michel", "1")));
        assertThrows(IllegalStateException.class, () -> {
            games.inputCommand(new TurnCard("Michel", "1", 1));
        });
    }

    @Test
    void turnCardSucceeds() {
        given("1", Arrays.asList(new GameStarted("Thomas", "Michel", "1")));
        games.inputCommand(new TurnCard("Thomas", "1", 1));
        then(Arrays.asList(new CardTurned("1", 1)));
    }

    private List<Event> emptyEventStore() {
        return new ArrayList<>();
    }

    private void given(String gameId, List<Event> initialEvents) {
        givenEvents = initialEvents;
        eventStorage.appendToStream(gameId, initialEvents);
    }

    private void then(List<Event> newlyGeneratedExpected) {
        final List<Event> events = eventStorage.streamById("1");

        givenEvents.addAll(newlyGeneratedExpected);
        assertThat(events).asList().containsExactlyElementsOf(givenEvents);
    }

}
