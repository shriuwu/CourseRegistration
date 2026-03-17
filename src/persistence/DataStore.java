package persistence;

import component.Course;
import model.Student;
import service.RegistrationService;

import java.io.*;
import java.util.List;
import java.util.Map;

// DATA PERSISTENCE (Mentor's improvement): Saves and loads data to/from .txt files
public class DataStore {

    private static final String STUDENTS_FILE      = "students.txt";
    private static final String COURSES_FILE       = "courses.txt";
    private static final String REGISTRATIONS_FILE = "registrations.txt";

    // ─── SAVE ────────────────────────────────────────────────────────────

    public static void saveAll(RegistrationService service) {
        saveStudents(service);
        saveCourses(service);
        saveRegistrations(service);
        System.out.println("\n💾 All data saved to files successfully.");
    }

    private static void saveStudents(RegistrationService service) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student s : service.getAllStudents().values()) {
                // Format: studentId,name,email
                bw.write(s.getStudentId() + "," + s.getName() + "," + s.getEmail());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    private static void saveCourses(RegistrationService service) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(COURSES_FILE))) {
            for (Course c : service.getAllCourses().values()) {
                // Format: courseCode,name,description,credits
                bw.write(c.getCourseCode() + "," + c.getName() + ","
                        + c.getDescription() + "," + c.getCredits());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    private static void saveRegistrations(RegistrationService service) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(REGISTRATIONS_FILE))) {
            for (Map.Entry<String, List<String>> entry :
                    service.getAllRegistrations().entrySet()) {
                // Format: studentId,courseCode1,courseCode2,...
                String line = entry.getKey() + ","
                        + String.join(",", entry.getValue());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving registrations: " + e.getMessage());
        }
    }

    // ─── LOAD ────────────────────────────────────────────────────────────

    public static void loadStudents(RegistrationService service) {
        File file = new File(STUDENTS_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    service.addStudent(new Student(parts[0], parts[1], parts[2]));
                }
            }
            System.out.println("📂 Students loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }
}