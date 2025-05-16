package code.challenge.api;

import code.challenge.entities.Ranking;
import code.challenge.exception.CodeChallengeException;

import java.util.List;

/**
 * Represents a processor responsible for analyzing game results, calculating rankings,
 * and providing a summary of rankings for teams based on their performance.
 * <p>
 * Using an interface is probably overkill for this task, but hey, you said "maintainable" didn't you?
 */
public interface Processor {

    /**
     * Processes the input data or resources required for ranking calculations and returns the processor instance
     * for further operations such as ranking.
     *
     * @return the processor instance after processing the necessary data or input.
     * @throws CodeChallengeException if any error occurs during the processing, such as missing or invalid input.
     */
    Processor process()
            throws CodeChallengeException;

    /**
     * Calculates and returns a list of rankings for teams based on their performance in the processed game results.
     * The rankings are sorted in descending order of points and contain team names along with their respective points.
     *
     * @return a list of {@code Ranking} objects representing the sorted rankings of teams.
     * @throws CodeChallengeException if an error occurs while calculating the rankings, such as missing or invalid data.
     */
    List<Ranking> rank()
            throws CodeChallengeException;

}
