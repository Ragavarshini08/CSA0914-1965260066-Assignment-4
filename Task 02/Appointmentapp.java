
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppointmentApp extends JFrame {

    JTextField name = new JTextField();
    JTextField date = new JTextField();

    JComboBox<String> department =
        new JComboBox<>(new String[]{
            "Cardiology", "Neurology", "Orthopedics"
        });

    JComboBox<String> doctor = new JComboBox<>();

    JLabel status = new JLabel("Enter appointment details");

    String[][] doctors = {
        {"Dr. Arun", "Dr. Kumar"},
        {"Dr. Priya", "Dr. Ravi"},
        {"Dr. Meena", "Dr. John"}
    };

    AppointmentApp() {

        setTitle("Hospital Appointment");
        setLayout(new BorderLayout());

        JLabel title = new JLabel(
            "Hospital Appointment Booking",
            JLabel.CENTER
        );

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));

        form.add(new JLabel("Patient Name:"));
        form.add(name);

        form.add(new JLabel("Department:"));
        form.add(department);

        form.add(new JLabel("Doctor:"));
        form.add(doctor);

        form.add(new JLabel("Date (DD-MM-YYYY):"));
        form.add(date);

        JButton book = new JButton("Book");
        JButton clear = new JButton("Clear");

        JPanel buttons = new JPanel();

        buttons.add(book);
        buttons.add(clear);

        add(title, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(buttons, BorderLayout.NORTH);
        bottom.add(status, BorderLayout.SOUTH);

        add(bottom, BorderLayout.SOUTH);

        department.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                refreshDoctors();
            }
        });

        book.addActionListener(e -> bookAppointment());

        clear.addActionListener(e -> {
            name.setText("");
            date.setText("");
            status.setText("Fields cleared");
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Window closed");
            }
        });

        refreshDoctors();

        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void refreshDoctors() {

        doctor.removeAllItems();

        int index = department.getSelectedIndex();

        for (String d : doctors[index]) {
            doctor.addItem(d);
        }
    }

    void bookAppointment() {

        String patient = name.getText().trim();
        String appointmentDate = date.getText().trim();

        if (patient.isEmpty()) {
            status.setText("Enter patient name");
            return;
        }

        if (doctor.getSelectedItem() == null) {
            status.setText("Select a doctor");
            return;
        }

        try {
            java.time.LocalDate d =
                java.time.LocalDate.parse(
                    appointmentDate,
                    java.time.format.DateTimeFormatter.ofPattern(
                        "dd-MM-uuuu"
                    ).withResolverStyle(
                        java.time.format.ResolverStyle.STRICT
                    )
                );

            if (d.isBefore(java.time.LocalDate.now())) {
                status.setText("Select a future date");
                return;
            }

        } catch (Exception ex) {
            status.setText("Invalid date format");
            return;
        }

        status.setText("Appointment booked successfully");

        JOptionPane.showMessageDialog(
            this,
            "Patient: " + patient +
            "\nDoctor: " + doctor.getSelectedItem() +
            "\nDate: " + appointmentDate
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            () -> new AppointmentApp()
        );
    }
}
