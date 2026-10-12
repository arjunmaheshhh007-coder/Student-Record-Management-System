package studentrecords;

import java.util.ArrayList;
import java.io.*;

public class StudentManager {

    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                return false;
            }
        }
        students.add(student);
        return true;
    }

    public Student searchStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean updateStudent(int id, String name, int age, String course, double marks) {
        Student student = searchStudent(id);

        if (student != null) {
            student.setName(name);
            student.setAge(age);
            student.setCourse(course);
            student.setMarks(marks);
            return true;
        }

        return false;
    }

    public boolean deleteStudent(int id) {
        Student student = searchStudent(id);

        if (student != null) {
            students.remove(student);
            return true;
        }

        return false;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        for (Student s : students) {
            writer.write(s.getId() + "," + s.getName() + "," + s.getAge() + "," + s.getCourse() + "," + s.getMarks());
            writer.newLine();
        }

        writer.close();
    }

    public void loadFromFile(String filename) throws IOException {
        students.clear();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            int age = Integer.parseInt(parts[2]);
            String course = parts[3];
            double marks = Double.parseDouble(parts[4]);

            students.add(new Student(id, name, age, course, marks));
        }

        reader.close();
    }
}