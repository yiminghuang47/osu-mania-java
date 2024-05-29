

import java.awt.Color;

import javax.swing.JFrame;

public class Viewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(Sizes.FRAME_WIDTH, Sizes.FRAME_HEIGHT);
        frame.setTitle("OSU Mania");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Player player = new Player();
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.add(player);

        frame.setVisible(true);
    }
}
