import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JPanel {
    private Viewer viewer;

    public StartScreen(Viewer viewer) {
        this.viewer = viewer;
        setBackground(Color.BLACK);
        setLayout(null); // Using absolute positioning

        JLabel titleLabel = new JLabel("OSU Mania");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(150, 100, 300, 50);
        add(titleLabel);
        
        JLabel authorsLabel = new JLabel("Authors: Yiming Huang, Genji Tsuchihashi, Elliot Tanalski");
        authorsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        authorsLabel.setForeground(Color.WHITE);
        authorsLabel.setBounds(50, 150, 400, 50);
        add(authorsLabel);

        
        JLabel description = new JLabel("<html><p>This is a java clone version of the popular (?) game osu! mania. It serves as our AP CS Final Project.</p></html>");
        description.setFont(new Font("Arial", Font.PLAIN, 14));
        description.setForeground(Color.WHITE);
        description.setBounds(50, 200, 400, 100);
        add(description);

        JButton startButton = new JButton("Start");
        startButton.setBounds(200, 400, 100, 40);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.showHomeScreen();
            }
        });
        add(startButton);
    }
}
