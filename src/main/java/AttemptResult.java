
public class AttemptResult {

    private final int correctColorAndPosition;// Number of pions with correct color and correct position
    private final int correctColorWrongPosition;// Number of pions with correct color but wrong position
    private final int totalPions; // Added to store total number of pions

    // Constructor
    public AttemptResult(int correctColorAndPosition, int correctColorWrongPosition, int totalPions) {
        if (correctColorAndPosition < 0 || correctColorWrongPosition < 0) {
            throw new IllegalArgumentException("Result values cannot be negative");
        }
        if (totalPions <= 0) {
            throw new IllegalArgumentException("Total pions must be positive");
        }

        this.correctColorAndPosition = correctColorAndPosition;
        this.correctColorWrongPosition = correctColorWrongPosition;
        this.totalPions = totalPions;
    }

    // ToString method
    @Override
    public String toString() {
        return correctColorAndPosition + " correct, " + correctColorWrongPosition + " misplaced(correct Color wrong position)";
    }

    // Create a detailed feedback string
    public String getFeedback() {
        if (isSecretFound()) {
            return "Congratulations! You've solved the secret combination!";
        }

        StringBuilder feedback = new StringBuilder();
        if (correctColorAndPosition > 0) {
            feedback.append(correctColorAndPosition).append(" correct position. ");
        }
        if (correctColorWrongPosition > 0) {
            feedback.append(correctColorWrongPosition).append(" wrong position with correct color");
        }
        return feedback.length() > 0 ? feedback.toString() : "No matches.";
    }

    
    
    
    // Getter for correct color and position
    public int getCorrectColorAndPosition() {
        return correctColorAndPosition;
    }

    // Getter for correct color wrong position
    public int getCorrectColorWrongPosition() {
        return correctColorWrongPosition;
    }

    // Getter for total number of pions
    public int getTotalPions() {
        return totalPions;
    }
    

    // Check if the secret combination is completely solved
    public boolean isSecretFound() {
        return correctColorAndPosition == totalPions;
    }

}
