package code.challenge.utilities;

import code.challenge.dto.GameResult;
import code.challenge.dto.TeamResult;
import code.challenge.exception.CodeChallengeException;

import java.util.regex.PatternSyntaxException;

/**
 * Utility class providing helper methods for processing game result data.
 * This class includes methods to parse and validate lines and tokens representing game results.
 */
public class Utils {

    /**
     * Processes a single line representing a game result, and converts it into
     * a {@code GameResult} instance.
     * <p>
     * Expected format: "Home Team 1, Away Team 1".
     *
     * @param line the input string containing the game result in the specified format
     * @return a {@code GameResult} object representing the parsed game result
     * @throws CodeChallengeException if the line does not meet the expected format, such as malformed input,
     *                                negative goals, missing values, or if both teams are the same
     */
    public static GameResult processLine(String line)
            throws CodeChallengeException {

        try {
            String[] tokens = line.split(",");
            if (tokens.length < 2) {
                throw new CodeChallengeException("Invalid line format: missing comma.");
            }
            if (tokens.length > 2) {
                throw new CodeChallengeException("Invalid line format: more than one comma.");
            }

            TeamResult homeTeamResult = processToken(tokens[0]);
            TeamResult awayTeamResult = processToken(tokens[1]);
            if (homeTeamResult.goals() < 0 || awayTeamResult.goals() < 0) {
                throw new CodeChallengeException("Invalid number format: negative goals.");
            }
            if (homeTeamResult.team().equals(awayTeamResult.team())) {
                throw new CodeChallengeException("Teams cannot be the same.");
            }

            return new GameResult(homeTeamResult, awayTeamResult);
        } catch (PatternSyntaxException | NullPointerException e) {
            throw new CodeChallengeException("Invalid line format.");
        }
    }

    /**
     * Parses a token representing a team's result and converts it into a {@code TeamResult} instance.
     * Format: "Team 1", where Team is a string, and the goals is a non-negative integer.
     *
     * @param token the input string containing the team's name and goals scored, separated by a space
     * @return a {@code TeamResult} object containing the parsed team name and goals
     * @throws CodeChallengeException if the token does not meet the expected format, or if the number
     *                                of goals is negative, missing, or not a valid integer
     */
    public static TeamResult processToken(String token)
            throws CodeChallengeException {

        int lastSpaceIndex = token.lastIndexOf(" ");
        if (lastSpaceIndex == -1) {
            throw new CodeChallengeException("String does not contain any spaces.");
        }

        // Find the team name and remove leading and trailing spaces.
        String team = token.substring(0, lastSpaceIndex).trim();
        if (team.isEmpty()) {
            throw new CodeChallengeException("Team name is empty.");
        }

        try {
            int goals = Integer.parseInt(token.substring(lastSpaceIndex + 1));
            if (goals < 0) {
                throw new CodeChallengeException("Goals cannot be negative.");
            }

            return new TeamResult(team, goals);
        } catch (NumberFormatException nfe) {
            throw new CodeChallengeException("Invalid number format.");
        }
    }

}
