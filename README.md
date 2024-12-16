# 🎮 Mastermind Game Project

This project is an implementation of the classic **Mastermind** game using **Object-Oriented Programming (OOP)**. The game challenges players to guess a secret combination of colored pegs chosen by an opponent within a limited number of attempts.

## ✨ Features

### 🌟 Core Features
1. **Customizable Game Settings**:
   - Number of pegs (4 or 5).
   - Number of colors (6 to 8).
   - Allow or disallow repeated colors.
   - Number of allowed attempts (10 or 12).
2. **Feedback on Attempts**:
   - Indicates the number of pegs with the correct color in the correct position.
   - Indicates the number of pegs with the correct color in the wrong position.

### 🚀 Additional Features
3. **Board Display**:
   - Terminal-based board visualization.
   - Uses Unicode symbols and ANSI escape codes for color representation.
4. **Attempt Validation**:
   - A method to validate attempts and return feedback for the player.
5. **Main Game Loop**:
   - A fully interactive program where players can:
     - Set game parameters.
     - Make attempts to guess the combination.
     - View the current game state.
   - The secret combination is randomly generated.
6. **Multiplayer Mode**:
   - Players take turns making guesses.
   - Points are awarded based on the number of remaining attempts after a successful guess.
   - The game supports multiple rounds, with total scores tracked.
7. **Save and Load Game State**:
   - Save the current game state to a text file.
   - Load a previously saved game to continue playing.

### 🎁 Bonus Features
8. **Automated Player**:
   - Play against an AI player.
9. **Enhanced User Interface**:
   - Text-based User Interface (TUI) using libraries like Lanterna.
   - Optional graphical user interface (GUI) using Java Swing.

## 🛠️ Project Structure

- **Classes**:
  - `Peg`: Represents an individual peg.
  - `Combination`: Manages a sequence of pegs (both attempts and the secret combination).
  - `Feedback`: Provides feedback for an attempt.
  - `Board`: Manages the game state and visual representation.
  - `Game`: Orchestrates the game logic and main loop.
- **Utilities**:
  - `save(Path)` and `load(Path)` methods for saving/loading game state.

## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone <repository_url>

2. Navigate to the project directory
   ```bash
   cd mastermind-game

3. Compile the project using a Java IDE or command-line tools (e.g., javac).

4. Run the main program:
   ```bash
   java Main

## 🔮 Future Improvements
- 🧠 Add difficulty levels for the AI player.
- ✨ Enhance the graphical interface with additional features.
- 🌐 Implement online multiplayer support.

##🤝 Contributing
Contributions are welcome! Feel free to submit pull requests or report issues.


