package exception;

// EXCEPTION HANDLING: Custom exception when a course doesn't exist
public class CourseNotFoundException extends Exception {

    public CourseNotFoundException(String courseCode) {
        super("Course with code [" + courseCode + "] was not found.");
    }
}