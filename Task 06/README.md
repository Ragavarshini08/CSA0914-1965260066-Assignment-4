# Campus Event Registration Management System

## Description
A Java Swing desktop application connected to MySQL using JDBC. Students can register for campus events. Committee members can view, filter and export registrations.

## Technologies Used
- Java
- Java Swing
- MySQL
- JDBC
- MySQL Connector/J

## Prerequisites
- JDK 8 or later
- MySQL Server
- MySQL Connector/J
- VS Code or IntelliJ IDEA

## Files
- `database.sql` – Creates the database and tables.
- `DBConnection.java` – Establishes database connection.
- `RegistrationDAO.java` – Performs database operations.
- `RegistrationService.java` – Validates and processes registrations.
- `CampusEventApp.java` – Main application interface.

## Setup Instructions
1. Start MySQL Server.
2. Execute `database.sql` in MySQL.
3. Replace `YOUR_MYSQL_PASSWORD` in `DBConnection.java` with your MySQL password.
4. Add MySQL Connector/J to the Java project classpath.
5. Compile all Java files.
6. Run `CampusEventApp.java`.

## Features
- Student event registration.
- Input validation.
- Duplicate registration prevention.
- Committee dashboard.
- Event-wise filtering.
- CSV export.
- Background database operations using SwingWorker.

## Testing
Test normal registration, empty input, invalid email, duplicate registration, event filtering and CSV export.

## Output Screenshots
The `output` folder contains screenshots of registration, invalid input, duplicate registration and dashboard after running the application.
