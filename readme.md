Memory game

- 2 players
- first player starts
- turn 1 card, then another
- if 2 equal cards, score++ and player can go again
- if 2 cards not equal, cards

Interface
```
void appendToStream(String streamId, Event[] events)
Event[] streamById(String streamId)
```

Tests
```
given [<events>]
when <command>
then [<events>]
```


Example
```
given []
when StartGame("Thomas, Michel", 1)
then GameStarted("Thomas, Michel", 1)
```

```
given GameStarted("Thomas, Michel", 1)
when StartGame("Thomas", "Michel", 1)
then Exception
```

```
given GameStarted("Thomas, Michel", 1)
when TurnCard("Michel", ...)
then Exception
```