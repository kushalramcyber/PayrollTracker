package com.payroll.dao;

import com.payroll.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AttendanceDAO {

    public boolean logAttendance(int empId, String monthYear, int presentDays, int halfDays) throws ClassNotFoundException {
        String sql = "INSERT INTO attendance (emp_id, work_date, status) VALUES (?, ?, ?)";
        int dayCounter = 1;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < presentDays; i++) {
                String formattedDate = String.format("%s-%02d", monthYear, dayCounter++);
                stmt.setInt(1, empId);
                stmt.setString(2, formattedDate);
                stmt.setString(3, "PRESENT");
                stmt.addBatch();
            }

            for (int i = 0; i < halfDays; i++) {
                String formattedDate = String.format("%s-%02d", monthYear, dayCounter++);
                stmt.setInt(1, empId);
                stmt.setString(2, formattedDate);
                stmt.setString(3, "HALF-DAY");
                stmt.addBatch();
            }

            stmt.executeBatch();
            return true;

        } catch (SQLException e) {
            System.err.println("Database Error logging attendance: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}