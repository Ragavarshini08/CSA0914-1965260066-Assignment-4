# Task 2 – Requirement to Event-Driven Design

## 1. Event Table

Scenario: A hospital outpatient appointment desk allows patients to book appointments with doctors.

| Event Source | Event Object | Listener Interface | Action |
|---|---|---|---|
| Department Choice | ItemEvent | ItemListener | Refresh doctor list |
| Doctor Choice | ItemEvent | ItemListener | Select doctor |
| Date Field | ActionEvent | ActionListener | Read appointment date |
| Book Button | ActionEvent | ActionListener | Validate and book appointment |
| Clear Button | ActionEvent | ActionListener | Clear all fields |
| Main Window | WindowEvent | WindowListener | Handle window closing |

## 2. Component Containment Tree

```text
JFrame – Appointment Window
|
+-- JPanel – Main Panel (BorderLayout)
    |
    +-- JPanel – Title Panel (FlowLayout)
    |   +-- JLabel – Hospital Appointment
    |
    +-- JPanel – Form Panel (GridLayout)
    |   +-- JLabel – Patient Name
    |   +-- JTextField – Name
    |   +-- JLabel – Department
    |   +-- JComboBox – Department
    |   +-- JLabel – Doctor
    |   +-- JComboBox – Doctor
    |   +-- JLabel – Appointment Date
    |   +-- JTextField – Date
    |
    +-- JPanel – Button Panel (FlowLayout)
    |   +-- JButton – Book
    |   +-- JButton – Clear
    |
    +-- JLabel – Status
```

## 3. Pseudocode to Refresh the Doctor List

```text
START

When department selection changes:

    Get selected department

    Clear doctor list

    Load doctors belonging to department

    Add doctors to doctor list

    If no doctors available:
        Display "No doctors available"

END
```

## 4. Pseudocode to Validate and Book an Appointment

```text
START

When Book button is clicked:

    Read patient name
    Read department
    Read doctor
    Read appointment date

    If patient name is empty:
        Display error

    Else if doctor is not selected:
        Display error

    Else if date is invalid:
        Display error

    Else:
        Check doctor availability

        If slot is available:
            Save appointment
            Display "Appointment booked successfully"

        Else:
            Display "Appointment slot unavailable"

END
```

## 5. Complete Java Implementation

File: `AppointmentApp.java`

Refer to the separate `AppointmentApp.java` file.

## 6. Method-Call Sequence

```text
START
    |
    v
main()
    |
    v
SwingUtilities.invokeLater()
    |
    v
AppointmentApp()
    |
    v
Create JFrame and Components
    |
    v
Register Event Listeners
    |
    v
setVisible(true)
    |
    v
User Selects Department
    |
    v
itemStateChanged()
    |
    v
refreshDoctors()
    |
    v
User Clicks Book
    |
    v
actionPerformed()
    |
    v
bookAppointment()
    |
    v
Validate and Display Result
    |
    v
User Closes Window
    |
    v
windowClosing()
    |
    v
Window Disposed
```

## 7. Window Events vs Applet Lifecycle

| Window Events | Applet Lifecycle |
|---|---|
| Window opening | init() |
| Window becomes visible | start() |
| Window painting | paint() |
| Window becomes inactive or closes | stop() |
| Window destroyed | destroy() |

**Explanation:**

- `init()` initializes the applet.
- `start()` starts its execution.
- `paint()` displays graphics.
- `stop()` pauses execution.
- `destroy()` releases resources.

A Swing JFrame uses window events and does not follow the applet lifecycle. Applets are a legacy technology.

## 8. Database Loading Comparison

| Load Every Time | Load Once into Map |
|---|---|
| Queries database whenever department changes. | Loads doctor details initially. |
| More database requests. | Fewer database requests. |
| Slower repeated selection. | Faster repeated selection. |
| Uses less local memory. | Requires additional memory. |
| Data is generally fresher. | Data may become outdated. |

**Conclusion:** Loading doctors once into a `Map<String, List<String>>` is efficient when doctor information does not change frequently. If doctor details change, the Map should be refreshed.

## 9. Complexity Analysis

Let `n` be the total number of doctors and `k` be the number of doctors in the selected department.

| Operation | Time Complexity |
|---|---|
| Load all doctors | O(n) |
| Refresh doctor list from array | O(k) |
| Retrieve doctors using HashMap | O(1) average lookup |
| Validate patient name | O(n) |
| Display appointment details | O(1) excluding text length |
