
package com.payroll.controller;

import com.payroll.dao.EmployeeDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EmployeeServlet extends HttpServlet {
    
    private EmployeeDAO employeeDAO = new EmployeeDAO();
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        
        List<String[]> employeeList = employeeDAO.getAllEmployees();
        request.setAttribute("employees", employeeList);
        request.getRequestDispatcher("employeeList.jsp").forward(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            int deptId = Integer.parseInt(request.getParameter("deptId"));
            double dailyRate = Double.parseDouble(request.getParameter("dailyRate"));

            employeeDAO.addEmployee(name, email, deptId, dailyRate);
        } else if ("delete".equalsIgnoreCase(action)) {
            int empId = Integer.parseInt(request.getParameter("empId"));
            employeeDAO.deleteEmployee(empId);
        }

        response.sendRedirect("EmployeeServlet");
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
