
import java.util.Random;

public class Pion {

    // Enumeration for colors to ensure type safety
    public enum Color {
        RED, BLUE, GREEN, YELLOW, PURPLE, ORANGE, WHITE, BLACK
    }

    // Attributes of a Pin
    private final Color color;  // The color of the pin
    private final int position; // The position of the pin in a combination

    // Constructor
    public Pion(Color color, int position) {
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }
        this.color = color;
        this.position = position;
    }

    // Getter for color
    public Color getColor() {
        return color;
    }

    // Getter for position
    public int getPosition() {
        return position;
    }

    // toString method for easy printing and debugging
    @Override
    public String toString() {
        return "Pin{"
                + "color=" + color
                + ", position=" + position
                + '}';
    }

    // Optional: Create a method to get a random pin
    public static Pion createRandomPin(int maxPosition) {
        Color[] colors = Color.values();
        Color randomColor = colors[new Random().nextInt(colors.length)];
        return new Pion(randomColor, new Random().nextInt(maxPosition));
    }

}
