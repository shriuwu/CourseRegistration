package service;

import component.Course;
import exception.AlreadyRegisteredException;
import exception.CourseNotFoundException;
import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// SERVICE LAYER: Handles all registration business logic
// COLLECTIONS: Uses HashMap and ArrayList extensively
public class RegistrationService {

    // ENCAPSULATION: private data stores
    // Map of courseCode -> Course
    private Map<String, Course> courseRegistry = new HashMap<>();

    // Map of studentId -> List of courseCodes they're registered in
    private Map<String, List<String>> studentRegistrations = new HashMap<>();

    // Map of studentId -> Student object
    private Map<String, Student> studentRegistry = new HashMap<>();

    // ─── Course Management ───────────────────────────────────────────────

    public void addCourse(Course course) {
        courseRegistry.put(course.getCourseCode(), course);
    }

    public Course getCourse(String courseCode) throws CourseNotFoundException {
        Course course = courseRegistry.get(courseCode);
        if (course == null) {
            throw new CourseNotFoundException(courseCode); // EXCEPTION HANDLING
        }
        return course;
    }

    public Map<String, Course> getAllCourses() {
        return courseRegistry;
    }

    // ─── Student Management ──────────────────────────────────────────────

    public void addStudent(Student student) {
        studentRegistry.put(student.getStudentId(), student);
    }

    public Student getStudent(String studentId) {
        return studentRegistry.get(studentId);
    }

    public Map<String, Student> getAllStudents() {
        return studentRegistry;
    }

    // ─── Registration Logic ──────────────────────────────────────────────

    public void register(String studentId, String courseCode)
            throws AlreadyRegisteredException, CourseNotFoundException {

        // Check course exists — throws CourseNotFoundException if not
        getCourse(courseCode);

        // Get or create the student's registration list
        studentRegistrations.putIfAbsent(studentId, new ArrayList<>());
        List<String> courses = studentRegistrations.get(studentId);

        // Check for duplicate — EXCEPTION HANDLING
        if (courses.contains(courseCode)) {
            throw new AlreadyRegisteredException(studentId, courseCode);
        }

        courses.add(courseCode);
        System.out.println("✅ Registered Student [" + studentId
                + "] in Course [" + courseCode + "] successfully.");
    }

    public void cancelRegistration(String studentId, String courseCode)
            throws CourseNotFoundException {

        getCourse(courseCode); // validate course exists

        List<String> courses = studentRegistrations.get(studentId);
        if (courses == null || !courses.contains(courseCode)) {
            System.out.println("⚠️  Student [" + studentId
                    + "] was not registered in [" + courseCode + "].");
            return;
        }

        courses.remove(courseCode);
        System.out.println("❌ Cancelled registration of Student [" + studentId
                + "] from Course [" + courseCode + "].");
    }

    public void printStudentRegistrations(String studentId)
            throws CourseNotFoundException {

        Student student = studentRegistry.get(studentId);
        if (student == null) {
            System.out.println("Student not found: " + studentId);
            return;
        }

        List<String> courses = studentRegistrations.getOrDefault(
                studentId, new ArrayList<>());

        System.out.println("\n📋 Registrations for " + student);
        if (courses.isEmpty()) {
            System.out.println("   No courses registered.");
        } else {
            for (String code : courses) {
                Course c = getCourse(code);
                System.out.println("   → " + c.getCourseCode()
                        + " : " + c.getName());
            }
        }
    }

    public Map<String, List<String>> getAllRegistrations() {
        return studentRegistrations;
    }
}