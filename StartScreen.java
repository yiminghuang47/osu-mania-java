import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JPanel {
    private Viewer viewer;
    private Image backgroundImage;
    public StartScreen(Viewer viewer) {
        this.viewer = viewer;
        setLayout(null); // Using absolute positioning

        // Load the background image
        ImageIcon backgroundImageIcon = new ImageIcon("images/background.jpg"); // Replace "background.jpg" with your image file path
        backgroundImage = backgroundImageIcon.getImage();

        JLabel titleLabel = new JLabel("<html><span style='color:#ff8ee6;'>O</span>p<span style='color:#ff8ee6;'>S</span>e<span style='color:#ff8ee6;'>U</span>do<span style='color:#ff8ee6;'>!</span> <span style='color:#ff8ee6;'>mania</span></html>");
        
        titleLabel.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(75, 100, 500, 50);
        add(titleLabel);
        
        JLabel authorsLabel = new JLabel("<html><p>Authors: <br> Yiming Huang, Genji Tsuchihashi, Elliott Tanalski</p></html>");
        authorsLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        authorsLabel.setForeground(Color.WHITE);
        authorsLabel.setBounds(50, 400, 400, 50);
        add(authorsLabel);

        
        JLabel description = new JLabel("<html><p>This is a ripoff version of the popular (?) rhythm game <br> <span style='color:#ff8ee6;'>osu! mania</span>. It's coded in Java and it serves as our AP CS Final Project.</p> <br> <p>It's open-source and you can add your own songs. It also generates beatmaps randomly.</p></html>");
        description.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 14));
        description.setForeground(Color.WHITE);
        description.setBounds(50, 150, 400, 150);
        add(description);

        JButton startButton = new JButton("Start");
        startButton.setBackground(new Color(255, 142, 230)); 
        startButton.setForeground(Color.WHITE); 
        startButton.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 35)); 
        startButton.setBounds(150, 330, 200, 60); 
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.showHomeScreen();
            }
        });
        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }
}
