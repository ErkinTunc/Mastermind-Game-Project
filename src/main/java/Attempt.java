
public class Attempt {

    private final Combination combination;// The combination attempted by the player
    private final AttemptResult result;// The result of the attempt

    // Constructor
    public Attempt(Combination combination, Combination secretCombination) {
        if (combination == null || secretCombination == null) {
            throw new IllegalArgumentException("Combinations cannot be null");
        }
        this.combination = combination;
        // Pass the total number of pions to AttemptResult
        int totalPions = combination.getPions().size();
        AttemptResult validation = secretCombination.validate(combination);
        this.result = new AttemptResult(
                validation.getCorrectColorAndPosition(),
                validation.getCorrectColorWrongPosition(),
                totalPions
        );
    }

    // Getter for combination
    public Combination getCombination() {
        return combination;
    }

    // Getter for result
    public AttemptResult getResult() {
        return result;
    }

    // Check if this attempt solved the secret
    public boolean isSolved() {
        return result.isSecretFound();
    }

    // ToString method
    @Override
    public String toString() {
        return "Attempt{combination=" + combination + ", result=" + result + '}';
    }
}
