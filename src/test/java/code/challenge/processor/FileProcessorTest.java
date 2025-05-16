package code.challenge.processor;

import code.challenge.MainApplication;
import code.challenge.entities.GameResult;
import code.challenge.entities.Ranking;
import code.challenge.entities.TeamResult;
import code.challenge.exception.CodeChallengeException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FileProcessorTest {

    @Test
    public void testExampleProcess()
            throws CodeChallengeException {

        String fileName = "src/test/resources/championship.txt";
        List<Ranking> rankings = new FileProcessor(fileName, MainApplication.WIN_POINTS, MainApplication.DRAW_POINTS)
                .process()
                .rank();

        assertRanking(rankings, "Tarantulas", 6);
        assertRanking(rankings, "Lions", 5);
        assertRanking(rankings, "FC Awesome", 1);
        assertRanking(rankings, "Snakes", 1);
        assertRanking(rankings, "Grouches", 0);

    }

    @Test
    public void testInvalidFiles() {

        assertEquals("File name is not available.",
            assertThrows(CodeChallengeException.class,
                () -> new FileProcessor(null, MainApplication.WIN_POINTS, MainApplication.DRAW_POINTS).process())
                    .getMessage());

        assertEquals("File not found: unknown (The system cannot find the file specified)",
            assertThrows(CodeChallengeException.class,
                () -> new FileProcessor("unknown", 0, 0).process())
                    .getMessage());

        assertEquals("File is empty.",
            assertThrows(CodeChallengeException.class,
                () -> new FileProcessor("src/test/resources/empty.txt", 0, 0).process())
                    .getMessage());

        assertEquals("Team name is empty.",
            assertThrows(CodeChallengeException.class,
                () -> new FileProcessor("src/test/resources/corrupted.txt", 0, 0).process())
                    .getMessage());
    }

    @Test
    public void testProcess()
            throws CodeChallengeException {

        List<Ranking> rankings = new MockProcessor(List.of(
                new GameResult(new TeamResult("team1", 2), new TeamResult("team2", 0))
        )).process().rank();

        assertRanking(rankings, "team1", 3);
        assertRanking(rankings, "team2", 0);

        rankings = new MockProcessor(List.of(
                new GameResult(new TeamResult("team1", 0), new TeamResult("team2", 0))
        )).process().rank();

        assertRanking(rankings, "team1", 1);
        assertRanking(rankings, "team2", 1);
    }

    private static void assertRanking(List<Ranking> rankings, String team, int points) {
        Optional<Ranking> team1 = rankings.stream().filter(r -> team.equals(r.team())).findFirst();
        team1.ifPresentOrElse(r -> assertEquals(points, r.points()), () -> fail("Missing " + team));
    }

}
