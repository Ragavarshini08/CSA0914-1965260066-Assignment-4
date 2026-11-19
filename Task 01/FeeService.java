
public class FeeService {

    public String processPayment(String name,
                    String amount,
                    String mode) {

        if (name.trim().isEmpty())
            return "Enter student name";

        if (amount.trim().isEmpty())
            return "Enter payment amount";

        double value;

        try {
            value = Double.parseDouble(amount);

            if (value <= 0)
                return "Enter a valid amount";

        } catch (NumberFormatException e) {
            return "Invalid payment amount";
        }

        if (!mode.equals("Online") &&
            !mode.equals("Cash"))
            return "Select payment mode";

        return "Payment Successful!\n" +
               "Student: " + name +
               "\nAmount: Rs. " + value +
               "\nMode: " + mode;
    }
}
