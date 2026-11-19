
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FeeWindow extends Frame {

    TextField regNo = new TextField(15);

    TextField amount = new TextField(15);

    Button pay = new Button("Pay");

    Button clear = new Button("Clear");

    Button receipt = new Button("Receipt");

    FeeService service = new FeeService();

    String lastReceipt = "";

    public FeeWindow() {

        setTitle("Student Fee Payment");

        setLayout(new GridLayout(4, 2, 10, 10));

        add(new Label("Register Number:"));

        add(regNo);

        add(new Label("Amount:"));

        add(amount);

        add(pay);

        add(clear);

        add(receipt);

        pay.addActionListener(e -> {

            String result = service.payFee(
                regNo.getText(),
                amount.getText()
            );

            lastReceipt = result;

            JOptionPane.showMessageDialog(this, result);

        });

        clear.addActionListener(e -> {

            regNo.setText("");

            amount.setText("");

        });

        receipt.addActionListener(e -> {

            if (lastReceipt.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this, "No payment receipt available"
                );

            } else {

                JOptionPane.showMessageDialog(
                    this, lastReceipt
                );

            }

        });

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                dispose();

            }

        });

        setSize(400, 250);

        setVisible(true);

    }

    public static void main(String[] args) {

        new FeeWindow();

    }

}
