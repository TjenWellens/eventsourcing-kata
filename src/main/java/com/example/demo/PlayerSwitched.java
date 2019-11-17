package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlayerSwitched extends Event {
    private final String activePlayerName;
    private final String gameId;
}
