package main;

import javax.swing.JFrame;

public class Window {
    private JFrame jframe;

    public Window(GamePanel gamePanel) {
        jframe = new JFrame();
        jframe.setSize(16 * 60, 10 * 60);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setVisible(true);
    }
}
