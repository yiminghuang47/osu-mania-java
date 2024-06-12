import java.awt.Color;
import javax.swing.JFrame;

public class Viewer {
    private JFrame frame;
    private HomeScreen homeScreen;
    private Player player;

    public static void main(String[] args) {
        Viewer viewer = new Viewer();
        viewer.showHomeScreen();
    }

    public Viewer() {
        frame = new JFrame();
        frame.setSize(Sizes.FRAME_WIDTH, Sizes.FRAME_HEIGHT);
        frame.setTitle("OSU Mania");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(400, 20);
        frame.getContentPane().setBackground(new Color(0, 0, 0));

        homeScreen = new HomeScreen(this);
        frame.add(homeScreen);
    }

    public void showHomeScreen() {
        if (player != null) {
            frame.remove(player);
        }
        frame.add(homeScreen);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    public void startGame(Beatmap map) {
        frame.remove(homeScreen);
        player = new Player(map,this);
        frame.add(player);
        frame.revalidate();
        frame.repaint();
        player.requestFocusInWindow();
    }
}
