
class FeeService {

    public String payFee(String regNo, String amount) {

        if (regNo == null || regNo.trim().isEmpty()) {

            return "Registration number is required";

        }

        double a;

        try {

            a = Double.parseDouble(amount);

        } catch (NumberFormatException e) {

            return "Invalid amount";

        }

        if (a <= 0) {

            return "Amount must be positive";

        }

        // Payment validation successful.
        // Database insertion can be performed here
        // using JDBC and PreparedStatement.

        return "Payment successful for " + regNo;

    }
}
