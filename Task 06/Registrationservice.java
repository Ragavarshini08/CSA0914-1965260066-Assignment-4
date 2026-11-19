
import java.sql.*;

public class RegistrationService {

    RegistrationDAO dao = new RegistrationDAO();

    public String validate(
            String name,
            String reg,
            String dept,
            String email) {

        if (name.isEmpty() || reg.isEmpty() ||
            dept.isEmpty() || email.isEmpty()) {

            return "All fields are required";
        }

        if (!email.matches(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

            return "Invalid email address";
        }

        return "OK";
    }

    public String register(
            String name,
            String reg,
            String dept,
            String email,
            int eventId) {

        String validation = validate(
            name, reg, dept, email
        );

        if (!validation.equals("OK"))
            return validation;

        try {

            boolean saved = dao.register(
                name, reg, dept, email, eventId
            );

            return saved
                ? "Registration Successful"
                : "Registration Failed";

        } catch (SQLIntegrityConstraintViolationException e) {

            return "Already registered for this event";

        } catch (SQLException e) {

            if ("23000".equals(e.getSQLState()))
                return "Duplicate registration";

            return "Database error: " + e.getMessage();
        }
    }
}
