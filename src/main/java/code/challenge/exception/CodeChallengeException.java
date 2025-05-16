package code.challenge.exception;

/**
 * CodeChallengeException is not a runtime exception because we want to force
 * the user to handle the exception.
 */
public class CodeChallengeException extends Exception {

    public CodeChallengeException(String message) {
        super(message);
    }

}
