import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class SongSelectionScreen extends JPanel {
    private Viewer viewer;

    public SongSelectionScreen(Viewer viewer) {
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

        SongInfo song1 = Songs.song1;
        JButton song1Button = new JButton(song1.getName());
        song1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.showDifficultySelection(song1);
            }
        });
        gbc.gridy = 1;
        add(song1Button, gbc);

        SongInfo song2 = Songs.song2;
        JButton song2Button = new JButton(song2.getName()); 
        song2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.showDifficultySelection(song2);
            }
        });
        gbc.gridy = 2;
        add(song2Button, gbc);

        // Add more buttons for additional songs
        
    }
}