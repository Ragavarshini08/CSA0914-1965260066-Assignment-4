
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class RegistrationDAO {

    public boolean register(
            String name,
            String reg,
            String dept,
            String email,
            int eventId) throws SQLException {

        String sql =
            "INSERT INTO registrations " +
            "(student_name, register_no, department, email, event_id) " +
            "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, reg);
            ps.setString(3, dept);
            ps.setString(4, email);
            ps.setInt(5, eventId);

            return ps.executeUpdate() == 1;
        }
    }

    public DefaultTableModel getRegistrations(
            int eventId) throws SQLException {

        DefaultTableModel model =
            new DefaultTableModel(
                new String[]{
                    "ID", "Name", "Register No",
                    "Department", "Email", "Event"
                }, 0
            );

        String sql =
            "SELECT r.registration_id, r.student_name, " +
            "r.register_no, r.department, r.email, e.event_name " +
            "FROM registrations r " +
            "JOIN events e ON r.event_id = e.event_id " +
            (eventId == 0 ? "" : "WHERE r.event_id = ? ") +
            "ORDER BY r.registration_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (eventId != 0)
                ps.setInt(1, eventId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    model.addRow(new Object[]{
                        rs.getInt("registration_id"),
                        rs.getString("student_name"),
                        rs.getString("register_no"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("event_name")
                    });
                }
            }
        }

        return model;
    }
}
