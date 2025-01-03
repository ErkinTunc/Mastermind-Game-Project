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

### **🧑‍🤝‍🧑 Player**
Represents a player in the game.

### **📋 Board**
Tracks the history of attempts and their results.

### **🧩 Combination**
Represents a sequence of pegs in the game.


### **📍 Pion
Represents a single peg in the combination.

### **📊 Attempt**
Stores the player's guessed combination and the resulting feedback.

### **✅ AttemptResult**
Contains the feedback for a player's guess.

### **🤖 AutomaticPlayer** (Optional)
An AI player that can generate intelligent guesses.

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

