import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Viewer {
    private JFrame frame;
    private StartScreen startScreen;
    private SongSelectionScreen homeScreen;
    private Player player;
    private Clip clip;

    private DifficultySelection difficultySelection;

    public static void main(String[] args) {
        Viewer viewer = new Viewer();
        viewer.showStartScreen();
    }

    public Viewer() {
        frame = new JFrame();
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        frame.setTitle("opseudo! mania");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(400, 20);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        startScreen = new StartScreen(this);

        frame.add(startScreen);
        homeScreen = new SongSelectionScreen(this);
    }

    public void showStartScreen() {
        frame.add(startScreen);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    public void showHomeScreen() {
        if (startScreen != null)
            frame.remove(startScreen);
        if (player != null)
            frame.remove(player);
        if (difficultySelection != null)
            frame.remove(difficultySelection);
        homeScreen = new SongSelectionScreen(this);
        frame.add(homeScreen);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    public void showDifficultySelection(SongInfo song) {
        if (homeScreen != null)
            frame.remove(homeScreen);
        difficultySelection = new DifficultySelection(this, song);
        frame.add(difficultySelection);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    public void startGame(Beatmap map, SongInfo song) {
        // Play Audio
        String filePath = song.getFilePath();
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.err.println("Audio file does not exist: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.err.println("Audio line is not supported: " + filePath);
                return;
            }

            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        if (homeScreen != null)
            frame.remove(homeScreen);
        if (difficultySelection != null)
            frame.remove(difficultySelection);
        player = new Player(map, this, song);
        frame.add(player);
        frame.revalidate();
        frame.repaint();
        player.requestFocusInWindow();
    }

    public void stopAudio() {
        if (clip != null && clip.isOpen() && clip.isRunning()) {
            clip.stop(); // Stop the audio clip
        }
    }
}
