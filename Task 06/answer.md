# Task 06 – Design Challenge

## Project Title
Campus Event Registration Management System

## 1. Introduction
The system is a Java Swing desktop application connected to a MySQL database. Students can register for campus events, while committee members can view registrations, filter them by event and export registration details.

## 2. Identify Users

| User | Responsibilities |
|---|---|
| Student | Register for campus events. |
| Committee Member | View registrations and filter records. |
| Administrator | Manage events and registration information. |

## 3. Screens Required

**Screen 1 – Student Registration**
- Student name
- Register number
- Department
- Event selection
- Email address
- Register button
- Clear button

**Screen 2 – Committee Dashboard**
- Registration table
- Event filter
- Search button
- Export button
- Refresh button

**Screen 3 – Registration Confirmation**
- Registration number
- Student details
- Event details
- Success message

## 4. Screen/Dialog Flow

```text
START
  |
  v
Main Window
  |
  +-------------------+
  |                   |
  v                   v
Student             Committee
Registration        Dashboard
  |                   |
  v                   v
Enter Details       View Table
  |                   |
  v                   v
Validate             Filter
  |                   |
  v                   v
Check Duplicate      Export Data
  |
  +---------+
  |         |
  v         v
 Valid    Invalid
  |         |
  v         v
 Save     Show Error
  |
  v
Success Dialog
  |
  v
 END
```

## 5. Data Requirements

| Field | Data Type | Description |
|---|---|---|
| registration_id | INT | Unique registration ID |
| student_name | VARCHAR(100) | Student name |
| register_no | VARCHAR(30) | Student register number |
| department | VARCHAR(50) | Department |
| email | VARCHAR(100) | Student email |
| event_id | INT | Selected event |
| event_name | VARCHAR(100) | Event name |
| registration_date | TIMESTAMP | Registration date |

## 6. Rules and Requirements

1. All required fields must be entered.
2. Email must be in a valid format.
3. Register number must not be empty.
4. A student cannot register twice for the same event.
5. Registration must be stored in MySQL.
6. Committee members can filter registrations by event.
7. Registration records can be exported.
8. Database operations must not freeze the GUI.

## 7. Functional Requirements

| ID | Requirement |
|---|---|
| FR01 | Student can register for an event. |
| FR02 | System validates student information. |
| FR03 | System prevents duplicate registration. |
| FR04 | Committee can view registrations. |
| FR05 | Committee can filter by event. |
| FR06 | Committee can export registration data. |
| FR07 | System displays success and error messages. |

## 8. Non-Functional Requirements

- User-friendly interface.
- Secure database access.
- Fast response time.
- Reliable data storage.
- Responsive GUI.
- Maintainable application structure.

## 9. Three-Layer Architecture

```text
USER
  |
  v
PRESENTATION LAYER
(Swing UI)
  |
  v
SERVICE LAYER
(Validation and Rules)
  |
  v
DATA ACCESS LAYER
(JDBC)
  |
  v
MYSQL DATABASE
```

## 10. Class Structure

```text
CampusEventApp
|
+-- RegistrationUI
|   |
|   +-- StudentForm
|   +-- CommitteeDashboard
|
+-- RegistrationService
|   |
|   +-- validate()
|   +-- register()
|   +-- filter()
|
+-- RegistrationDAO
    |
    +-- insert()
    +-- getAll()
    +-- filterByEvent()
    +-- checkDuplicate()
    +-- export()
```

## 11. Register Pseudocode

```text
START
Read student details
Validate all fields

If any field is empty:
    Display error
    STOP

Validate email

If email is invalid:
    Display error
    STOP

Check duplicate registration

If registration already exists:
    Display "Already Registered"
    STOP

Insert registration into database

If insertion successful:
    Display "Registration Successful"
Else:
    Display error message

END
```

## 12. Filter Pseudocode

```text
START
Read selected event
Connect to database
Retrieve registrations
WHERE event_id = selected event
Display records in JTable

If no records:
    Display "No registrations found"

END
```

## 13. Important Implementation Details

- PreparedStatement protects database queries from SQL injection.
- SwingWorker executes database operations in the background.
- `done()` updates the GUI on the EDT.
- The SQL UNIQUE constraint prevents duplicate registrations.
- try-with-resources closes database resources.
- The dashboard provides event filtering and CSV export.

## 14. Expected Output and Testing

| Test | Input | Expected Result |
|---|---|---|
| Normal registration | Valid student details | Registration Successful |
| Empty input | Student name empty | All fields are required |
| Invalid email | abc123 | Invalid email address |
| Duplicate registration | Same register number and event | Already registered |
| Filter records | Select Hackathon | Only Hackathon records |
| Export | Click Export | CSV file generated |

**Example valid registration:**

```text
Student Name: Arun Kumar
Register Number: 12345
Department: CSE
Email: arun@gmail.com
Event: Hackathon

Registration Successful
```

**Example invalid registration:**

```text
Student Name:
Register Number: 12345
Department: CSE
Email: arun@gmail.com
Event: Hackathon

All fields are required
```

**Example duplicate registration:**

```text
Student Name: Arun Kumar
Register Number: 12345
Department: CSE
Email: arun@gmail.com
Event: Hackathon

Already registered for this event
```

## 15. Event Dispatch Thread (EDT)

The Event Dispatch Thread handles Swing GUI events. If database operations are performed directly on the EDT, the application may freeze.

**Correct approach:** Use `SwingWorker` to perform database operations in the background and update the GUI after completion.

Advantages:
1. Prevents GUI freezing.
2. Performs long-running tasks in the background.
3. Updates the interface after completion.
4. Improves user experience.

## 16. Loading 100,000 Records vs Pagination

| Parameter | Load All Records | Paginated Loading |
|---|---|---|
| Records loaded | 100,000 | Limited records per page |
| Loading time | Generally higher | Generally lower initially |
| Memory usage | High | Lower |
| Responsiveness | May become slow | Better |
| Database requests | Fewer | More |
| Scalability | Less suitable | More suitable |

SQL query for loading all records:

```sql
SELECT * FROM registrations;
```

SQL query for pagination:

```sql
SELECT * FROM registrations
ORDER BY registration_id
LIMIT 100 OFFSET 0;
```

Next page:

```sql
SELECT * FROM registrations
ORDER BY registration_id
LIMIT 100 OFFSET 100;
```

Third page:

```sql
SELECT * FROM registrations
ORDER BY registration_id
LIMIT 100 OFFSET 200;
```

**Conclusion:** Pagination is more suitable for large registration databases because it reduces the number of records loaded at a time and improves memory usage and GUI responsiveness. Large OFFSET values can still become expensive, so keyset pagination can be considered for very large datasets.

## 17. Complexity Analysis

Let `n` be the total number of registrations and `p` be the page size.

| Operation | Time Complexity |
|---|---|
| Validate input | O(n) for string-based validation |
| Insert registration | O(log n) approximately for indexed database insertion |
| Duplicate checking | O(log n) with a suitable unique index |
| Load all registrations | O(n) |
| Load one page | O(p) returned rows, excluding database query overhead |
| Filter registrations | Depends on database indexing and matching records |
| Export registrations | O(n) |
