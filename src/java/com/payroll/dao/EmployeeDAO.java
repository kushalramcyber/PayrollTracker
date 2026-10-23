package com.payroll.dao;

import com.payroll.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    
    public boolean addEmployee(String name, String email, int deptId, double dailyRate) {
    String sql = "INSERT INTO employees (name, email, dept_id, daily_rate) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, name);
        ps.setString(2, email);

        if (deptId <= 0) {
            ps.setNull(3, java.sql.Types.INTEGER);
        } else {
            ps.setInt(3, deptId);
        }

        ps.setDouble(4, dailyRate);

        int rowsInserted = ps.executeUpdate();
        System.out.println("Employee Inserted Successfully! Rows affected: " + rowsInserted);
        return rowsInserted > 0;

    } catch (SQLException | ClassNotFoundException e) {
        System.err.println("JDBC Error during Employee Insert: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

    
    public List<String[]> getAllEmployees() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT e.emp_id, e.name, e.email, IFNULL(d.dept_name, 'N/A') AS dept_name, e.daily_rate " +
                     "FROM employees e LEFT JOIN departments d ON e.dept_id = d.dept_id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new String[]{
                    String.valueOf(rs.getInt("emp_id")),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("dept_name"),
                    String.valueOf(rs.getDouble("daily_rate"))
                });
            }
        } catch (Exception e) {
            System.err.println("Error reading employees: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    
    public boolean updateRate(int empId, double newRate) {
        String sql = "UPDATE employees SET daily_rate = ? WHERE emp_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setDouble(1, newRate);
            ps.setInt(2, empId);
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("SQL Exception on Update: " + e.getMessage());
            return false;
        }
    }

    
    public boolean deleteEmployee(int empId) {
        String sql = "DELETE FROM employees WHERE emp_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, empId);
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("SQL Exception on Delete: " + e.getMessage());
            return false;
        }
    }
}