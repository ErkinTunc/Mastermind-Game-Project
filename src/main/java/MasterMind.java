
import java.util.*;

public class MasterMind {

    private static boolean showDetailedErrors = true;
    private static int maxAttempts = 10; // 10 or 12
    private static int numberOfPions = 4; // 4 or 5
    private static int numberOfColors = 6; // 6 or 8

    private static void singlePlayerMode(int maxAttempts, int numberOfPions, int numberOfColors, boolean showDetailedErrors) {
        Scanner scanner = new Scanner(System.in);
        // Get player name
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);

        // Create and start game
        Game game = new Game(player, maxAttempts, numberOfPions, numberOfColors, showDetailedErrors);
        game.start();
    }

    private static void multiplayerMode(int maxAttempts, int numberOfPions, int numberOfColors, boolean showDetailedErrors) {
        Scanner scanner = new Scanner(System.in);
        // Get number of players
        int playerCount = getIntInput("Enter number of players (2-4): ", 2, 4);
        List<Player> players = new ArrayList<>();

        // Create players
        for (int i = 1; i <= playerCount; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String playerName = scanner.nextLine();
            players.add(new Player(playerName));
        }

        // Create and start game
        Game game = new Game(players, maxAttempts, numberOfPions, numberOfColors, showDetailedErrors);
        game.start();
    }

    private static void playAgainstComputer(int maxAttempts, int numberOfPions, int numberOfColors, boolean showDetailedErrors) {
        Scanner scanner = new Scanner(System.in);
        // Get player name
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        Player humanPlayer = new Player(playerName);
        AutomaticPlayer computerPlayer = new AutomaticPlayer("ComputerAI");

        // Create player list
        List<Player> players = new ArrayList<>();
        players.add(humanPlayer);
        players.add(computerPlayer);

        // Create and start game
        Game game = new Game(players, maxAttempts, numberOfPions, numberOfColors, showDetailedErrors);
        game.start();
    }

    private static void gameConfiguration() {
        System.out.println("\nGame Configuration:");
        maxAttempts = getIntInput("Enter max attempts (5-20): ", 5, 20);
        numberOfPions = getIntInput("Enter number of pions (3-5): ", 3, 5);
        numberOfColors = getIntInput("Enter number of colors (4-8): ", 4, 8);

        // Add option to toggle detailed error messages
        System.out.print("Show detailed error messages? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        showDetailedErrors = scanner.nextLine().trim().toLowerCase().startsWith("y");
    }

    // Utility method for getting validated integer input
    private static int getIntInput(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Input must be between " + min + " and " + max);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    public static int getMaxAttempts() {
        return maxAttempts;
    }

    public static int getNumberOfPions() {
        return numberOfPions;
    }

    public static int getNumberOfColors() {
        return numberOfColors;
    }

    public static boolean isShowDetailedErrors() {
        return showDetailedErrors;
    }

    // ======= Main Game  =========
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Main menu
            System.out.println("===== MASTER MIND =====");
            System.out.println("1. Single Player Mode");
            System.out.println("2. Multiplayer Mode");
            System.out.println("3. Play Against Computer");
            System.out.println("4. Settings");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    singlePlayerMode(maxAttempts, numberOfPions, numberOfColors, showDetailedErrors);
                    continue;
                case 2:
                    multiplayerMode(maxAttempts, numberOfPions, numberOfColors, showDetailedErrors);
                    continue;
                case 3:
                    playAgainstComputer(maxAttempts, numberOfPions, numberOfColors, showDetailedErrors);
                    continue;
                case 4:
                    gameConfiguration();
                    continue;
                case 5: {
                    System.out.println("Thanks for playing! Goodbye.");
                    return;
                }
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

}

// ====== Testing Pion Class ======
/*
        // Create a specific pin
        Pion redPin = new Pion(Pion.Color.RED, 2);

        // Create a random pin
        Pion randomPin = Pion.createRandomPin(4);  // 4 is max position

        // Compare pins        
        boolean areSame = redPin.equals(randomPin);

        System.out.println("1. " + redPin.toString());
        System.out.println("2. " + randomPin.toString());
        System.out.println("They are same : " + areSame);
 */
// ====== Testing Combination Class ======
/*
        // Generate a random secret combination
        Combination secretCombination = Combination.generateRandom(4, 6);

        // Create an attempt combination
        List<Pion> attemptPions = Arrays.asList(
                new Pion(Pion.Color.RED, 0),
                new Pion(Pion.Color.BLUE, 1),
                new Pion(Pion.Color.GREEN, 2),
                new Pion(Pion.Color.YELLOW, 3)
        );
        Combination attempt = new Combination(attemptPions, 6);
        System.out.println("is attempt valid: " + attempt.isValid());

        // Validate the attempt
        AttemptResult result = secretCombination.validate(attempt);
        boolean areTheySame = secretCombination.equals(attempt);
        System.out.println("Secret Combination : " + secretCombination.toString());
        System.out.println("Player's Attempt : " + attempt.toString());
        System.out.println("They are same : " + areTheySame);
        System.out.println(result);
 */
// ====== Testing Board Class ======
/*
        // Create a board with 10 attempts, 4 pions, and 6 colors
        Board board = new Board(10, 4, 6);

        // Create an attempt combination
        List<Pion> attemptPions2 = Arrays.asList(
                new Pion(Pion.Color.RED, 0),
                new Pion(Pion.Color.BLUE, 1),
                new Pion(Pion.Color.GREEN, 2),
                new Pion(Pion.Color.YELLOW, 3)
        );
        Combination attempt2 = new Combination(attemptPions2, 6);

        // Add the attempt to the board
        Attempt gameAttempt = board.addAttempt(attempt2);

        // Check game status
        System.out.println(board.getGameStatusReport());
        System.out.println("Remaining Attempts: " + board.getRemainingAttempts());
 */
// ====== Testing Game Class ======
/*
        // Single player game
        Player humanPlayer = new Player("Alice");
        Game singlePlayerGame = new Game(
                humanPlayer,
                5, // 5 rounds
                10, // 10 max attempts per round
                4, // 4 pions
                6 // 6 colors
        );
        singlePlayerGame.start();
 */
 /*
        // Multiplayer game
        List<Player> players = Arrays.asList(
                new Player("Alice"),
                new AutomaticPlayer("ComputerAI")
        );
        Game multiplayerGame = new Game(
                players,
                5, // 5 rounds
                10, // 10 max attempts per round
                4, // 4 pions
                6 // 6 colors
        );
        multiplayerGame.start();
 */
