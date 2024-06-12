import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DifficultySelection extends JPanel {
    private Viewer viewer;
    private String songName;

    public DifficultySelection(Viewer viewer, String songName) {
        this.viewer = viewer;
        this.songName = songName;
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Select Difficulty for " + songName);
        titleLabel.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        JButton easyButton = new JButton("Easy");
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.startGame(new RandomizedMapMedium());
            }
        });
        gbc.gridy = 1;
        add(easyButton, gbc);

        JButton mediumButton = new JButton("Medium");
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.startGame(new RandomizedMapMedium());
            }
        });
        gbc.gridy = 2;
        add(mediumButton, gbc);

        JButton hardButton = new JButton("Hard");
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.startGame(new RandomizedMapHard());
            }
        });
        gbc.gridy = 3;
        add(hardButton, gbc);
    }
}
