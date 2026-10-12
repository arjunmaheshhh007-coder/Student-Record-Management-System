# Student Record Management System

## About the Project

The Student Record Management System is a Java desktop application designed to manage student records through a graphical user interface.

The application allows users to add, search, update, delete, display, save and load student records. It was developed as part of my first-year Computer Science studies at Cardiff Metropolitan University.

## Technologies Used

- **Java** — application logic and object-oriented programming
- **Java Swing** — graphical user interface
- **Java Collections** — storing and managing student objects
- **File I/O** — saving and loading student records

## Features

- Add student records
- Search for students using their IDs
- Update existing student information
- Delete student records
- Display all student records in a table
- Save records to a file
- Load records from a file
- Validate selected inputs, including age and marks
- Prevent duplicate student IDs

## Student Information

Each record contains:

- Student ID
- Name
- Age
- Course
- Marks

## Project Structure

```text
Student-Record-Management-System/
├── src/
│   └── studentrecords/
│       ├── Main.java
│       ├── Student.java
│       ├── StudentGUI.java
│       └── StudentManager.java
├── students.txt
├── .gitignore
└── README.md
```

Use fictional sample records in `students.txt` before publishing the repository. Do not publish real student IDs or personal information.

## How to Run

### Requirements

- Java Development Kit (JDK)
- An IDE such as IntelliJ IDEA, or a Java compiler

### Running the application

1. Download or clone this repository.
2. Open the project in your preferred Java IDE.
3. Ensure the source files are in the `studentrecords` package.
4. Compile the Java source files.
5. Run `Main.java` to launch the application.

The application opens a desktop window where student records can be managed.

## Object-Oriented Design

The project separates its responsibilities across several classes:

- **Main.java** — starts the application.
- **Student.java** — represents a student and stores student attributes.
- **StudentGUI.java** — builds the interface and handles user interactions.
- **StudentManager.java** — manages student records and file operations.

## What I Learned

- Java programming fundamentals
- Classes, objects and encapsulation
- Separating application logic from the GUI
- Building interfaces using Java Swing
- Working with collections
- Reading and writing files
- Validating user input

## Future Improvements

- Improve input validation and exception handling
- Add more robust file parsing
- Introduce database integration
- Improve the interface design
- Add automated unit tests

## Academic Context

**Course:** BSc (Hons) Computer Science  
**University:** Cardiff Metropolitan University  
**Project:** First-year academic project

This repository demonstrates my development in Java and object-oriented application design.
