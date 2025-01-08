
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Combination {

    // Attributes
    private final List<Pion> pions;
    private final int numberOfColors;

    // Constructor
    public Combination(List<Pion> pions, int numberOfColors) {
        if (pions == null || pions.isEmpty()) {
            throw new IllegalArgumentException("Combination must contain at least one pion");
        }
        this.pions = new ArrayList<>(pions);
        this.numberOfColors = numberOfColors;
    }

    // Method to generate a random combination
    public static Combination generateRandom(int numberOfPions, int numberOfColors) {
        Random random = new Random();
        List<Pion> randomPions = new ArrayList<>();

        for (int i = 0; i < numberOfPions; i++) {
            Pion.Color randomColor = Pion.Color.values()[random.nextInt(numberOfColors)];
            randomPions.add(new Pion(randomColor, i));
        }

        return new Combination(randomPions, numberOfColors);
    }

    // Method to validate an attempt
    public AttemptResult validate(Combination attempt) {
        int correctColorAndPosition = 0;
        int correctColorWrongPosition = 0;

        // Create copies for marking matched pions
        List<Pion> secretPions = new ArrayList<>(this.pions);
        List<Pion> attemptPions = new ArrayList<>(attempt.getPions());

        // First check for correct color and position
        for (int i = 0; i < secretPions.size(); i++) {
            if (secretPions.get(i).getColor().equals(attemptPions.get(i).getColor())) {
                correctColorAndPosition++;
                // Mark matched pions to avoid double counting
                secretPions.set(i, null);
                attemptPions.set(i, null);
            }
        }

        // Then check for correct color but wrong position
        for (int i = 0; i < secretPions.size(); i++) {
            if (attemptPions.get(i) != null) {
                for (int j = 0; j < secretPions.size(); j++) {
                    if (secretPions.get(j) != null
                            && attemptPions.get(i).getColor().equals(secretPions.get(j).getColor())) {
                        correctColorWrongPosition++;
                        secretPions.set(j, null);
                        break;
                    }
                }
            }
        }

        return new AttemptResult(correctColorAndPosition, correctColorWrongPosition, pions.size());
    }

    // Getter for pions
    public List<Pion> getPions() {
        return new ArrayList<>(pions);
    }

    // ToString method
    @Override
    public String toString() {
        return pions.stream()
                .map(pion -> pion.getColor().toString())
                .collect(Collectors.joining(", ", "Combination[", "]"));
    }
}
