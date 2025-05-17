package code.challenge.processor;

import code.challenge.MainApplication;
import code.challenge.api.Processor;
import code.challenge.dto.GameResult;

import java.util.List;

/**
 * This is a mock implementation of the Processor interface for testing purposes.
 */
public class MockProcessor extends AbstractProcessor {

    private final List<GameResult> results;

    public MockProcessor(List<GameResult> results) {
        super(MainApplication.WIN_POINTS, MainApplication.DRAW_POINTS);
        this.results = results;
    }

    public Processor process() {
        // Process each game result.
        this.results.forEach(this::process);

        this.status = Status.PROCESSED;
        return this;
    }
}
