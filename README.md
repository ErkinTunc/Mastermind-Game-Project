# 🎮 Mastermind Game

## ✨ Overview
This project is a digital version of the classic **Mastermind** game. Players attempt to guess a secret combination of colored pegs within a limited number of attempts. After each guess, the game provides feedback on the number of correct colors in the correct positions and the number of correct colors in incorrect positions.

## 🌟 Features
- **Customizable settings:** Configure the number of pegs, colors, and attempts to suit your desired difficulty.
- **Game modes:** Play in single-player, multiplayer, or with an optional automatic player (AI).
- **Save/Load functionality:** Save the current game state to continue later.
- **Intelligent AI:** The automatic player can make strategic guesses based on previous attempts.

## 🏗️ Classes

### **🎲 Game**
The core class that manages the game flow.
- **Attributes**: Number of pegs, colors, attempts, and the secret combination.
- **Methods**:
  - `start()`: Initializes the game.
  - `play()`: Handles gameplay logic.
  - `save(path)`: Saves the current game state.
  - `load(path)`: Loads a saved game state.

### **🧑‍🤝‍🧑 Player**
Represents a player in the game.
- **Attributes**: Player's name, score, and remaining attempts.
- **Methods**:
  - `makeAttempt()`: Allows the player to guess the combination.
  - `updateScore(score)`: Updates the player's score.

### **📋 Board**
Tracks the history of attempts and their results.
- **Attributes**: List of all attempts and the maximum allowed attempts.
- **Methods**:
  - `addAttempt(attempt)`: Adds a new attempt to the board.
  - `displayBoard()`: Displays all previous attempts and feedback.

### **🧩 Combination**
Represents a sequence of pegs in the game.
- **Attributes**: A list of pins (pegs) representing the combination.
- **Methods**:
  - `generate(numberOfPins, numberOfColors)`: Generates a random combination.
  - `validate(attempt)`: Compares the attempt against the secret combination.
  - `equals(other)`: Checks if two combinations are identical.

### **📍 Pin**
Represents a single peg in the combination.
- **Attributes**: Color and position of the peg.
- **Methods**:
  - `getColor()`: Returns the color of the pin.

### **📊 Attempt**
Stores the player's guessed combination and the resulting feedback.
- **Attributes**: The guessed combination and the feedback result.
- **Methods**:
  - `checkResult(secretCombination)`: Compares the guessed combination to the secret one.

### **✅ AttemptResult**
Contains the feedback for a player's guess.
- **Attributes**:
  - Number of pegs with correct color and position.
  - Number of pegs with correct color but wrong position.
- **Methods**:
  - `isSecretFound()`: Returns true if the guess matches the secret combination.

### **🤖 AutomaticPlayer** (Optional)
An AI player that can generate intelligent guesses.
- **Methods**:
  - `generateIntelligentAttempt(previousAttempts)`: Uses previous feedback to make a strategic guess.

### **🎨 Color (Enum)**
An enumeration of possible peg colors (e.g., RED, BLUE, GREEN, etc.).

## ⚙️ Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/mastermind-game.git

2. Navigate to the project directory:
   ```bash
   cd mastermind-game
   
3. Compile and run the game:
   ```bash
   javac MasterMind.java
   java MasterMind

## 🕹️ How to Play

1. Setup:
   - Configure the number of pegs, colors, and attempts.
   - Decide on single-player, multiplayer, or AI mode.
2. Gameplay:
   - For each turn, make a guess for the secret combination.
3. Receive feedback on your guess:
   - The number of pegs with the correct color and position.
   - The number of pegs with the correct color but wrong position.
4. Win Condition:
   - Find the exact combination within the allowed attempts.
  
## 📖 Example

### Initial Setup
   - Number of pegs: 4
   - Available colors: Red, Blue, Green, Yellow
   -Maximum attempts: 10
### Feedback Example
   - Secret combination: [Red, Blue, Green, Yellow]
   - Player's guess: [Red, Green, Blue, Yellow]
   - Feedback:
      - 2 pegs with correct color and position.
      - 2 pegs with correct color but wrong position.

## 🤝 Contributing
Feel free to fork this repository, suggest changes, or submit pull requests to improve the game!

