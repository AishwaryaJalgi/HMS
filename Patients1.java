package com.learnJDBC;

import java.sql.*;
import java.util.Scanner;

public class Patients1 {

    private Connection connection;
    private Scanner scanner;

    public Patients1(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    // Add Patient
    public void addPatient() {
        System.out.println("Enter Patient's Name");
        String name = scanner.next();
        System.out.println("Enter Patient's Age");
        int age = scanner.nextInt();
        System.out.println("Enter Patient's Gender");
        String gender = scanner.next();

        String query = "INSERT INTO patients1 (name, age, gender) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Patient added successfully!");
            } else {
                System.out.println("Failed to add patient.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Patients
    public void viewPatients() {
        String query = "SELECT * FROM patients1";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            System.out.println("ID\tName\tAge\tGender");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                                   rs.getString("name") + "\t" +
                                   rs.getInt("age") + "\t" +
                                   rs.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get Patient by ID
    public boolean getPatientByID(int id) {
        String query = "SELECT * FROM patients1 WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
