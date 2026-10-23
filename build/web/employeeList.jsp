<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="p-4 bg-white text-dark">
    <div class="container">
        
        <!-- Header -->
        <div class="d-flex justify-content-between align-items-center mb-4 pb-2 border-bottom">
            <h3 class="h4 text-dark mb-0">Employee & Payroll Tracker</h3>
            <div>
                <span class="me-3">Logged in as: <strong><c:out value="${sessionScope.user}"/></strong></span>
                <a href="LogoutServlet" class="btn btn-outline-dark btn-sm">Logout</a>
            </div>
        </div>

        <!-- Add Employee Form -->
        <div class="mb-4 p-3 border">
            <h5 class="h6 mb-3 border-bottom pb-2">REGISTER NEW EMPLOYEE</h5>
            <form action="EmployeeServlet" method="POST" class="row g-3">
                <input type="hidden" name="action" value="add">
                <div class="col-md-3">
                    <label class="form-label">Full Name</label>
                    <input type="text" name="name" class="form-control" required>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Department</label>
                    <select name="deptId" class="form-select" required>
                        <option value="1">1 - Engineering</option>
                        <option value="2">2 - Human Resources</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Daily Rate (₹)</label>
                    <input type="number" step="0.01" name="dailyRate" class="form-control" required>
                </div>
                <div class="col-md-2 d-flex align-items-end">
                    <button type="submit" class="btn btn-dark w-100">Add Employee</button>
                </div>
            </form>
        </div>

        <!-- Registered Employees Table -->
        <div class="mb-5">
            <h5 class="h6 mb-3 border-bottom pb-2">REGISTERED EMPLOYEES</h5>
            <table class="table table-bordered align-middle">
                <thead>
                    <tr class="table-light">
                        <th>Emp ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Department</th>
                        <th>Daily Rate</th>
                        <th style="width: 100px;">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty employees}">
                            <c:forEach var="emp" items="${employees}">
                                <tr>
                                    <td><c:out value="${emp[0]}"/></td>
                                    <td><c:out value="${emp[1]}"/></td>
                                    <td><c:out value="${emp[2]}"/></td>
                                    <td><c:out value="${emp[3]}"/></td>
                                    <td>₹<c:out value="${emp[4]}"/></td>
                                    <td>
                                        <form action="EmployeeServlet" method="POST" class="m-0">
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="empId" value="${emp[0]}">
                                            <button type="submit" class="btn btn-outline-dark btn-sm w-100" onclick="return confirm('Delete employee?')">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" class="text-center text-muted py-3">No employees registered yet.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

        <!-- Payroll Processing Section -->
        <div class="p-3 border">
            <h5 class="h6 mb-3 border-bottom pb-2">CALCULATE EMPLOYEE PAYROLL</h5>
            <form action="PayrollServlet" method="POST" class="row g-3">
                <div class="col-md-2">
                    <label class="form-label">Emp ID</label>
                    <input type="number" name="empId" class="form-control" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label">YYYY-MM</label>
                    <input type="text" name="monthYear" class="form-control" required pattern="\d{4}-\d{2}">
                </div>
                <div class="col-md-2">
                    <label class="form-label">Days Present</label>
                    <input type="number" name="presentDays" class="form-control" required min="0" max="31">
                </div>
                <div class="col-md-2">
                    <label class="form-label">Half Days</label>
                    <input type="number" name="halfDays" class="form-control" required min="0" max="31">
                </div>
                <div class="col-md-2">
                    <label class="form-label">Daily Rate (₹)</label>
                    <input type="number" step="0.01" name="dailyRate" class="form-control" required>
                </div>
                <div class="col-md-2 d-flex align-items-end">
                    <button type="submit" class="btn btn-dark w-100">Process Salary</button>
                </div>
            </form>
        </div>

    </div>
</body>
</html>