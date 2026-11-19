
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Converter1 extends JFrame
       implements ActionListener {

    JTextField amount = new JTextField(10);

    JComboBox<String> from = new JComboBox<>(
        new String[]{"USD", "INR", "EUR", "GBP"}
    );

    JComboBox<String> to = new JComboBox<>(
        new String[]{"USD", "INR", "EUR", "GBP"}
    );

    JLabel result = new JLabel("Result");

    JButton convert = new JButton("Convert");

    double[] rate = {1, 88.50, 0.86, 0.75};

    Converter1() {

        setLayout(new FlowLayout());

        add(new JLabel("Amount"));
        add(amount);
        add(from);
        add(to);
        add(convert);
        add(result);

        convert.addActionListener(this);

        amount.addActionListener(e -> actionPerformed(
            new ActionEvent(convert,
                ActionEvent.ACTION_PERFORMED, "Convert")
        ));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(400, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            double value = Double.parseDouble(
                amount.getText().trim()
            );

            if (!Double.isFinite(value) || value < 0)
                throw new NumberFormatException();

            int f = from.getSelectedIndex();
            int t = to.getSelectedIndex();

            double converted = value * rate[f] / rate[t];

            String output = String.format(
                "%.2f %s", converted,
                to.getSelectedItem()
            );

            result.setText(output);

            JOptionPane.showMessageDialog(this, output);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this, "Enter a valid non-negative amount"
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Converter1());
    }
}
