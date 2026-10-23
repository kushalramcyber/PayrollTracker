<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payroll Slip Summary</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-white p-5">
    <div class="container col-md-6 border p-4 mt-4">
        <h4 class="mb-3 border-bottom pb-2">Payroll Summary Statement</h4>
        
        <table class="table table-bordered">
            <tr>
                <th>Employee ID</th>
                <td>${empId}</td>
            </tr>
            <tr>
                <th>Pay Period</th>
                <td>${monthYear}</td>
            </tr>
            <tr>
                <th>Gross Salary</th>
                <td>₹${grossSalary}</td>
            </tr>
            <tr>
                <th>Deductions (10% Tax)</th>
                <td>-₹${deductions}</td>
            </tr>
            <tr class="table-light">
                <th>Net Payable Salary</th>
                <td><strong>₹${netSalary}</strong></td>
            </tr>
        </table>

        <a href="EmployeeServlet" class="btn btn-outline-dark mt-2">Return to Dashboard</a>
    </div>
</body>
</html>