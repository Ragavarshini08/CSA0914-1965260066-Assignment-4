# Task 1 – GUI Design Review

## 1. Diagnosis of Design Problems

| No. | Design Problem | Impact |
|---|---|---|
| 1 | No separation of concerns | UI and business logic become difficult to maintain. |
| 2 | Incorrect String comparison using `==` | String values may not be compared correctly. |
| 3 | No input validation | Empty or invalid payment details may be accepted. |
| 4 | Database access inside event handler | The interface may freeze during database operations. |
| 5 | Use of Thread.sleep() | Blocks the Event Dispatch Thread (EDT). |
| 6 | No proper user feedback | Students cannot understand whether payment succeeded or failed. |
| 7 | Receipt action not handled separately | Receipt generation becomes difficult to maintain. |
| 8 | Unsafe SQL statements | SQL injection may occur if user input is directly concatenated. |
| 9 | Resources are not closed | Database connections and statements may leak. |
| 10 | Poor exception handling | Errors may terminate the application or remain hidden. |

## 2. Corrected Application Design

The application should use separate classes for the user interface and payment processing.

### Class Structure

```text
          FeePaymentApp
          (JFrame / UI)
                 |
                 | User Actions
                 v
             FeeService
       (Validation and Payment)
                 |
                 v
             PaymentDAO
          (Database Operations)
                 |
                 v
               MySQL
```

### Responsibilities

| Class | Responsibility |
|---|---|
| FeePaymentApp | Collects input and displays results. |
| FeeService | Validates details and processes payments. |
| PaymentDAO | Handles database operations. |
| Receipt | Generates payment receipts. |

## 3. Corrected Event Handling

The following code demonstrates separate UI and payment-service logic.

Refer to `FeeService.java` and `FeePaymentApp.java` for the complete implementation.

## 4. Comparison of Listener Approaches

| Shared Listener | Separate Listeners |
|---|---|
| One listener handles multiple buttons. | Each button has its own listener. |
| Requires checking the event source. | Each action is handled independently. |
| Can become lengthy. | Easier to read and maintain. |
| Suitable for simple applications. | Suitable for larger applications. |

**Swing Action:** An Action object can combine an action's behaviour, name, icon and enabled state. It can be reused by buttons and menu items.

## 5. Design Justification (Within 100 Words)

The redesigned application separates the user interface from payment processing. FeePaymentApp collects input and displays results, while FeeService validates and processes payments. This separation improves readability, reusability, testing and maintenance. Separate listeners make button actions easier to understand. Input validation prevents incorrect values, and proper exception handling improves reliability. Database operations should be performed using a separate DAO class with prepared statements and closed resources. Long-running operations should execute in the background to prevent the GUI from freezing.

## 6. Complexity Analysis

| Operation | Time Complexity | Space Complexity |
|---|---|---|
| Student name validation | O(n) | O(1) |
| Amount validation | O(1) | O(1) |
| Payment processing (without database) | O(n) | O(n) |
| Receipt display | O(n) | O(n) |

Here, n represents the length of the input or receipt text.
