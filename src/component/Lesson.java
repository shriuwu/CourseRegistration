package component;

// INHERITANCE: Lesson extends CourseComponent (is-a relationship)
// Lesson is the LEAF node in Composite Pattern — smallest unit, no children
public class Lesson extends CourseComponent {

    private int durationMinutes; // ENCAPSULATION: private field

    public Lesson(String name, String description, int durationMinutes) {
        super(name, description); // calls parent constructor
        this.durationMinutes = durationMinutes;
    }

    // ENCAPSULATION
    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    // POLYMORPHISM: display() behaves differently for Lesson vs Module vs Course
    @Override
    public void display(String indent) {
        System.out.println(indent + "📖 Lesson: " + getName()
                + " | " + getDescription()
                + " | Duration: " + durationMinutes + " mins");
    }

    // A lesson itself counts as 1 lesson
    @Override
    public int getTotalLessons() {
        return 1;
    }

    @Override
    public String getType() {
        return "Lesson";
    }
}