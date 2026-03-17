# Course Registration System Using Composite Pattern for Course Modules

## Problem Statement
Managing course structures in educational institutions is complex due to 
hierarchical relationships between courses, modules, and lessons. Traditional 
systems struggle to uniformly handle these nested structures. This project 
addresses the problem by implementing the Composite Design Pattern in Java, 
allowing courses to contain modules and modules to contain lessons — all treated 
uniformly through a common interface. The system enables students to register 
for courses, handles duplicate and invalid registrations through custom 
exceptions, persists data using file I/O, and provides a clean hierarchical 
display of the entire course structure.

---

## Target User
- **Students** who need to register for or cancel courses
- **Academic administrators** who manage course structures and modules
- **Developers** learning Composite Design Pattern with real-world OOP application

---

## Core Features
- Build hierarchical course structures (Course → Module → Lesson)
- Display full course tree with all nested components
- Register students into courses
- Cancel student registrations
- Prevent duplicate registrations with custom exception handling
- Handle invalid course codes with custom exception handling
- Save and load student/course/registration data using file I/O
- Track total lessons and credits per course

---

## OOP Concepts Used

| Concept | Where Applied |
|---|---|
| **Abstraction** | `CourseComponent` abstract class defines contract for all components |
| **Encapsulation** | Private fields with getters/setters in all classes |
| **Inheritance** | `Course`, `Module`, `Lesson` all extend `CourseComponent` |
| **Polymorphism** | `display()` and `getTotalLessons()` behave differently per class |
| **Exception Handling** | `AlreadyRegisteredException`, `CourseNotFoundException` |
| **Collections** | `HashMap` and `ArrayList` used in `RegistrationService` |
| **Data Persistence** | `DataStore.java` saves/loads data to/from `.txt` files |

---

## Architecture Description

The system follows the **Composite Design Pattern**:
```
CourseComponent (Abstract Base Class)
│
├── Course (Root Node)
│   └── contains → Module (Composite Node)
│                   └── contains → Lesson (Leaf Node)
│
├── RegistrationService (Business Logic Layer)
│   ├── Manages course registry (HashMap)
│   ├── Manages student registry (HashMap)
│   └── Manages registrations (HashMap + ArrayList)
│
├── DataStore (Persistence Layer)
│   ├── Saves students to students.txt
│   ├── Saves courses to courses.txt
│   └── Saves registrations to registrations.txt
│
└── Custom Exceptions (Exception Layer)
    ├── AlreadyRegisteredException
    └── CourseNotFoundException
```

**Flow:**
1. Courses and modules are built using the Composite Pattern
2. Students are added to the system
3. RegistrationService handles all registration logic
4. Custom exceptions handle error cases
5. DataStore persists all data to files

---

## How to Run

### Prerequisites
- Java JDK 11 or higher
- Git

### Steps

**1. Clone the repository:**
```bash
git clone https://github.com/shriuwu/CourseRegistration.git
cd CourseRegistration
```

**2. Create output directory:**
```bash
mkdir out
```

**3. Compile all source files:**
```bash
javac -d out src/component/CourseComponent.java src/component/Lesson.java src/component/Module.java src/component/Course.java src/model/Student.java src/exception/AlreadyRegisteredException.java src/exception/CourseNotFoundException.java src/service/RegistrationService.java src/persistence/DataStore.java src/Main.java
```

**4. Run the program:**
```bash
java -cp out Main
```

### Expected Output
- Full course structure display
- Student registration confirmations
- Exception handling demonstrations
- Data saved to `.txt` files

---

## Project Management
This project uses Git for version control and development tracking.
All changes are committed with meaningful messages to maintain a clear 
development history on GitHub.
```

---

Save the file (`Ctrl + S`) then run in VS Code terminal:
```
git add README.md
```
```
git commit -m "Update README with complete project documentation"
```
```
git push