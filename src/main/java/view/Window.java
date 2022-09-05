package view;

import javax.swing.*;

public class Window {
    private JFrame jframe;
    private JPanel mainPanel;
    public Window(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        jframe = new JFrame();
        jframe.setSize(16 * 60, 16 * 50);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("0x2EE");
        jframe.add(this.mainPanel);
        jframe.setVisible(true);
    }
}
