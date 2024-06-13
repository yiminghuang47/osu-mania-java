import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DifficultySelection extends JPanel {
    private Viewer viewer;
    private String songName;
    private Image backgroundImage;

    public DifficultySelection(Viewer viewer, SongInfo song) {
        this.viewer = viewer;
        this.songName = song.getName();
        
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File(song.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("<html><p>Select Difficulty for <br>" + songName + "</p></html>");
        titleLabel.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        JButton easyButton = new JButton("Easy");
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.startGame(new RandomizedMapEasy(song), song);
            }
        });
        gbc.gridy = 1;
        add(easyButton, gbc);

        JButton mediumButton = new JButton("Medium");
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.startGame(new RandomizedMapMedium(song), song);
            }
        });
        gbc.gridy = 2;
        add(mediumButton, gbc);

        JButton hardButton = new JButton("Hard");
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.startGame(new RandomizedMapHard(song), song);
            }
        });
        gbc.gridy = 3;
        add(hardButton, gbc);

        JButton extremeButton = new JButton("Extreme");
        extremeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.startGame(new RandomizedMapExtreme(song), song);
            }
        });
        gbc.gridy = 4;
        add(extremeButton, gbc);
    }
    public void drawScaledImage(Graphics2D g2d, Image img, int panelWidth, int panelHeight, float opacity) {
        int imgWidth = img.getWidth(null);
        int imgHeight = img.getHeight(null);
    
        // Calculate the scaling factor to fit the image within the panel
        double scale = Math.max((double) panelWidth / imgWidth, (double) panelHeight / imgHeight);
        
        // Calculate the new width and height of the scaled image
        int newWidth = (int) (imgWidth * scale);
        int newHeight = (int) (imgHeight * scale);
    
        // Calculate the position to center the image
        int x = (panelWidth - newWidth) / 2;
        int y = (panelHeight - newHeight) / 2;
    
        // Set the alpha (transparency) value
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
    
        // Draw the scaled image
        g2d.drawImage(img, x, y, newWidth, newHeight, null);
    }
    
    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (backgroundImage != null) {
        Graphics2D g2d = (Graphics2D) g.create();
        drawScaledImage(g2d, backgroundImage, getWidth(), getHeight(), 0.3f);
        g2d.dispose();
    }
}

}
