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

        JLabel titleLabel = new JLabel("OSU Mania");
        titleLabel.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        JButton level1Button = new JButton("Level 1");
        level1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.startGame(new RandomizedMap());
            }
        });
        gbc.gridy = 1;
        add(level1Button, gbc);

        JButton level2Button = new JButton("Level 2");
        level2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.startGame(new RandomizedMap2());
            }
        });
        gbc.gridy = 2;
        add(level2Button, gbc);
        /* 
        JButton level3Button = new JButton("Level 3");
        level3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.startGame(3);
            }
        });
        gbc.gridy = 3;
        add(level3Button, gbc);
        */
    }
}
