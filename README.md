# MasterMind Game

A Java implementation of the classic MasterMind board game where players try to guess a secret combination of colored pins.

## Overview

This implementation of MasterMind allows players to:
- Play in single-player or multiplayer mode
- Customize game parameters (number of pins, colors, and attempts)
- Receive detailed or summary feedback on each guess
- Earn points based on remaining attempts

## Game Structure

The project is organized into several key classes:

- `Game`: Main game controller managing game flow and player interactions
- `Pion`: Represents individual colored pins and their positions
- `Combination`: Handles both secret combinations and player guesses
- `Result`: Processes and stores the outcome of each guess
- `Plateau` (Board): Manages the game board state
- `Color`: Contains ANSI color codes for terminal display

## Features

### Game Modes
- **Single Player**: Challenge yourself against the computer
- **Multiplayer**: Compete with other players (experimental feature)

### Customizable Parameters
- Number of pins in the combination
- Number of available colors
- Maximum attempts allowed
- Detailed or summary feedback mode

### Scoring System
- Points awarded based on remaining attempts
- 10 points per remaining attempt
- Leaderboard display at game end

### Feedback Systems
Two feedback modes are available:

1. **Detailed Feedback**
   - Shows correct positions
   - Indicates incorrect positions
   - Displays color-specific information

2. **Summary Feedback**
   - Number of correct color and position matches
   - Number of correct colors in wrong positions

## Technical Implementation

### Class Dependencies
```
Game
├── Combination
│   ├── Pion
│   └── Result
└── Plateau
    └── Pion
```

### Input Validation
- Validates all game parameters
- Ensures at least one player is present
- Checks for valid number of attempts, pins, and colors

### Error Handling
- Comprehensive parameter validation
- Illegal argument checking
- Safe game state management

## Installation

1. Clone the repository
2. Compile Java files
3. Run the main class

## Usage

Basic game start:
```java
Player player = new Player("Player 1");
Game game = new Game(player, 10, 4, 6, false);
game.start();
```

Multiplayer mode:
```java
List<Player> players = Arrays.asList(
    new Player("Player 1"),
    new Player("Player 2")
);
Game game = new Game(players, 10, 4, 6, false);
game.start();
```

## Current Limitations

- Multiplayer mode is experimental and needs improvement
- No save/restore functionality
- Terminal-based interface only

## Future Improvements

- Enhanced multiplayer functionality
- Save/load game state
- Graphical user interface
- Additional game modes
- Network play support

## Contributing

Feel free to submit issues and enhancement requests!

## License

[License information not provided in source documents]
