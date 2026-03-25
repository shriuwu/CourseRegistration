package component;

/**
 * COMPOSITE PATTERN - Component Interface
 * This abstract class is the base for all course elements.
 * It defines the common contract that Course, Module, and Lesson must follow.
 *
 * OOP CONCEPTS:
 * - Abstraction: Abstract class with abstract methods
 * - Encapsulation: Private fields with getters/setters
 */
public abstract class CourseComponent {

    // ENCAPSULATION: private fields — only accessible via getters/setters
    private String name;
    private String description;

    // Constructor
    public CourseComponent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // ENCAPSULATION: Controlled access to private fields
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    /**
     * ABSTRACTION: Forces all subclasses to implement their own display logic
     * POLYMORPHISM: Each subclass displays differently
     */
    public abstract void display(String indent);

    /**
     * ABSTRACTION: Forces all subclasses to count their lessons
     */
    public abstract int getTotalLessons();

    /**
     * ABSTRACTION: Forces all subclasses to identify their type
     */
    public abstract String getType();
}
