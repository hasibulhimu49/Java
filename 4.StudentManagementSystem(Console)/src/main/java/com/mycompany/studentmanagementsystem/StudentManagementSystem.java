package com.mycompany.studentmanagementsystem;

import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/student_management";
    private static final String USER = "root";  // Replace with your MySQL username
    private static final String PASSWORD = "";  // Replace with your MySQL password

    // Method to connect to the database
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to add a student to the database
    private static void addStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume the leftover newline

        System.out.print("Enter student grade: ");
        String grade = scanner.nextLine();

        String query = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, grade);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " student(s) added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view all students in the database
    private static void viewStudents() {
        String query = "SELECT * FROM students";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("ID\tName\t\tAge\tGrade");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String grade = resultSet.getString("grade");
                System.out.println(id + "\t" + name + "\t\t" + age + "\t" + grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a student by ID
    private static void deleteStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();

        String query = "DELETE FROM students WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a student's information
    private static void updateStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume the leftover newline

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume the leftover newline

        System.out.print("Enter new grade: ");
        String grade = scanner.nextLine();

        String query = "UPDATE students SET name = ?, age = ?, grade = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, grade);
            preparedStatement.setInt(4, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main menu for interacting with the system
    public static void main(String[] args) {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n----- Student Management System -----");
            System.out.println("1. Add a student");
            System.out.println("2. View all students");
            System.out.println("3. Delete a student");
            System.out.println("4. Update student information");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

