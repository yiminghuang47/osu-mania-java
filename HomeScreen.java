import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class HomeScreen extends JPanel {
    private Viewer viewer;

    public HomeScreen(Viewer viewer) {
        this.viewer = viewer;
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Select Song");
        titleLabel.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        JButton song1Button = new JButton("Song 1");
        song1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.showDifficultySelection("Song 1");
            }
        });
        gbc.gridy = 1;
        add(song1Button, gbc);

        JButton song2Button = new JButton("Song 2");
        song2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.showDifficultySelection("Song 2");
            }
        });
        gbc.gridy = 2;
        add(song2Button, gbc);

        // Add more buttons for additional songs
        
    }
}