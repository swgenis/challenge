package code.challenge.utilities;

import code.challenge.dto.GameResult;
import code.challenge.dto.TeamResult;
import org.junit.jupiter.api.Test;
import code.challenge.exception.CodeChallengeException;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {
    
    @Test
    public void testProcessLine()
            throws CodeChallengeException {

        // Test valid lines.
        GameResult result = Utils.processLine("Lions 3, Snakes 3");
        assertEquals("Lions", result.homeTeamResult().team());
        assertEquals(3, result.homeTeamResult().goals());
        assertEquals("Snakes", result.awayTeamResult().team());
        assertEquals(3, result.awayTeamResult().goals());

        // Test invalid lines.
        assertThrows(CodeChallengeException.class, () -> Utils.processLine("Lions 3 Snakes 3"));
        assertThrows(CodeChallengeException.class, () -> Utils.processLine("Lions, 3, Snakes, 3"));
        assertThrows(CodeChallengeException.class, () -> Utils.processLine(null));
    }

    @Test
    public void testToken()
            throws CodeChallengeException {

        // Test valid tokens.
        TeamResult result = Utils.processToken("Lions 3");
        assertEquals("Lions", result.team());
        assertEquals(3, result.goals());

        result = Utils.processToken("Lions RC 3");
        assertEquals("Lions RC", result.team());
        assertEquals(3, result.goals());

        // Test invalid tokens.
        assertThrows(CodeChallengeException.class, () -> Utils.processLine("Lions3"));
        assertThrows(CodeChallengeException.class, () -> Utils.processLine("Lions  3"));
        assertThrows(CodeChallengeException.class, () -> Utils.processLine("Lions -3"));
        assertThrows(CodeChallengeException.class, () -> Utils.processLine("3"));
        assertThrows(CodeChallengeException.class, () -> Utils.processLine(null));
        assertThrows(CodeChallengeException.class, () -> Utils.processToken("Lions"));
        assertThrows(CodeChallengeException.class, () -> Utils.processToken("Lions 3.4"));
    }

}
