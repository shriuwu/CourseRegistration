package component;

// ABSTRACTION: Abstract class defines a contract that all course elements must follow
// This is the "Component" in the Composite Pattern
public abstract class CourseComponent {

    private String name;        // ENCAPSULATION: private field
    private String description; // ENCAPSULATION: private field

    // Constructor
    public CourseComponent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // ENCAPSULATION: Getters and Setters (controlled access)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // ABSTRACTION: Abstract methods — every subclass MUST implement these
    public abstract void display(String indent);
    public abstract int getTotalLessons();
    public abstract String getType();
}