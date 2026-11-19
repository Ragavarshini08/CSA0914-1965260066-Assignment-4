
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPad extends JFrame {

    JLabel counter = new JLabel("Clicks: 0");

    ArrayList<Point> points = new ArrayList<>();

    DrawingPad() {

        setTitle("Mouse Drawing Pad");

        JPanel panel = new JPanel() {

            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                g.setColor(Color.BLUE);

                for (Point p : points) {
                    g.fillOval(p.x, p.y, 10, 10);
                }
            }
        };

        panel.setBackground(Color.WHITE);

        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                points.add(e.getPoint());

                counter.setText(
                    "Clicks: " + points.size()
                );

                panel.repaint();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(counter, BorderLayout.SOUTH);

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new DrawingPad()
        );
    }
}
