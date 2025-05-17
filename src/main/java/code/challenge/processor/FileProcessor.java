package code.challenge.processor;

import code.challenge.api.Processor;
import code.challenge.dto.GameResult;
import code.challenge.exception.CodeChallengeException;
import code.challenge.utilities.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class reads game results from a file for processing and calculating team rankings
 * based on game results. 3 points are awarded to a winning team, and 1 point is awarded for a draw.
 * <p>
 * Game results are summarized into rankings.
 */
public class FileProcessor extends AbstractProcessor {

    private final String fileName;

    public FileProcessor(String fileName, int winningPoints, int drawPoints) {
        super(winningPoints, drawPoints);
        this.fileName = fileName;
    }

    @Override
    public Processor process()
            throws CodeChallengeException {

        if (status != Status.INITIALIZED) {
            throw new IllegalStateException("Processor is not yet ready to rank.");
        }
        if (fileName == null) {
            throw new CodeChallengeException("File name is not available.");
        }

        try {

            // Read the file.
            Scanner fileScanner = new Scanner(new File(fileName));
            if (!fileScanner.hasNextLine()) {
                throw new CodeChallengeException("File is empty.");
            }

            // Process each line in the file.
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                GameResult result = Utils.processLine(line);
                process(result);
            }

            fileScanner.close();
        } catch (FileNotFoundException fnfe) {
            throw new CodeChallengeException("File not found: " + fnfe.getMessage());
        }

        this.status = Status.PROCESSED;
        return this;
    }

}
