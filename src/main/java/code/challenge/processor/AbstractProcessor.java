package code.challenge.processor;

import code.challenge.api.Processor;
import code.challenge.entities.GameResult;
import code.challenge.entities.Ranking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * An abstract class implementing the Processor interface that provides core functionality
 * for processing game results and calculating team rankings.
 * <p>
 * This class stores a configurable number of points to be assigned for winning and drawing
 * games. It also maintains a map of team names to their cumulative points.
 * <p>
 * Subclasses of this abstract class are required to define specific behavior for processing input data.
 */
public abstract class AbstractProcessor implements Processor {

    public enum Status {
        INITIALIZED,
        PROCESSED
    }

    protected Status status;

    // Making the processor more configurable just for fun.
    private final int winningPoints;
    private final int drawPoints;

    /**
     * A mapping of team names and their achieved points.
     */
    private final Map<String, Integer> rankings = new HashMap<>();

    public AbstractProcessor(int winningPoints, int drawPoints) {
        this.winningPoints = winningPoints;
        this.drawPoints = drawPoints;
        this.status = Status.INITIALIZED;
    }

    public void process(GameResult gameResult) {

        // Check if teams exist or not and add default value to map.
        rankings.putIfAbsent(gameResult.homeTeamResult().team(), 0);
        rankings.putIfAbsent(gameResult.awayTeamResult().team(), 0);

        // Add points to existing map value for the winning team.
        if (gameResult.homeTeamResult().goals() > gameResult.awayTeamResult().goals()) {
            rankings.merge(gameResult.homeTeamResult().team(), winningPoints, Integer::sum);
        } else if (gameResult.homeTeamResult().goals() < gameResult.awayTeamResult().goals()) {
            rankings.merge(gameResult.awayTeamResult().team(), winningPoints, Integer::sum);
        } else {
            // On draw, both teams get points.
            rankings.merge(gameResult.homeTeamResult().team(), drawPoints, Integer::sum);
            rankings.merge(gameResult.awayTeamResult().team(), drawPoints, Integer::sum);
        }
    }

    @Override
    public List<Ranking> rank() {

        if (status != Status.PROCESSED) {
            throw new IllegalStateException("Processor is not yet ready to rank.");
        }
        return rankings.entrySet().stream()
                .map(e -> new Ranking(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

}
