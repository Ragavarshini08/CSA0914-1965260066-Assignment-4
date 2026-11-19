
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FeePaymentApp extends JFrame {

    JTextField name = new JTextField(15);
    JTextField amount = new JTextField(15);

    JComboBox<String> mode =
        new JComboBox<>(new String[]{"Online", "Cash"});

    JButton pay = new JButton("Pay");
    JButton receipt = new JButton("Receipt");

    JLabel result = new JLabel("Enter payment details");

    FeeService service = new FeeService();

    String paymentResult = "";

    FeePaymentApp() {

        setTitle("Student Fee Payment");
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Student Name:"));
        add(name);

        add(new JLabel("Amount:"));
        add(amount);

        add(new JLabel("Payment Mode:"));
        add(mode);

        add(pay);
        add(receipt);

        add(new JLabel("Status:"));
        add(result);

        pay.addActionListener(e -> {

            paymentResult = service.processPayment(
                name.getText(),
                amount.getText(),
                (String) mode.getSelectedItem()
            );

            result.setText(paymentResult.split("\n")[0]);

            JOptionPane.showMessageDialog(
                this, paymentResult
            );
        });

        receipt.addActionListener(e -> {

            if (paymentResult.startsWith("Payment Successful")) {
                JOptionPane.showMessageDialog(
                    this, paymentResult,
                    "Payment Receipt",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    this, "Complete payment first."
                );
            }
        });

        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            () -> new FeePaymentApp()
        );
    }
}
