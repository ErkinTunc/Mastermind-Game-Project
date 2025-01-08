
import java.util.ArrayList;
import java.util.List;

public class Game {

    // Game configuration
    private final int maxAttempts;
    private final int numberOfPions;
    private final int numberOfColors;
    private final boolean showDetailedErrors;

    // Game state
    private final int attemptsPerPlayer; // Store original attempts per player
    private final List<Player> players;// Players participating in the game
    private final boolean isMultiplayerMode;// Game mode (single player or multiplayer)
    private Board gameBoard;

    // Constructor for single player mode
    public Game(Player player, int maxAttempts, int numberOfPions, int numberOfColors, boolean showDetailedErrors) {
        this(List.of(player), maxAttempts, numberOfPions, numberOfColors, showDetailedErrors);
    }

    // Constructor for multiplayer mode
    public Game(List<Player> players, int maxAttempts, int numberOfPions, int numberOfColors, boolean showDetailedErrors) {
        validateGameParameters(players, maxAttempts, numberOfPions, numberOfColors);

        this.players = new ArrayList<>(players);
        this.attemptsPerPlayer = maxAttempts; // Store original attempts per player
        this.maxAttempts = maxAttempts;
        this.numberOfPions = numberOfPions;
        this.numberOfColors = numberOfColors;
        this.showDetailedErrors = showDetailedErrors;
        this.isMultiplayerMode = players.size() > 1;
    }

    private void validateGameParameters(List<Player> players, int maxAttempts, int numberOfPions, int numberOfColors) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("At least one player is required");
        }
        if (maxAttempts <= 0 || numberOfPions <= 0 || numberOfColors <= 0) {
            throw new IllegalArgumentException("Invalid game configuration");
        }
    }

    private List<Integer> initializePlayerScores(int playerCount) {
        List<Integer> scores = new ArrayList<>(playerCount);
        for (int i = 0; i < playerCount; i++) {
            scores.add(0);
        }
        return scores;
    }

    // Main game flow
    public void start() {
        initializeGame();
        playGame();
        showGameResults();
    }

    private void initializeGame() {
        players.forEach(Player::resetScore);
        gameBoard = new Board(maxAttempts, numberOfPions, numberOfColors);
        System.out.println("\n=== Game Started ===");
        if (isMultiplayerMode) {
            System.out.println("Each player has " + attemptsPerPlayer + " attempts");
        }
        else{
            System.out.println("You have " + attemptsPerPlayer + " attempts");
        }
    }

    private void playGame() {
        //System.out.println("\n The secret combination : " + gameBoard.getSecretCombination());
        Player winner = null;
        while (!gameBoard.isSolved() && !gameBoard.isOutOfAttempts()) {
            for (Player currentPlayer : players) {
                System.out.println("\n" + currentPlayer.getName() + "'s turn");

                Combination attempt = currentPlayer.makeAttempt(gameBoard);
                Attempt result = gameBoard.addAttempt(attempt);

                showAttemptResult(attempt, result);

                if (gameBoard.isSolved()) {
                    winner = currentPlayer;
                    updateWinnerScore(winner);
                    break;
                }
            }
        }
        if (winner == null) {
            System.out.println("\nGame Over! The secret combination was: " + gameBoard.getSecretCombination());
        }
    }

    private void showAttemptResult(Combination attempt, Attempt result) {
        if (showDetailedErrors) {
            showDetailedFeedback(attempt);
        } else {
            showSummaryFeedback(result);
        }
    }

    private void showDetailedFeedback(Combination attempt) {
        List<Pion> attemptPions = attempt.getPions();
        List<Pion> secretPions = gameBoard.getSecretCombination().getPions();

        System.out.println("\nDetailed Feedback:");
        System.out.println("Your attempt: " + attempt);

        // Show correct positions first
        boolean hasCorrectPositions = false;
        System.out.println("Correct positions:");
        for (int i = 0; i < attemptPions.size(); i++) {
            if (attemptPions.get(i).getColor() == secretPions.get(i).getColor()) {  // Compare colors instead of using equals
                System.out.println("Position " + (i + 1) + ": " + attemptPions.get(i).getColor());
                hasCorrectPositions = true;
            }
        }
        if (!hasCorrectPositions) {
            System.out.println("None");
        }

        // Then show incorrect positions
        boolean hasIncorrectPositions = false;
        System.out.println("\nIncorrect positions:");
        for (int i = 0; i < attemptPions.size(); i++) {
            if (attemptPions.get(i).getColor() != secretPions.get(i).getColor()) {  // Compare colors instead of using equals
                System.out.println("Position " + (i + 1) + ": " + attemptPions.get(i).getColor());
                hasIncorrectPositions = true;
            }
        }
        if (!hasIncorrectPositions) {
            System.out.println("None");
        }
    }

    private void showSummaryFeedback(Attempt result) {
        AttemptResult attemptResult = result.getResult();
        System.out.println("Correct color and position: " + attemptResult.getCorrectColorAndPosition());
        System.out.println("Correct color, wrong position: " + attemptResult.getCorrectColorWrongPosition());
    }

    private void updateWinnerScore(Player winner) {
        int remainingAttempts;
        if (isMultiplayerMode) {
            // Calculate remaining attempts based on current player's attempts only
            int usedAttempts = gameBoard.getAllAttempts().size() % attemptsPerPlayer;
            remainingAttempts = attemptsPerPlayer - usedAttempts;
        } else {
            remainingAttempts = gameBoard.getRemainingAttempts();
        }

        int score = calculateScore(remainingAttempts);
        winner.updateScore(score);
        System.out.println("\nCongratulations " + winner.getName() + "!");
        System.out.println("You won with " + remainingAttempts + " attempts remaining!");
        System.out.println("Score: " + score);
    }

    private int calculateScore(int remainingAttempts) {
        return remainingAttempts * 10;
    }

    private void showGameResults() {
        System.out.println("\n=== Game Results ===");
        players.forEach(player
                -> System.out.println(player.getName() + ": " + player.getScore() + " points"));
    }

    // Getters
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public Board getGameBoard() {
        return gameBoard;
    }

}
