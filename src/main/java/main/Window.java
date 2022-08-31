package main;

import javax.swing.JFrame;

public class Window {
    private JFrame jframe;
    public Window(GamePanel gamePanel) {
        jframe = new JFrame();
        jframe.setSize(16 * 60, 12 * 60);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("0x2EE");
        jframe.add(gamePanel);
        jframe.setVisible(true);
    }
}
