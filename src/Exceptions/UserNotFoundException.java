package Exceptions;

/**
 * Exception thrown when a user is not found.
 */
public class UserNotFoundException extends Exception {
    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message The detail message.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
