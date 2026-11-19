
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Converter2 extends JFrame {

    JTextField amount = new JTextField(10);

    JComboBox<String> from =
        new JComboBox<>(new String[]{"USD", "INR", "EUR", "GBP"});

    JComboBox<String> to =
        new JComboBox<>(new String[]{"USD", "INR", "EUR", "GBP"});

    JLabel result = new JLabel("Result");

    double[] rate = {1, 88.50, 0.86, 0.75};

    Converter2() {

        setLayout(new FlowLayout());

        JButton convert = new JButton("Convert");

        add(amount);
        add(from);
        add(to);
        add(convert);
        add(result);

        convert.addActionListener(new ConvertHandler());
        amount.addActionListener(new ConvertHandler());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(400, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    class ConvertHandler implements ActionListener {

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
                    Converter2.this, output
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    Converter2.this, "Invalid amount"
                );
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Converter2());
    }
}
