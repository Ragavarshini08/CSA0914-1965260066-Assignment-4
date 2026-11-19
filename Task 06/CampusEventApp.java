
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CampusEventApp extends JFrame {

    JTextField name = new JTextField(15);
    JTextField reg = new JTextField(15);
    JTextField dept = new JTextField(15);
    JTextField email = new JTextField(15);

    JComboBox<String> event = new JComboBox<>(
        new String[]{
            "Hackathon",
            "Coding Competition",
            "Technical Symposium",
            "Project Expo"
        }
    );

    JButton register = new JButton("Register");
    JButton clear = new JButton("Clear");
    JButton dashboard = new JButton("Dashboard");

    RegistrationService service =
        new RegistrationService();

    CampusEventApp() {

        setTitle("Campus Event Registration");
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel(
            "CAMPUS EVENT REGISTRATION",
            JLabel.CENTER
        );

        title.setFont(
            new Font("Arial", Font.BOLD, 20)
        );

        JPanel form = new JPanel(
            new GridLayout(5, 2, 8, 8)
        );

        form.setBorder(
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );

        form.add(new JLabel("Student Name:"));
        form.add(name);

        form.add(new JLabel("Register Number:"));
        form.add(reg);

        form.add(new JLabel("Department:"));
        form.add(dept);

        form.add(new JLabel("Email:"));
        form.add(email);

        form.add(new JLabel("Event:"));
        form.add(event);

        JPanel buttons = new JPanel();

        buttons.add(register);
        buttons.add(clear);
        buttons.add(dashboard);

        add(title, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        register.addActionListener(e -> submit());

        clear.addActionListener(e -> {
            name.setText("");
            reg.setText("");
            dept.setText("");
            email.setText("");
            event.setSelectedIndex(0);
        });

        dashboard.addActionListener(e -> openDashboard());

        setSize(550, 350);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenu());

        setVisible(true);
    }

    JMenuBar createMenu() {

        JMenuBar bar = new JMenuBar();

        JMenu menu = new JMenu("File");

        JMenuItem exit = new JMenuItem("Exit");

        exit.addActionListener(e -> dispose());

        menu.add(exit);
        bar.add(menu);

        return bar;
    }

    void submit() {

        String student = name.getText().trim();
        String number = reg.getText().trim();
        String department = dept.getText().trim();
        String mail = email.getText().trim();

        int eventId = event.getSelectedIndex() + 1;

        register.setEnabled(false);

        SwingWorker<String, Void> worker =
            new SwingWorker<String, Void>() {

                protected String doInBackground() {

                    return service.register(
                        student,
                        number,
                        department,
                        mail,
                        eventId
                    );
                }

                protected void done() {

                    register.setEnabled(true);

                    try {

                        String message = get();

                        JOptionPane.showMessageDialog(
                            CampusEventApp.this,
                            message
                        );

                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(
                            CampusEventApp.this,
                            "Registration error: " + ex.getMessage()
                        );
                    }
                }
            };

        worker.execute();
    }

    void openDashboard() {

        JFrame frame = new JFrame("Committee Dashboard");

        frame.setSize(800, 400);
        frame.setLocationRelativeTo(this);

        String[] events = {
            "All Events",
            "Hackathon",
            "Coding Competition",
            "Technical Symposium",
            "Project Expo"
        };

        JComboBox<String> filter =
            new JComboBox<>(events);

        JButton search = new JButton("Filter");
        JButton export = new JButton("Export");

        JTable table = new JTable();

        JScrollPane scroll = new JScrollPane(table);

        JPanel top = new JPanel();

        top.add(filter);
        top.add(search);
        top.add(export);

        frame.add(top, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);

        Runnable load = () -> {

            int eventId = filter.getSelectedIndex();

            search.setEnabled(false);

            new SwingWorker<javax.swing.table.DefaultTableModel, Void>() {

                protected javax.swing.table.DefaultTableModel
                    doInBackground() throws Exception {

                    RegistrationDAO dao =
                        new RegistrationDAO();

                    return dao.getRegistrations(eventId);
                }

                protected void done() {

                    search.setEnabled(true);

                    try {

                        table.setModel(get());

                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(
                            frame,
                            "Unable to load registrations: " +
                            ex.getMessage()
                        );
                    }
                }

            }.execute();
        };

        search.addActionListener(e -> load.run());

        export.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            if (chooser.showSaveDialog(frame) ==
                JFileChooser.APPROVE_OPTION) {

                java.io.File file = chooser.getSelectedFile();

                try (java.io.PrintWriter out =
                    new java.io.PrintWriter(file)) {

                    for (int c = 0; c < table.getColumnCount(); c++) {

                        out.print(table.getColumnName(c));

                        if (c < table.getColumnCount() - 1)
                            out.print(",");
                    }

                    out.println();

                    for (int r = 0; r < table.getRowCount(); r++) {

                        for (int c = 0;
                             c < table.getColumnCount(); c++) {

                            String value = String.valueOf(
                                table.getValueAt(r, c)
                            );

                            out.print("\"" +
                                value.replace("\"", "\"\"") +
                                "\"");

                            if (c < table.getColumnCount() - 1)
                                out.print(",");
                        }

                        out.println();
                    }

                    JOptionPane.showMessageDialog(
                        frame, "Export successful"
                    );

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                        frame, "Export failed"
                    );
                }
            }
        });

        frame.setVisible(true);

        load.run();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new CampusEventApp()
        );
    }
}
