
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Converter3 extends JFrame {

    JTextField amount = new JTextField(10);

    JComboBox<String> from =
        new JComboBox<>(new String[]{"USD", "INR", "EUR", "GBP"});

    JComboBox<String> to =
        new JComboBox<>(new String[]{"USD", "INR", "EUR", "GBP"});

    JLabel result = new JLabel("Result");

    double[] rate = {1, 88.50, 0.86, 0.75};

    Converter3() {

        setLayout(new FlowLayout());

        JButton convert = new JButton("Convert");

        add(amount);
        add(from);
        add(to);
        add(convert);
        add(result);

        ActionListener handler = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    double value = Double.parseDouble(
                        amount.getText().trim()
                    );

                    if (!Double.isFinite(value) || value < 0)
                        throw new NumberFormatException();

                    double answer = value *
                        rate[from.getSelectedIndex()] /
                        rate[to.getSelectedIndex()];

                    String output = String.format(
                        "%.2f %s",
                        answer, to.getSelectedItem()
                    );

                    result.setText(output);

                    JOptionPane.showMessageDialog(
                        Converter3.this, output
                    );

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                        Converter3.this, "Invalid amount"
                    );
                }
            }
        };

        convert.addActionListener(handler);
        amount.addActionListener(handler);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(400, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Converter3());
    }
}
