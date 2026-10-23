# Employee Attendance & Payroll Tracker

A lightweight, beginner-friendly web application built using Java Servlets, JSP, JDBC, and MySQL. The system provides secure administrative access, automated daily attendance logging, dynamic employee management, and net salary computation in Indian Rupees (₹/INR).

---

## 📌 Features

* **Session Management & Security**: Admin login and session control (`admin` / `admin123`) to prevent unauthorized portal access.
* **Employee Profile Management**: Dynamic CRUD operations (Add, View, and Delete employees) backed by a MySQL database.
* **Automated Daily Attendance Logging**: Automatically inserts sequential batch records (`PRESENT` / `HALF-DAY`) into the database for specified pay periods using `AttendanceDAO`.
* **Automated Net Salary Calculation**: Calculates gross pay, statutory tax deductions (10%), and net payable salary in ₹ (INR).
* **Pay Slip Generation**: Displays a clean, printable itemized salary summary statement (`salaryResult.jsp`).
* **Monochrome UI**: Designed using a minimalist, high-contrast black-and-white Bootstrap 5 layout.

---

## 🛠️ Tech Stack & Prerequisites

* **Language & Backend**: Java (JDK 8/11/17), Java Servlets, JSP (JSTL 1.2 & EL)
* **Database Access**: JDBC (Java Database Connectivity) with MySQL
* **Frontend**: HTML5, CSS3, Bootstrap 5 (Monochrome styling)
* **IDE & Tools**: NetBeans IDE, Apache Tomcat Server, phpMyAdmin / MySQL Server
* **Version Control**: Git & GitHub

---

## 🗄️ Database Architecture

The system uses a database named **`payroll_db`** with the following primary tables:

### 1. `employees`

| Column       | Type                      |
|--------------|---------------------------|
| `emp_id`     | INT, Primary Key, Auto Increment |
| `name`       | VARCHAR                   |
| `email`      | VARCHAR                   |
| `dept_id`    | INT, Foreign Key          |
| `daily_rate` | DOUBLE                    |

### 2. `attendance`

| Column          | Type                                                        |
|-----------------|-------------------------------------------------------------|
| `attendance_id` | INT, Primary Key, Auto Increment                            |
| `emp_id`        | INT, Foreign Key referencing `employees.emp_id`             |
| `work_date`     | VARCHAR / DATE (sequential date logs, e.g. `YYYY-MM-DD`)    |
| `status`        | VARCHAR (values: `PRESENT`, `HALF-DAY`)                     |

### 3. `departments`

| Column      | Type             |
|-------------|------------------|
| `dept_id`   | INT, Primary Key |
| `dept_name` | VARCHAR          |

---

## 🚀 Setup & Execution Instructions

### 1. Database Setup

1. Open **phpMyAdmin** or your MySQL client.
2. Create a database named `payroll_db`.
3. Create the required tables (`employees`, `attendance`, `departments`).
4. Ensure the database connection credentials match those in `com.payroll.util.DBConnection.java`:

   ```java
   String url = "jdbc:mysql://localhost:3306/payroll_db";
   String user = "root";
   String password = ""; // Add your MySQL password if applicable
   ```

### 2. Project Execution in NetBeans

1. Open NetBeans IDE.
2. Go to **File → Open Project** and select `PayrollTracker`.
3. Ensure Apache Tomcat Server is configured.
4. Right-click the project → select **Clean and Build**.
5. Right-click the project → select **Run**.

---

## 🔐 Credentials & Default Landing

* **Default Entry Point**: `http://localhost:8080/PayrollTracker/` (redirects to `login.jsp`)
* **Admin Username**: `admin`
* **Admin Password**: `admin123`

---

## 📊 Business Logic (Salary Rules)

* **Total Work Days** = Present Days + (0.5 × Half Days)
* **Gross Salary** = Total Work Days × Daily Rate (₹)
* **Statutory Deductions** = Gross Salary × 10%
* **Net Payable Salary** = Gross Salary − Deductions

---

## 📄 License & Repository

* **Repository**: [kushalramcyber/PayrollTracker](https://github.com/kushalramcyber/PayrollTracker)
