# Task 04 – Event-Handling Forensics

## 1. Identify the Problems

1. The mouse click handler does not execute because the method may be incorrectly named or the mouse listener is not registered.
2. The click counter is not updated because the label is not refreshed.
3. Drawing directly inside the mouse-click event is not permanent. The dots disappear when the window is minimized and restored.

## 2. Corrected Approach

- Store each mouse-click position using a `Point` object.
- Update the click counter.
- Update the label.
- Call `repaint()` after every click.
- Draw all stored points inside `paintComponent()`.

## 3. Complete Java Implementation

**File:** `DrawingPad.java`

Refer to the separate `DrawingPad.java` file.

## 4. Explanation of Important Methods

| Method | Purpose |
|---|---|
| `addMouseListener()` | Registers mouse events. |
| `mouseClicked()` | Executes when the mouse is clicked. |
| `points.add()` | Stores the clicked position. |
| `counter.setText()` | Updates the click count. |
| `repaint()` | Requests the component to redraw. |
| `paintComponent()` | Draws all stored points. |

## 5. Output

Example:

```text
MOUSE DRAWING PAD

    •       •
        •
            •
      •

Clicks: 5
```

## 6. Minimizing and Restoring Test

| Test | Action | Expected Result |
|---|---|---|
| 1 | Open drawing pad | Blank drawing area. |
| 2 | Click five different positions | Five dots appear. |
| 3 | Minimize the window | Window is hidden. |
| 4 | Restore the window | All five dots remain visible. |
| 5 | Click another position | Sixth dot appears and counter becomes 6. |

## 7. Importance of `@Override`

`@Override` indicates that a method is intended to override a method declared in a superclass or interface.

Example:

```java
@Override
public void mouseClicked(MouseEvent e) {
    System.out.println("Mouse clicked");
}
```

If the method name is incorrectly written as `mouseClick()`, the compiler reports an error because it does not override the required method.

Thus, `@Override` helps detect incorrect method names during compilation.

## 8. Point Objects vs BufferedImage

| Point Objects | BufferedImage |
|---|---|
| Stores each clicked position. | Stores the actual drawn image. |
| Easy to implement. | Requires additional graphics handling. |
| Redraws points during repaint. | Displays a stored image during repaint. |
| Good for simple drawing pads. | Better for complex drawings. |
| Memory depends on number of points. | Memory depends on image dimensions. |

**Conclusion:** Point objects are suitable for this assignment because they provide a simple way to preserve and redraw mouse-click positions.

## 9. Complexity Analysis

Let `n` be the number of clicked points.

| Operation | Time Complexity |
|---|---|
| Store one point | O(1) amortized |
| Update counter | O(1) |
| Repaint all points | O(n) |
| Space required | O(n) |
