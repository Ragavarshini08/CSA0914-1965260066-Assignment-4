# Task 05 – Same Screen, Different Event Styles

## 1. Introduction

Build a currency converter with:
- Amount input field.
- From currency selection.
- To currency selection.
- Convert button.
- Result label.

The Convert action is implemented using four event-handling approaches:
1. Frame implements ActionListener.
2. Named inner class.
3. Anonymous inner class.
4. Lambda expression.

## 2. Currency Conversion Formula

**Converted Amount = Amount × From Rate / To Rate**

Fixed example exchange rates relative to USD:

| Currency | Rate |
|---|---:|
| USD | 1.00 |
| INR | 88.50 |
| EUR | 0.86 |
| GBP | 0.75 |

## 3. Four Event-Handling Approaches

- **Version 1 – Frame implements ActionListener:** The frame implements the interface and overrides `actionPerformed()`.
- **Version 2 – Named inner class:** A separate inner class handles the conversion event.
- **Version 3 – Anonymous inner class:** An anonymous listener object handles the event.
- **Version 4 – Lambda expression:** A short lambda expression handles the event.

All four programs also allow pressing Enter in the amount field to perform conversion. The result is displayed in a dialog. `WindowAdapter` handles window closing.

## 4. Comparison of Four Event-Handling Approaches

| Approach | Readability | Access to frame fields | Reuse |
|---|---|---|---|
| Frame implements ActionListener | Moderate | Direct | Moderate |
| Named inner class | Good | Direct | Good |
| Anonymous inner class | Moderate | Direct | Limited |
| Lambda expression | Very good | Direct | Good when assigned to a shared variable |

## 5. Code Change Comparison

| Version | Main code requirement | Handler structure |
|---|---|---|
| Implements ActionListener | Implement interface and override method | Separate class method |
| Named inner class | Define inner handler class | Separate named class |
| Anonymous inner class | Create listener object | Inline class |
| Lambda | Define lambda expression | Short event handler |

The four programs have similar conversion logic; only the listener implementation changes.

## 6. Lambda vs Method Reference vs Shared Action

| Method | Description |
|---|---|
| Lambda | Provides a short implementation of a functional interface. |
| Method reference | Refers to an existing compatible method. |
| Shared Action | Allows multiple controls to share the same action behaviour and properties. |

Example method reference:

```java
convert.addActionListener(this::convertCurrency);
```

**Conclusion:** Lambda expressions are suitable for short event handlers. Named inner classes are useful for larger handlers, while shared Action objects are useful when the same operation is used by multiple GUI components.

## 7. Complexity Analysis

Let `n` be the number of digits in the input amount.

| Operation | Time Complexity | Space Complexity |
|---|---|---|
| Parse amount | O(n) | O(1) |
| Find currency rate | O(1) | O(1) |
| Perform conversion | O(1) | O(1) |
| Display result | O(n) | O(n) |

## 8. Output Screenshots

Capture:
1. Valid currency conversion showing the converted result.
2. Invalid amount showing the error message.
