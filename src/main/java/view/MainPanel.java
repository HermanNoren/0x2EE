package view;

import model.Game;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;
import view.panelstates.PanelStateFactory;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainPanel extends JPanel implements IObserver {

    private final Game game;
    private IPanelState state;

    public MainPanel(Game game) {
        this.game = game;
        state = PanelStateFactory.createPanelState(EPanelState.MAINMENU, this, game);
        changeKeyListeners();
        setFocusable(true);
        initFonts();
    }

    private void initFonts(){
        try {
            Font gameDefaultFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/view/Font/GameFont.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(gameDefaultFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public Game getGame() {
        return game;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        state.draw(g2);
    }


    public void changePanelState(EPanelState state) {
        this.state = PanelStateFactory.createPanelState(state, this, game);
        changeKeyListeners();
    }

    private void changeKeyListeners() {
        for (KeyListener listener : getKeyListeners()) {
            removeKeyListener(listener);
        }
        for (KeyListener listener : state.getKeyListeners()) {
            addKeyListener(listener);
        }
    }

    @Override
    public void draw() {
        repaint();
    }
}
