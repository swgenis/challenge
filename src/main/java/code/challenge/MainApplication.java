package code.challenge;

import code.challenge.entities.Ranking;
import code.challenge.exception.CodeChallengeException;
import code.challenge.utilities.Printer;
import code.challenge.processor.FileProcessor;

import java.util.List;

/**
 * The MainApplication serves as the entry point for the application which processes game results,
 * ranks teams based on their performance, and prints the leaderboard.
 * <p>
 * Responsibilities:
 * - Reads a file containing the game results, processes the data, and calculates rankings.
 * - Utilizes constants for assigning points for wins and draws.
 * - Handles exceptions during file processing or ranking calculation.
 * <p>
 * The main method coordinates the following actions:
 * - Initializes a FileProcessor with the fileName and scoring constants (WIN_POINTS, DRAW_POINTS).
 * - Processes the file to compute game results and ranks teams accordingly.
 * - Passes the results to Printer to print the sorted leaderboard of rankings.
 * <p>
 * Constants:
 * - WIN_POINTS: Points awarded for a win.
 * - DRAW_POINTS: Points awarded for a draw.
 * <p>
 * Exceptions handled include:
 * - CodeChallengeException: Thrown for application-specific errors like empty files or invalid data.
 * - General Exception: Logs unexpected errors to ensure robustness.
 */
public class MainApplication {

    /**
     * Just trying to show that constants are good to use.
     */
    public static final int WIN_POINTS = 3;
    public static final int DRAW_POINTS = 1;

    public static void main(String[] args) {

        try {
            String fileName = "src/test/resources/example1.txt";
            List<Ranking> rankings = new FileProcessor(fileName, WIN_POINTS, DRAW_POINTS)
                    .process()
                    .rank();
            Printer.print(rankings);

        } catch (CodeChallengeException cde) {
            System.out.println(cde.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error occurred.");
        }

    }

}
