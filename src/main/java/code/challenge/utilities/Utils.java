package code.challenge.utilities;

import code.challenge.entities.GameResult;
import code.challenge.entities.TeamResult;
import code.challenge.exception.CodeChallengeException;

import java.util.regex.PatternSyntaxException;

/**
 * Utility class providing helper methods for processing game result data.
 * This class includes methods to parse and validate lines and tokens representing game results.
 */
public class Utils {

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
            return new TeamResult(team, goals);
        } catch (NumberFormatException nfe) {
            throw new CodeChallengeException("Invalid number format.");
        }
    }

}
