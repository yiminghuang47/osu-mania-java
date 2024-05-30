import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.util.*;

public class Player extends JComponent {
    private final ArrayList<ArrayList<Note>> allNotes;
    private final int velocity;
    private final Set<Integer> keysPressed;
    private boolean showJudgement;
    private Timer judgementTimer;
    private String judgementMessage;
    private final boolean SHOW_JUDGEMENT_LINES = false;

    private int noteCount;
    private int score;
    private int combo;

    public Player() {
        judgementMessage = "";
        score = 0;
        noteCount = 0;
        combo = 0;
        velocity = 25; // Speed at which the notes fall
        Beatmap beatmap = new RandomizedMap2();
        allNotes = beatmap.getAllNotes();
        /* 
        allNotes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            allNotes.add(new ArrayList<>());
        }
        */
        // Add multiple notes at different initial positions
        /* 
        allNotes.get(0).add(new Note(50, 0));
        allNotes.get(1).add(new Note(150, -100));
        allNotes.get(2).add(new Note(250, -150));
        allNotes.get(3).add(new Note(350, 0));
        allNotes.get(0).add(new Note(50, -250));
        allNotes.get(0).add(new Note(50, -350));
        */


        Timer timer = new Timer(30, e -> {
            for (int lane = 0; lane < 4; lane++) {
                List<Note> notes = allNotes.get(lane);
                for (Note note : notes) {
                    note.setY(note.getY() + velocity);
                    
                }
            }
            for (int lane = 0; lane < 4; lane++) {
                List<Note> notes = allNotes.get(lane);
                if(notes.size()==0) continue;
                Note note = notes.get(0);
                if (note.getY() > Sizes.FRAME_HEIGHT) {
                    removeFirstNoteInLane(lane);
                }

            }
            repaint();
        });
        timer.start();

        // Initialize the set to keep track of pressed keys
        keysPressed = new HashSet<>();

        // Add key listener
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
    }

    private void removeFirstNoteInLane(int lane) {
        if (!allNotes.get(lane).isEmpty()) {

            Note firstNote = allNotes.get(lane).get(0);
            String judgement = Judgements.getJudgement(firstNote.getY());
            if (judgement.equals("NONE"))
                return;
            noteCount++;
            showJudgementMessage();
            judgementMessage = judgement;
            if (judgementMessage.equals("PERFECT")) {
                score += 300;
                combo++;
            } else if (judgementMessage.equals("GOOD")) {
                score += 100;
                combo++;
            } else if (judgementMessage.equals("BAD")) {
                score += 50;
                combo++;
            } else{
                combo = 0;
            }

            allNotes.get(lane).remove(0);
        }
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (List<Note> notes : allNotes) {
            for (Note note : notes) {
                note.draw(g2);
            }
        }
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g2.drawString(String.valueOf(score), 350, 100);
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
            HashMap<String, Color> judgementColor = new HashMap<String, Color>();
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
            g2.drawString(centeredString, getWidth() / 2 - 50, getHeight() / 2+50);
            
        }
    }
}