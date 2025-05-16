package code.challenge.utilities;

import code.challenge.entities.GameResult;
import code.challenge.entities.TeamResult;
import org.junit.jupiter.api.Test;
import code.challenge.exception.CodeChallengeException;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {
    
    @Test
    public void testProcessLine()
            throws CodeChallengeException {

        GameResult result = Utils.processLine("Lions 3, Snakes 3");

        assertEquals("Lions", result.homeTeamResult().getTeam());
        assertEquals(3, result.homeTeamResult().getGoals());
        assertEquals("Snakes", result.awayTeamResult().getTeam());
        assertEquals(3, result.awayTeamResult().getGoals());

        assertThrows(CodeChallengeException.class, () -> Utils.processLine("Lions 3 Snakes 3"));
        assertThrows(CodeChallengeException.class, () -> Utils.processLine("Lions, 3, Snakes, 3"));
        assertThrows(CodeChallengeException.class, () -> Utils.processLine(null));
    }

    @Test
    public void testToken()
            throws CodeChallengeException {

        TeamResult result = Utils.processToken("Lions 3");

        assertEquals("Lions", result.getTeam());
        assertEquals(3, result.getGoals());

        assertThrows(CodeChallengeException.class, () -> Utils.processLine("Lions3"));
        assertThrows(CodeChallengeException.class, () -> Utils.processLine("3"));
        assertThrows(CodeChallengeException.class, () -> Utils.processLine(null));
    }

}
