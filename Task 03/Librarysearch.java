
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class LibrarySearch extends JFrame {

    JTextField search = new JTextField(20);

    JComboBox<String> category =
        new JComboBox<>(new String[]{
            "All", "Java", "Python", "Database"
        });

    DefaultTableModel model =
        new DefaultTableModel(
            new String[]{"Book ID", "Book Name", "Category"},
            0
        );

    JTable table = new JTable(model);

    JLabel status = new JLabel("Ready");

    String[][] books = {
        {"101", "Java Programming", "Java"},
        {"102", "Python Basics", "Python"},
        {"103", "Database Systems", "Database"},
        {"104", "Advanced Java", "Java"},
        {"105", "Python Projects", "Python"}
    };

    LibrarySearch() {

        setTitle("Campus Library Search");
        setLayout(new BorderLayout(5, 5));

        JLabel title = new JLabel(
            "CAMPUS LIBRARY",
            JLabel.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel searchPanel = new JPanel(new FlowLayout());

        JButton searchButton = new JButton("Search");

        searchPanel.add(search);
        searchPanel.add(category);
        searchPanel.add(searchButton);

        JScrollPane scroll = new JScrollPane(table);

        JButton prev = new JButton("Prev");
        JButton next = new JButton("Next");
        JButton reserve = new JButton("Reserve");

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(prev);
        buttonPanel.add(next);
        buttonPanel.add(reserve);

        JPanel bottom = new JPanel(new BorderLayout());

        bottom.add(buttonPanel, BorderLayout.NORTH);
        bottom.add(status, BorderLayout.SOUTH);

        JPanel centre = new JPanel(new BorderLayout());

        centre.add(searchPanel, BorderLayout.NORTH);
        centre.add(scroll, BorderLayout.CENTER);

        add(title, BorderLayout.NORTH);
        add(centre, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchBooks());

        prev.addActionListener(e ->
            status.setText("Previous page selected")
        );

        next.addActionListener(e ->
            status.setText("Next page selected")
        );

        reserve.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                status.setText("Select a book first");
            } else {
                status.setText(
                    "Reserved: " + model.getValueAt(row, 1)
                );
            }
        });

        searchButton.doClick();

        setSize(650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void searchBooks() {

        model.setRowCount(0);

        String key = search.getText().trim().toLowerCase();

        String selected =
            category.getSelectedItem().toString();

        for (String[] book : books) {

            boolean matchName =
                book[1].toLowerCase().contains(key);

            boolean matchCategory =
                selected.equals("All") ||
                book[2].equals(selected);

            if (matchName && matchCategory) {
                model.addRow(book);
            }
        }

        status.setText(
            model.getRowCount() + " books found"
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            () -> new LibrarySearch()
        );
    }
}
