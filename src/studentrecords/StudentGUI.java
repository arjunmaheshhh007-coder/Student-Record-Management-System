package studentrecords;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
public class StudentGUI extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField courseField;
    private JTextField marksField;

    private JTable table;
    private DefaultTableModel tableModel;

    private JButton addButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewAllButton;
    private JButton clearButton;
    private JButton saveButton;
    private JButton loadButton;

    private StudentManager manager;

    public StudentGUI() {
        manager = new StudentManager();

        setTitle("Student Record Management System");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        inputPanel.add(courseField);

        inputPanel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        inputPanel.add(marksField);

        add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Name", "Age", "Course", "Marks"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

// ID
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

// Age
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

// Marks
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));

        addButton = new JButton("Add");
        searchButton = new JButton("Search");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        viewAllButton = new JButton("View All");
        clearButton = new JButton("Clear");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewAllButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addStudent());
        searchButton.addActionListener(e -> searchStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        viewAllButton.addActionListener(e -> viewAllStudents());
        clearButton.addActionListener(e -> clearFields());
        saveButton.addActionListener(e -> saveStudents());
        loadButton.addActionListener(e -> loadStudents());
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText();
            double marks = Double.parseDouble(marksField.getText());

            if (name.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Course cannot be empty.");
                return;
            }

            if (age < 16 || age > 100) {
                JOptionPane.showMessageDialog(this, "Age must be between 16 and 100.");
                return;
            }

            if (marks < 0 || marks > 100) {
                JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100.");
                return;
            }

            Student student = new Student(id, name, age, course, marks);
            boolean added = manager.addStudent(student);

            if (added) {
                JOptionPane.showMessageDialog(this, "Student added successfully.");
                clearFields();
                viewAllStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Student ID already exists.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.");
        }
    }

    private void searchStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            Student student = manager.searchStudent(id);

            if (student != null) {
                nameField.setText(student.getName());
                ageField.setText(String.valueOf(student.getAge()));
                courseField.setText(student.getCourse());
                marksField.setText(String.valueOf(student.getMarks()));

                tableModel.setRowCount(0);
                tableModel.addRow(new Object[]{
                        student.getId(),
                        student.getName(),
                        student.getAge(),
                        student.getCourse(),
                        student.getMarks()
                });
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid student ID.");
        }
    }

    private void updateStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText();
            double marks = Double.parseDouble(marksField.getText());

            boolean updated = manager.updateStudent(id, name, age, course, marks);

            if (updated) {
                JOptionPane.showMessageDialog(this, "Student updated successfully.");
                clearFields();
                viewAllStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.");
        }
    }

    private void deleteStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            boolean deleted = manager.deleteStudent(id);

            if (deleted) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully.");
                clearFields();
                viewAllStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid student ID.");
        }
    }

    private void viewAllStudents() {
        tableModel.setRowCount(0);

        for (Student student : manager.getAllStudents()) {
            Object[] row = {
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getCourse(),
                    student.getMarks()
            };
            tableModel.addRow(row);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");
        marksField.setText("");
        tableModel.setRowCount(0);
    }

    private void saveStudents() {
        try {
            manager.saveToFile("students.txt");
            JOptionPane.showMessageDialog(this, "Students saved successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
        }
    }

    private void loadStudents() {
        try {
            manager.loadFromFile("students.txt");
            JOptionPane.showMessageDialog(this, "Students loaded successfully.");
            viewAllStudents();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading file: " + ex.getMessage());
        }
    }
}