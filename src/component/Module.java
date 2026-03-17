package component;

import java.util.ArrayList;
import java.util.List;

// INHERITANCE: Module extends CourseComponent
// Module is a COMPOSITE node — it can contain Lessons or other Modules
public class Module extends CourseComponent {

    // COLLECTIONS: ArrayList stores child components
    private List<CourseComponent> children = new ArrayList<>();

    public Module(String name, String description) {
        super(name, description);
    }

    // Add a child (Lesson or another Module)
    public void addComponent(CourseComponent component) {
        children.add(component);
    }

    // Remove a child
    public void removeComponent(CourseComponent component) {
        children.remove(component);
    }

    public List<CourseComponent> getChildren() {
        return children;
    }

    // POLYMORPHISM: display() for Module shows all its children recursively
    @Override
    public void display(String indent) {
        System.out.println(indent + "📦 Module: " + getName()
                + " | " + getDescription());
        for (CourseComponent component : children) {
            component.display(indent + "    "); // recursive call
        }
    }

    // Recursively count all lessons inside this module
    @Override
    public int getTotalLessons() {
        int total = 0;
        for (CourseComponent component : children) {
            total += component.getTotalLessons();
        }
        return total;
    }

    @Override
    public String getType() {
        return "Module";
    }
}