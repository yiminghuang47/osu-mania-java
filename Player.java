import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Player extends JComponent {
    private final ArrayList<ArrayList<Note>> allNotes;
    private final Set<Integer> keysPressed;
    private boolean showJudgement;
    private Timer judgementTimer;
    private String judgementMessage;
    private final boolean SHOW_JUDGEMENT_LINES = false;

    private int noteCount;
    private int score;
    private int combo;
    private int maxCombo;
    private boolean isEnd;
    private JButton returnButton;
    private Viewer viewer;
    private Timer endScreenTimer;

    private int perfectCount;
    private int goodCount;
    private int badCount;
    private int missCount;

    private Image backgroundImage;
    
    
    private SongInfo song;

    public Player(Beatmap map, Viewer viewer, SongInfo song) {
        this.viewer = viewer;
        this.song = song;
        judgementMessage = "";
        score = 0;
        isEnd = false;
        noteCount = 0;
        combo = 0;
        
        Beatmap beatmap = map;
        allNotes = beatmap.getAllNotes();

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File(song.getImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        Timer startTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        startTimer.setRepeats(false); // Only execute the action once
        startTimer.start(); 

        */
        
        Timer timer = new Timer(Constants.REFRESH_RATE, e -> {
            for (int lane = 0; lane < 4; lane++) {
                List<Note> notes = allNotes.get(lane);
                for (Note note : notes) {
                    note.setY(note.getY() + Constants.NOTE_VELOCITY);
                }
            }
            for (int lane = 0; lane < 4; lane++) {
                List<Note> notes = allNotes.get(lane);
                if (notes.size() == 0)
                    continue;
                Note note = notes.get(0);
                if (note.getY() > Constants.FRAME_HEIGHT) {
                    removeFirstNoteInLane(lane);
                }
            }
            repaint();
        });
        timer.start();

        keysPressed = new HashSet<>();
        setFocusable(true);
        showJudgement = false;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (!keysPressed.contains(keyCode)) {
                    keysPressed.add(keyCode);
                    switch (keyCode) {
                        case KeyEvent.VK_D:
                            removeFirstNoteInLane(0);
                            break;
                        case KeyEvent.VK_F:
                            removeFirstNoteInLane(1);
                            break;
                        case KeyEvent.VK_J:
                            removeFirstNoteInLane(2);
                            break;
                        case KeyEvent.VK_K:
                            removeFirstNoteInLane(3);
                            break;
                    }
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keysPressed.remove(e.getKeyCode());
            }
        });

        returnButton = new JButton("Return");
        returnButton.setBounds(175, 400, 150, 50);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewer.showHomeScreen();
            }
        });
        returnButton.setVisible(false);
        add(returnButton);
    }

    
    private void removeFirstNoteInLane(int lane) {
        if (!allNotes.get(lane).isEmpty()) {
            Note firstNote = allNotes.get(lane).get(0);
            String judgement = Judgements.getJudgement(firstNote.getY());
            if (judgement.equals("NONE")) return;
            noteCount++;
            showJudgementMessage();
            judgementMessage = judgement;
            switch (judgementMessage) {
                case "PERFECT":
                    score += 300;
                    combo++;
                    perfectCount++;
                    break;
                case "GOOD":
                    score += 200;
                    combo++;
                    goodCount++;
                    break;
                case "BAD":
                    score += 100;
                    combo++;
                    badCount++;
                    break;
                case "MISS":
                    combo = 0;
                    missCount++;
                    break;
            }
            maxCombo = Math.max(combo, maxCombo);
    
            allNotes.get(lane).remove(0);
            if (allNotes.get(0).size() == 0 && allNotes.get(1).size() == 0 && allNotes.get(2).size() == 0
                    && allNotes.get(3).size() == 0) {
                startEndScreenTimer();
            }
        }
    }
    

    private void startEndScreenTimer() {
        if (endScreenTimer != null && endScreenTimer.isRunning()) {
            endScreenTimer.stop();
        }
        endScreenTimer = new Timer(3000, e -> {
            isEnd = true;
            repaint();
        });
        endScreenTimer.setRepeats(false);
        endScreenTimer.start();
    }

    private void showJudgementMessage() {
        showJudgement = true;
        if (judgementTimer != null && judgementTimer.isRunning()) {
            judgementTimer.stop();
        }
        judgementTimer = new Timer(3000, e -> {
            showJudgement = false;
            repaint();
        });
        judgementTimer.setRepeats(false);
        judgementTimer.start();
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
    Graphics2D g2 = (Graphics2D) g;
    if (backgroundImage != null) {
        Graphics2D g2d = (Graphics2D) g.create();
        drawScaledImage(g2d, backgroundImage, getWidth(), getHeight(), 0.3f);
        g2d.dispose();
    }

    if (!isEnd) {
        for (List<Note> notes : allNotes) {
            for (Note note : notes) {
                note.draw(g2);
            }
        }
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g2.drawString(String.format("%08d",score), 300, 100);
        double accuracy = 100.00;
        if (noteCount != 0) {
            accuracy = (double) score / (300 * noteCount) * 100;
        }
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g2.drawString(String.format("%.2f", accuracy) + " %", 350, 150);

        g2.setColor(Color.WHITE);
        g2.drawLine(0, Judgements.JUDGEMENT_LINE, 500, Judgements.JUDGEMENT_LINE);

        if (SHOW_JUDGEMENT_LINES) {
            g2.setColor(Color.YELLOW);
            g2.drawLine(0, Judgements.JUDGEMENT_LINE + Judgements.PERFECT_DIFF, 500,
                    Judgements.JUDGEMENT_LINE + Judgements.PERFECT_DIFF);
            g2.drawLine(0, Judgements.JUDGEMENT_LINE - Judgements.PERFECT_DIFF, 500,
                    Judgements.JUDGEMENT_LINE - Judgements.PERFECT_DIFF);
            g2.setColor(Color.GREEN);
            g2.drawLine(0, Judgements.JUDGEMENT_LINE + Judgements.GOOD_DIFF, 500,
                    Judgements.JUDGEMENT_LINE + Judgements.GOOD_DIFF);
            g2.drawLine(0, Judgements.JUDGEMENT_LINE - Judgements.GOOD_DIFF, 500,
                    Judgements.JUDGEMENT_LINE - Judgements.GOOD_DIFF);
            g2.setColor(Color.BLUE);
            g2.drawLine(0, Judgements.JUDGEMENT_LINE - Judgements.BAD_DIFF, 500,
                    Judgements.JUDGEMENT_LINE - Judgements.BAD_DIFF);
            g2.drawLine(0, Judgements.JUDGEMENT_LINE + Judgements.BAD_DIFF, 500,
                    Judgements.JUDGEMENT_LINE + Judgements.BAD_DIFF);
            g2.setColor(Color.RED);
            g2.drawLine(0, Judgements.JUDGEMENT_LINE - Judgements.MISS_DIFF, 500,
                    Judgements.JUDGEMENT_LINE - Judgements.MISS_DIFF);
            g2.drawLine(0, Judgements.JUDGEMENT_LINE + Judgements.MISS_DIFF, 500,
                    Judgements.JUDGEMENT_LINE + Judgements.MISS_DIFF);
        }

        if (showJudgement) {
            HashMap<String, Color> judgementColor = new HashMap<>();
            judgementColor.put("PERFECT", Color.YELLOW);
            judgementColor.put("GOOD", Color.GREEN);
            judgementColor.put("BAD", Color.BLUE);
            judgementColor.put("MISS", Color.RED);

            g2.setColor(judgementColor.get(judgementMessage));
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 40));
            String centeredString = String.format("%10s", judgementMessage);
            g2.drawString(centeredString, getWidth() / 2 - 130, getHeight() / 2);
            g2.setColor(Color.WHITE);
            centeredString = String.format("%4s", String.valueOf(combo));
            g2.drawString(centeredString, getWidth() / 2 - 50, getHeight() / 2 + 50);
        }
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        
        g2.drawString("D",85,525);
        g2.drawString("F",185,525);
        g2.drawString("J",285,525);
        g2.drawString("K",385,525);
    } else {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g2.drawString("SCORE   " + score, 150, 100);
        g2.setColor(Color.YELLOW);
        g2.drawString("PERFECT", 50, 150);
        g2.setColor(Color.WHITE);
        g2.drawString(perfectCount+"", 200, 150);

        g2.setColor(Color.GREEN);
        g2.drawString("GOOD", 275, 150);
        g2.setColor(Color.WHITE);
        g2.drawString(goodCount+"", 375, 150);

        g2.setColor(Color.BLUE);
        g2.drawString("BAD", 50, 225);
        g2.setColor(Color.WHITE);
        g2.drawString(badCount+"", 200, 225);

        g2.setColor(Color.RED);
        g2.drawString("MISS", 275, 225);
        g2.setColor(Color.WHITE);
        g2.drawString(missCount+"", 375, 225);

        g2.drawString("Max Combo", 50, 300);
        g2.drawString(maxCombo+"", 50, 350);
        double accuracy = (double) score / (300 * noteCount) * 100;
        g2.drawString("Accuracy", 275, 300);
        
        g2.drawString(String.format("%.2f", accuracy) + " %", 275, 350);
        

        returnButton.setVisible(true);
    }
}

}
