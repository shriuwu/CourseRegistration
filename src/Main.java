import component.Course;
import component.Lesson;
import component.Module;
import exception.AlreadyRegisteredException;
import exception.CourseNotFoundException;
import model.Student;
import persistence.DataStore;
import service.RegistrationService;

public class Main {

    public static void main(String[] args) {

        RegistrationService service = new RegistrationService();

        // ── Load persisted students from file (if any) ──────────────────
        DataStore.loadStudents(service);

        // ── Build Course Structure (Composite Pattern) ───────────────────

        // Lessons (LEAF nodes)
        Lesson l1 = new Lesson("Intro to Java",       "Basics of Java",         60);
        Lesson l2 = new Lesson("OOP Concepts",        "Classes and Objects",    90);
        Lesson l3 = new Lesson("Data Structures",     "Arrays, Lists, Maps",   120);
        Lesson l4 = new Lesson("Algorithms",          "Sorting and Searching",  90);
        Lesson l5 = new Lesson("Database Basics",     "SQL fundamentals",       75);
        Lesson l6 = new Lesson("JDBC",                "Java DB Connectivity",   60);

        // Modules (COMPOSITE nodes)
        Module m1 = new Module("Core Java",    "Fundamentals of Java programming");
        m1.addComponent(l1);
        m1.addComponent(l2);

        Module m2 = new Module("DSA",          "Data Structures and Algorithms");
        m2.addComponent(l3);
        m2.addComponent(l4);

        Module m3 = new Module("Database",     "Database and connectivity");
        m3.addComponent(l5);
        m3.addComponent(l6);

        // Course (ROOT node)
        Course course1 = new Course("CS101", "B.Tech CSE Sem 1",
                "First semester Computer Science", 20);
        course1.addModule(m1);
        course1.addModule(m2);

        Course course2 = new Course("CS102", "B.Tech CSE Sem 2",
                "Second semester Computer Science", 18);
        course2.addModule(m3);
        // New Course CS103 — Web Development
        Lesson l7 = new Lesson("HTML & CSS",       "Web page structure and styling",  60);
        Lesson l8 = new Lesson("JavaScript",       "Dynamic web programming",         90);
        Lesson l9 = new Lesson("React Basics",     "Component-based UI development",  90);
        Lesson l10 = new Lesson("Node.js",         "Server-side JavaScript",          75);

        Module m4 = new Module("Frontend",  "Client-side web development");
        m4.addComponent(l7);
        m4.addComponent(l8);
        m4.addComponent(l9);

        Module m5 = new Module("Backend",   "Server-side web development");
        m5.addComponent(l10);

        Course course3 = new Course("CS103", "Web Development",
                "Full stack web development fundamentals", 22);
        course3.addModule(m4);
        course3.addModule(m5);
        // Add courses to service
        service.addCourse(course1);
        service.addCourse(course2);
        service.addCourse(course3);
        
        // ── Display Course Structure ──────────────────────────────────────
        System.out.println("========== COURSE STRUCTURE ==========");
        course1.display("");
        System.out.println();
        course2.display("");

        // ── Add Students ─────────────────────────────────────────────────
        Student s1 = new Student("STU001", "Rahul Sharma",  "rahul@email.com");
        Student s2 = new Student("STU002", "Priya Mehta",   "priya@email.com");
        Student s3 = new Student("STU003", "Arjun Das",     "arjun@email.com");

        service.addStudent(s1);
        service.addStudent(s2);
        service.addStudent(s3);
        Student s4 = new Student("STU004", "Shri Agarwal", "shri@email.com");
        service.addStudent(s4);

        // ── Register Students ─────────────────────────────────────────────
        System.out.println("\n========== REGISTRATIONS ==========");
        try {
            service.register("STU001", "CS101");
            service.register("STU001", "CS102");
            service.register("STU002", "CS101");
            service.register("STU001", "CS101"); // ← triggers AlreadyRegisteredException
            service.register("STU003", "CS103");
            service.register("STU004", "CS101");
            service.register("STU004", "CS102");
            service.register("STU004", "CS103");
        } catch (AlreadyRegisteredException e) {
            System.out.println("⚠️  Error: " + e.getMessage());
        } catch (CourseNotFoundException e) {
            System.out.println("⚠️  Error: " + e.getMessage());
        }

        // ── Try registering in non-existent course ────────────────────────
        try {
            service.register("STU003", "CS999"); // ← triggers CourseNotFoundException
        } catch (AlreadyRegisteredException | CourseNotFoundException e) {
            System.out.println("⚠️  Error: " + e.getMessage());
        }

        // ── Print Registrations ───────────────────────────────────────────
        System.out.println("\n========== STUDENT REGISTRATION DETAILS ==========");
        try {
            service.printStudentRegistrations("STU001");
            service.printStudentRegistrations("STU002");
            service.printStudentRegistrations("STU003");
        } catch (CourseNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ── Cancel a Registration ─────────────────────────────────────────
        System.out.println("\n========== CANCEL REGISTRATION ==========");
        try {
            service.cancelRegistration("STU001", "CS102");
            service.printStudentRegistrations("STU001");
        } catch (CourseNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
       // ── Display all courses and students using new methods ────────────
        service.displayAllCourses();
        service.displayAllStudents();
        // ── Save all data to files (Data Persistence) ─────────────────────
        System.out.println("\n========== SAVING DATA ==========");
        DataStore.saveAll(service);
    }
}