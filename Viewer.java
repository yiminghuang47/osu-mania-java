import java.awt.Color;
import javax.swing.JFrame;

public class Viewer {
    private JFrame frame;
    private StartScreen startScreen;
    private HomeScreen homeScreen;
    private Player player;

    private DifficultySelection difficultySelection;

    public static void main(String[] args) {
        Viewer viewer = new Viewer();
        viewer.showStartScreen();
    }

    public Viewer() {
        frame = new JFrame();
        frame.setSize(Sizes.FRAME_WIDTH, Sizes.FRAME_HEIGHT);
        frame.setTitle("OSU Mania");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(400, 20);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        startScreen = new StartScreen(this);
        
        frame.add(startScreen);
        homeScreen = new HomeScreen(this);
    }

    public void showStartScreen() {
        frame.add(startScreen);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    
    public void showHomeScreen() {
        if (startScreen != null) frame.remove(startScreen);
        if (player != null) frame.remove(player);
        if (difficultySelection != null) frame.remove(difficultySelection);
        homeScreen = new HomeScreen(this);
        frame.add(homeScreen);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    public void showDifficultySelection(String songName) {
        if (homeScreen != null) frame.remove(homeScreen);
        difficultySelection = new DifficultySelection(this, songName);
        frame.add(difficultySelection);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }


    public void startGame(Beatmap map) {
        if(homeScreen!=null) frame.remove(homeScreen);
        if(difficultySelection!=null) frame.remove(difficultySelection);
        player = new Player(map,this);
        frame.add(player);
        frame.revalidate();
        frame.repaint();
        player.requestFocusInWindow();
    }
}
