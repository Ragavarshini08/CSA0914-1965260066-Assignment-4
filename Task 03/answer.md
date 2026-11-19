# Task 3 – From Sketch to Layout

## 1. Screen Design

Scenario: Design a campus library search screen.

The screen contains:

1. Title at the top.
2. Search field.
3. Category selection.
4. Search button.
5. Results area in the centre.
6. Previous, Next and Reserve buttons at the bottom.
7. Status message.

## 2. Layout Manager Selection

| Container | Layout Manager | Reason |
|---|---|---|
| Main Frame | BorderLayout | Divides screen into five regions. |
| Title Panel | FlowLayout | Displays title at the top. |
| Search Panel | FlowLayout | Places search controls in one row. |
| Results Panel | BorderLayout | Allows results to expand. |
| Bottom Panel | FlowLayout | Arranges buttons horizontally. |

## 3. Nested Panel Structure

```text
JFrame – Library Search
(BorderLayout)
|
+-- NORTH
|   +-- Title Panel (FlowLayout)
|       +-- JLabel – Campus Library
|
+-- CENTER
|   +-- Main Panel (BorderLayout)
|       |
|       +-- NORTH
|       |   +-- Search Panel (FlowLayout)
|       |       +-- JTextField – Search
|       |       +-- Choice/JComboBox – Category
|       |       +-- JButton – Search
|       |
|       +-- CENTER
|           +-- JTable – Search Results
|               (inside JScrollPane)
|
+-- SOUTH
    +-- Bottom Panel (BorderLayout)
        |
        +-- NORTH
        |   +-- Button Panel (FlowLayout)
        |       +-- Previous
        |       +-- Next
        |       +-- Reserve
        |
        +-- SOUTH
            +-- JLabel – Status
```

## 4. Complete Java Implementation

File: `LibrarySearch.java`

Refer to the separate `LibrarySearch.java` file.

## 5. Output

Example:

```text
          CAMPUS LIBRARY

[Search Book] [All v] [Search]

Book ID      Book Name            Category
101          Java Programming     Java
102          Python Basics        Python
103          Database Systems     Database
104          Advanced Java        Java
105          Python Projects      Python

      [Prev] [Next] [Reserve]

Status: 5 books found
```

## 6. Resizing Test Cases

| Test | Window Size | Expected Behaviour |
|---|---|---|
| 1 | 400 × 300 | Results area becomes smaller but remains visible. |
| 2 | 650 × 400 | Normal layout with all controls visible. |
| 3 | 900 × 600 | Results area expands to use available space. |

## 7. Nested Layouts vs GridBagLayout vs Absolute Positioning

| Nested Layouts | GridBagLayout | Absolute Positioning |
|---|---|---|
| Uses multiple panels. | Uses flexible grid constraints. | Uses fixed coordinates. |
| Easy to understand. | More complex to implement. | Easy for simple fixed screens. |
| Supports resizing. | Supports advanced resizing. | Poor resizing support. |
| Good maintainability. | Good flexibility. | Difficult maintenance. |

**Conclusion:** Nested layouts are suitable for the campus library screen because the results area can grow while the search and bottom panels maintain their intended arrangement.

## 8. Complexity Analysis

Let `n` be the number of books.

| Operation | Time Complexity |
|---|---|
| Search all books | O(n × m) |
| Add results to table | O(n) |
| Select a book | O(1) |
| Display results | O(n) |

Here, `m` represents the average book-name length.
