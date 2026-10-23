<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payroll System - Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script>
        function validateLoginForm() {
            var user = document.forms["loginForm"]["username"].value;
            var pass = document.forms["loginForm"]["password"].value;
            if (user.trim() === "" || pass.trim() === "") {
                alert("Please enter both Username and Password.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body class="bg-white text-dark p-5">
    <div class="container style-box border p-4 col-md-4 mx-auto mt-5">
        <h4 class="mb-3 text-center border-bottom pb-2">Admin Login</h4>
        
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="p-2 border border-dark text-center mb-3">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>

        <form name="loginForm" action="LoginServlet" method="POST" onsubmit="return validateLoginForm()">
            <div class="mb-3">
                <label class="form-label">Username:</label>
                <input type="text" name="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Password:</label>
                <input type="password" name="password" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-dark w-100">Login</button>
        </form>
    </div>
</body>
</html>