package view;

import config.Config;

import javax.swing.*;

public class Window {
    private JFrame jframe;
    private JPanel mainPanel;
    public Window(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        jframe = new JFrame();
        jframe.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("0x2EE");
        jframe.add(this.mainPanel);
        jframe.setVisible(true);
        jframe.setLocationRelativeTo(null);
    }
}
