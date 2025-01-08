
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int maxAttempts;// Maximum number of attempts allowed
    private final Combination secretCombination;// The secret combination to be guessed
    private final List<Attempt> attempts;// List to store all attempts made
    private final int numberOfPions;
    private final int numberOfColors;

    // Constructor
    public Board(int maxAttempts, int numberOfPions, int numberOfColors) {
        validateParameters(maxAttempts, numberOfPions, numberOfColors);

        this.maxAttempts = maxAttempts;
        this.numberOfPions = numberOfPions;
        this.numberOfColors = numberOfColors;

        // Generate a secret combination randomly
        this.secretCombination = Combination.generateRandom(numberOfPions, numberOfColors);

        // Initialize attempts list
        this.attempts = new ArrayList<>(maxAttempts);
    }

    // Alternative constructor with a predefined secret combination
    public Board(int maxAttempts, Combination secretCombination) {
        if (maxAttempts <= 0 || secretCombination == null) {
            throw new IllegalArgumentException("Invalid board configuration");
        }

        this.maxAttempts = maxAttempts;
        this.secretCombination = secretCombination;
        this.numberOfPions = secretCombination.getPions().size();
        this.numberOfColors = secretCombination.getPions().stream()
                .map(Pion::getColor)
                .distinct()
                .toList()
                .size();

        // Initialize attempts list
        this.attempts = new ArrayList<>(maxAttempts);
    }

    private void validateParameters(int maxAttempts, int numberOfPions, int numberOfColors) {
        if (maxAttempts <= 0 || numberOfPions <= 0 || numberOfColors <= 0) {
            throw new IllegalArgumentException("Invalid board configuration");
        }
    }

    // Add a new attempt to the board
    public Attempt addAttempt(Combination attempt) {
        // Check if board is already solved or out of attempts
        if (isSolved() || isOutOfAttempts()) {
            throw new IllegalStateException("Cannot add more attempts");
        }

        // Validate the attempt
        if (attempt.getPions().size() != numberOfPions) {
            throw new IllegalArgumentException("Attempt must have " + numberOfPions + " pions");
        }

        // Create and store the attempt
        Attempt newAttempt = new Attempt(attempt, secretCombination);
        attempts.add(newAttempt);

        return newAttempt;
    }

    // Check if the secret has been found
    public boolean isSolved() {
        return !attempts.isEmpty() && attempts.get(attempts.size() - 1).isSolved();
    }

    // Check if player is out of attempts
    public boolean isOutOfAttempts() {
        return attempts.size() >= maxAttempts;
    }

    // Get remaining attempts
    public int getRemainingAttempts() {
        return maxAttempts - attempts.size();
    }

    // Method to get number of pions
    public int getNumberOfPions() {
        return numberOfPions;
    }

    // Method to get number of Colors
    public int getNumberOfColors() {
        return numberOfColors;
    }

    // Get all attempts made
    public List<Attempt> getAllAttempts() {
        return new ArrayList<>(attempts);
    }

    // Get the secret combination (for testing or revealing at end of game)
    public Combination getSecretCombination() {
        return secretCombination;
    }

    // Generate a detailed game status report
    public String getGameStatusReport() {
        StringBuilder report = new StringBuilder();
        report.append("Game Status Report:\n");
        report.append("Total Attempts: ").append(attempts.size())
                .append(" / ").append(maxAttempts).append("\n");
        report.append("Remaining Attempts: ").append(getRemainingAttempts()).append("\n");

        if (isSolved()) {
            report.append("Game Status: SOLVED!\n");
        } else if (isOutOfAttempts()) {
            report.append("Game Status: FAILED\n");
        } else {
            report.append("Game Status: IN PROGRESS\n");
        }

        return report.toString();
    }

    // ToString method
    @Override
    public String toString() {
        return "Board{"
                + "maxAttempts=" + maxAttempts
                + ", secretCombination=" + secretCombination
                + ", attempts=" + attempts.size()
                + ", numberOfPins=" + numberOfPions
                + ", numberOfColors=" + numberOfColors
                + '}';
    }
}
