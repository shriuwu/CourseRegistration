package exception;

// EXCEPTION HANDLING: Custom exception for duplicate registration
public class AlreadyRegisteredException extends Exception {

    public AlreadyRegisteredException(String studentId, String courseCode) {
        super("Student [" + studentId + "] is already registered in course ["
                + courseCode + "].");
    }
}