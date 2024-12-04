# Apples2Apples Game

## Overview
Apples2Apples is a card-based party game where players match nouns (Red Apples) to adjectives (Green Apples) for humorous or creative outcomes. This Java implementation provides a console-interactive version of the game.

## Features
### Current Implementation
- **Game Setup**:
  - Reads Green Apple (adjectives) and Red Apple (nouns) cards from text files.
  - Shuffles both decks for randomness.
  - Deals seven Red Apple cards to each player, including the judge.
  - Randomly selects the initial judge using `JudgeManager` and `RandomJudgeSelection`.

- **Console Outputs**:
  - Round announcements and judge assignments.
  - Setup completion confirmation.
  - Distribution details for each player.

## Example Usage
Run the `Apples2Apples` (project/src/main/Apples2Apples) to set up the game and assign an initial judge:
```
Total Green Apples loaded: 614
Total Red Apples loaded: 1826
Player 12 has 7 cards.
Player 30 has 7 cards.
Player 56 has 7 cards.
*****************************************************
**                 NEW ROUND - JUDGE               **
*****************************************************
The judge for the first round is: Player 30
Game setup is done!
```

## Future Enhancements
- Implement game play features to Apples2Apples.
  - Allow dynamic player creation through console input or configuration files.
  - Implement the actual gameplay where players submit cards and the judge selects a winner.
  - Extend the interaction model to support network-based multiplayer via RemotePlayerCommunication.
- Include classes to implement other game variations.

## Version History
`Version 1.0:` Original code for the game Apples to Apples.

`Version 2.0:` Refactored code adhering to SOLID principles and Booch metrics:
- Restructure project and modularized components necessary for full gameplay.
- Implemented game setup for Apples to Apples game. 
- Game requirements 1 to 5 have pass unit testing.
- Console interaction for game setup.