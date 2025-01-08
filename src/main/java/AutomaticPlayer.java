
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutomaticPlayer extends Player {

    // Strategy for generating attempts
    private final List<Attempt> previousAttempts;
    private final Random random;

    // Constructor
    public AutomaticPlayer(String name) {
        super(name);
        this.previousAttempts = new ArrayList<>();
        this.random = new Random();
    }

    @Override
    public Combination makeAttempt(Board board) {
        System.out.println("\n" + getName() + " is thinking...");

        Combination attempt;
        List<Attempt> allAttempts = board.getAllAttempts();

        if (previousAttempts.isEmpty() || allAttempts.isEmpty()) {
            attempt = generateRandomAttempt(board);
        } else if (allAttempts.size() < 3) {
            attempt = generateSmartAttempt(board);
        } else {
            attempt = generateIntelligentAttempt(board);
        }

        // Store the attempt for future reference
        Attempt result = new Attempt(attempt, board.getSecretCombination());
        previousAttempts.add(result);

        System.out.println(getName() + "'s attempt: " + attempt);
        return attempt;
    }

    private Combination generateRandomAttempt(Board board) {
        return Combination.generateRandom(board.getNumberOfPions(), board.getNumberOfColors());
    }

    private Combination generateSmartAttempt(Board board) {
        // Get the latest attempt from our previous attempts
        Attempt lastAttempt = previousAttempts.get(previousAttempts.size() - 1);
        AttemptResult lastResult = lastAttempt.getResult();
        List<Pion> lastPions = lastAttempt.getCombination().getPions();

        List<Pion> newPions = new ArrayList<>();

        for (int i = 0; i < board.getNumberOfPions(); i++) {
            Pion.Color color;

            if (lastResult.getCorrectColorAndPosition() > 0) {
                // Higher chance to keep correct colors
                if (random.nextDouble() < 0.7) {
                    color = lastPions.get(i).getColor();
                } else {
                    color = getRandomNewColor(board.getNumberOfColors(), lastPions.get(i).getColor());
                }
            } else if (lastResult.getCorrectColorWrongPosition() > 0) {
                // Try moving colors around
                if (random.nextDouble() < 0.5) {
                    int otherPos = random.nextInt(board.getNumberOfPions());
                    color = lastPions.get(otherPos).getColor();
                } else {
                    color = getRandomNewColor(board.getNumberOfColors(), lastPions.get(i).getColor());
                }
            } else {
                // If nothing was correct, try completely new colors
                color = getRandomNewColor(board.getNumberOfColors(), lastPions.get(i).getColor());
            }

            newPions.add(new Pion(color, i));
        }

        return new Combination(newPions, board.getNumberOfColors());
    }

    private Combination generateIntelligentAttempt(Board board) {
        List<Attempt> allAttempts = board.getAllAttempts();
        List<Pion> newPions = new ArrayList<>();

        int numberOfPositions = board.getNumberOfPions();
        int numberOfColors = board.getNumberOfColors();

        int[][] positionColorScores = new int[numberOfPositions][numberOfColors];
        int[] successfulColors = new int[numberOfColors];

        // Analyze all previous attempts
        for (Attempt attempt : allAttempts) {
            AttemptResult result = attempt.getResult();
            List<Pion> attemptPions = attempt.getCombination().getPions();

            int weight = calculateAttemptWeight(result);

            for (int pos = 0; pos < attemptPions.size(); pos++) {
                int colorIndex = attemptPions.get(pos).getColor().ordinal();

                // Update position-specific score
                positionColorScores[pos][colorIndex] += weight;

                // Update general color success score
                if (result.getCorrectColorAndPosition() > 0
                        || result.getCorrectColorWrongPosition() > 0) {
                    successfulColors[colorIndex]++;
                }
            }
        }

        // Generate new combination based on analysis
        for (int pos = 0; pos < numberOfPositions; pos++) {
            Pion.Color color = selectBestColor(pos, positionColorScores, successfulColors, numberOfColors);
            newPions.add(new Pion(color, pos));
        }

        return new Combination(newPions, board.getNumberOfColors());
    }

    private int calculateAttemptWeight(AttemptResult result) {
        // Weight from 1-10 based on correctness
        return (result.getCorrectColorAndPosition() * 2 + result.getCorrectColorWrongPosition()) * 10
                / (result.getTotalPions() * 3);
    }

    private Pion.Color selectBestColor(int position, int[][] positionColorScores, int[] successfulColors, int numberOfColors) {
        double bestScore = -1;
        int bestColorIndex = 0;

        for (int colorIndex = 0; colorIndex < numberOfColors; colorIndex++) {
            // Combine position-specific score (70%) and general success score (30%)
            double score = (positionColorScores[position][colorIndex] * 0.7)
                    + (successfulColors[colorIndex] * 0.3);

            // Add small random factor to break ties
            score += random.nextDouble() * 0.1;

            if (score > bestScore) {
                bestScore = score;
                bestColorIndex = colorIndex;
            }
        }

        return Pion.Color.values()[bestColorIndex];
    }

    private Pion.Color getRandomNewColor(int numberOfColors, Pion.Color currentColor) {
        Pion.Color[] colors = Pion.Color.values();
        Pion.Color newColor;
        do {
            newColor = colors[random.nextInt(numberOfColors)];
        } while (newColor == currentColor);
        return newColor;
    }

    @Override
    public String toString() {
        return "AutomaticPlayer{name='" + getName()
                + "', attempts=" + previousAttempts.size()
                + ", score=" + getScore() + "}";
    }
}
