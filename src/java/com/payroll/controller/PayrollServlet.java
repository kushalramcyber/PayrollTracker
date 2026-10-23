
package com.payroll.controller;

import com.payroll.util.DBConnection;
import com.payroll.dao.AttendanceDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PayrollServlet extends HttpServlet {

    private AttendanceDAO attendanceDAO = new AttendanceDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            int empId = Integer.parseInt(request.getParameter("empId"));
            String monthYear = request.getParameter("monthYear");
            int presentDays = Integer.parseInt(request.getParameter("presentDays"));
            int halfDays = Integer.parseInt(request.getParameter("halfDays"));
            double dailyRate = Double.parseDouble(request.getParameter("dailyRate"));

           
            attendanceDAO.logAttendance(empId, monthYear, presentDays, halfDays);

            
            double totalWorkDays = presentDays + (halfDays * 0.5);
            double grossSalary = totalWorkDays * dailyRate;
            double deductions = grossSalary * 0.10; 
            double netSalary = grossSalary - deductions;

            
            request.setAttribute("empId", empId);
            request.setAttribute("monthYear", monthYear);
            request.setAttribute("grossSalary", String.format("%.2f", grossSalary));
            request.setAttribute("deductions", String.format("%.2f", deductions));
            request.setAttribute("netSalary", String.format("%.2f", netSalary));

            
            request.getRequestDispatcher("salaryResult.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("EmployeeServlet");
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
