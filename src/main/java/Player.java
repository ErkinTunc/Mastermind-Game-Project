
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {

    private final String name;
    private int score;

    // Constructor
    public Player(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty");
        }
        this.name = name;
        this.score = 0;
    }

    // Make an attempt with user input
    public Combination makeAttempt(Board board) {
        Scanner scanner = new Scanner(System.in);
        List<Pion> attemptPions = new ArrayList<>();

        // Get available colors based on the board's configuration
        Pion.Color[] allColors = Pion.Color.values();
        List<Pion.Color> availableColors = new ArrayList<>();
        for (int i = 0; i < board.getNumberOfColors(); i++) {
            availableColors.add(allColors[i]);
        }

        System.out.println(name + ", please enter your attempt:");
        for (int i = 0; i < board.getNumberOfPions(); i++) {
            while (true) {
                System.out.print("Enter color for pion " + (i + 1) + " (");
                for (Pion.Color color : availableColors) {
                    System.out.print(color + " ");
                }
                System.out.print("): ");

                String input = scanner.nextLine().toUpperCase();
                try {
                    Pion.Color selectedColor = Pion.Color.valueOf(input);
                    if (!availableColors.contains(selectedColor)) {
                        showError("Color not available. Please choose from the listed colors.");
                        continue;
                    }
                    attemptPions.add(new Pion(selectedColor, i));
                    break;
                } catch (IllegalArgumentException e) {
                    showError("Invalid color. Please try again.");
                }
            }
        }

        return new Combination(attemptPions, board.getNumberOfColors());
    }

    // Add helper method for showing errors
    private void showError(String message) {
        System.out.println("\u001B[31m❌ Error: " + message + "\u001B[0m");
    }

    // Update score after a game
    public void updateScore(int points) {
        this.score = points;
    }

    public void resetScore() {
        this.score = 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', score=" + score + '}';
    }
}
