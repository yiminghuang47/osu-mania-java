import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SongSelectionScreen extends JPanel {
    private Viewer viewer;
    private Image backgroundImage;
    public SongSelectionScreen(Viewer viewer) {
        this.viewer = viewer;
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
         // Load the background image
         ImageIcon backgroundImageIcon = new ImageIcon("images/background.jpg"); // Replace "background.jpg" with your image file path
         backgroundImage = backgroundImageIcon.getImage();
 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Select Song");
        titleLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 40));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        add(titleLabel, gbc);

        gbc.gridwidth = 1; // Reset to default
        gbc.gridy = 1; // Start adding buttons below the title

        SongInfo song1 = Songs.song1;
        JButton song1Button = new JButton(song1.getName());
        song1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.showDifficultySelection(song1);
            }
        });
        gbc.gridx = 0;
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
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(song2Button, gbc);

        SongInfo song3 = Songs.song3;
        JButton song3Button = new JButton(song3.getName()); 
        song3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.showDifficultySelection(song3);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(song3Button, gbc);

        SongInfo song4 = Songs.song4;
        JButton song4Button = new JButton(song4.getName()); 
        song4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.showDifficultySelection(song4);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(song4Button, gbc);

        SongInfo song5 = Songs.song5;
        JButton song5Button = new JButton(song5.getName()); 
        song5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.showDifficultySelection(song5);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(song5Button, gbc);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }
}
