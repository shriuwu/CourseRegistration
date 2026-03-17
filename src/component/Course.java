package component;

import java.util.ArrayList;
import java.util.List;

// INHERITANCE: Course extends CourseComponent
// Course is the ROOT node in Composite Pattern — top-level container
public class Course extends CourseComponent {

    private String courseCode;   // ENCAPSULATION
    private int credits;         // ENCAPSULATION

    // COLLECTIONS: stores modules inside this course
    private List<CourseComponent> modules = new ArrayList<>();

    public Course(String courseCode, String name, String description, int credits) {
        super(name, description);
        this.courseCode = courseCode;
        this.credits = credits;
    }

    // ENCAPSULATION: Getters and Setters
    public String getCourseCode() {
        return courseCode;
    }

    public int getCredits() {
        return credits;
    }

    public void addModule(CourseComponent module) {
        modules.add(module);
    }

    public void removeModule(CourseComponent module) {
        modules.remove(module);
    }

    public List<CourseComponent> getModules() {
        return modules;
    }

    // POLYMORPHISM: display() for Course shows courseCode + all modules
    @Override
    public void display(String indent) {
        System.out.println(indent + "🎓 Course [" + courseCode + "]: " + getName()
                + " | " + getDescription()
                + " | Credits: " + credits
                + " | Total Lessons: " + getTotalLessons());
        for (CourseComponent module : modules) {
            module.display(indent + "  ");
        }
    }

    // Count all lessons across all modules
    @Override
    public int getTotalLessons() {
        int total = 0;
        for (CourseComponent module : modules) {
            total += module.getTotalLessons();
        }
        return total;
    }

    @Override
    public String getType() {
        return "Course";
    }
}