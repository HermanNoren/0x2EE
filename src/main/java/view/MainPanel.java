package view;

import model.gameinterfaces.IGame;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;
import view.panelstates.PanelStateFactory;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

/**
 * @author Herman Noren
 * @responsibility This class is the main panel that everything is drawn upon.
 */
public class MainPanel extends JPanel implements IObserver, IChangeableStatePanel {

    private final IGame game;
    private IPanelState state;

    /**
     * Instantiates a MainPanel
     * @param game main model object
     */
    public MainPanel(IGame game) {
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


    /**
     * See override description to see Graphics g draw implementation. This method
     * is used to draw all logic of the game.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        state.draw(g2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        repaint();
    }
}
