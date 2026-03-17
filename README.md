# Course Registration System
## Using Composite Pattern for Course Modules

A Java-based Course Registration System demonstrating the **Composite Design Pattern** and core OOP principles.

---

## OOP Concepts Used
- **Abstraction** — `CourseComponent` abstract class defines contract for all components
- **Encapsulation** — Private fields with getters/setters in all classes
- **Inheritance** — `Course`, `Module`, `Lesson` all extend `CourseComponent`
- **Polymorphism** — `display()` method behaves differently for each class
- **Exception Handling** — Custom exceptions for duplicate/invalid registrations
- **Collections** — `HashMap` and `ArrayList` for data management
- **Data Persistence** — File I/O to save/load student and course data

---

## Composite Pattern Structure
CourseComponent (Abstract)
├── Course (Root Node)
│   └── Module (Composite Node)
│       └── Lesson (Leaf Node)

---

## Project Structure
src/
├── component/
│   ├── CourseComponent.java
│   ├── Course.java
│   ├── Module.java
│   └── Lesson.java
├── model/
│   └── Student.java
├── exception/
│   ├── AlreadyRegisteredException.java
│   └── CourseNotFoundException.java
├── service/
│   └── RegistrationService.java
├── persistence/
│   └── DataStore.java
└── Main.java

---

## How to Run
Compile:
javac -d out src/component/*.java src/model/*.java src/exception/*.java src/service/*.java src/persistence/*.java src/Main.java

Run:
java -cp out Main

---

## Project Management
Version control using Git for development tracking and collaboration.
```

### Step 3:
Click **File → Save As**

### Step 4:
In the Save As window:
1. Navigate to `C:\Users\USER\Downloads\CourseRegistration`
2. Change **"Save as type"** to **"All Files"** ⬅️ very important!
3. In filename type: `README.md`
4. Click **Save**

---

### Step 5 — Back in VS Code terminal:
```
git add README.md
```
```
git commit -m "Add README with project documentation"
```
```
git push