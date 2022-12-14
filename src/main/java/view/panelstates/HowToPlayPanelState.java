package view.panelstates;

import config.Config;
import controllers.ButtonSwitcherController;
import view.IChangeableStatePanel;
import view.ImageHandler;
import view.buttons.GameButton;
import controllers.buttonactions.MenuButtonAction;
import view.drawers.ButtonDrawer;
import view.drawers.IDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the view for the howToPlay state.
 * @author Rickard Leksell
 */
class HowToPlayPanelState implements IPanelState{
    private BufferedImage controls, instructions;
    private final ButtonSwitcherController bc;
    private final ArrayList<GameButton> buttons;
    private List<IDrawer> drawers;
    private List<KeyListener> keyListeners;
    private IChangeableStatePanel mainPanel;

    private ImageHandler imageHandler;

    private String title = "HOW TO PLAY";


    public HowToPlayPanelState(IChangeableStatePanel mainPanel){
        this.mainPanel = mainPanel;
        buttons = new ArrayList<>();
        createButtons();
        bc = new ButtonSwitcherController(buttons);
        keyListeners = new ArrayList<>();
        keyListeners.add(bc);
        imageHandler = new ImageHandler();
        controls = imageHandler.getImage("imgs/menus/h2p.png");
        instructions = imageHandler.getImage("imgs/menus/instructions.png");
        drawers = new ArrayList<>();
        drawers.add(new ButtonDrawer(buttons));
    }


    @Override
    public void draw(Graphics2D g2) {

        bc.update();

        g2.setColor(Color.black);
        g2.fillRect(0,0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        g2.setFont(Config.BUTTON_FONT);
        for (IDrawer drawer : drawers){
            drawer.draw(g2);
        }
        g2.setColor(Color.white);
        g2.setFont(Config.TITLE_FONT);
        g2.drawString(title, (Config.SCREEN_WIDTH - g2.getFontMetrics().stringWidth(title)) / 2 , 100);
        g2.drawImage(controls, 0,80, Config.SCREEN_WIDTH*100/150, Config.SCREEN_HEIGHT, null);
        g2.drawImage(instructions, 575,130, 350, 400, null);
    }

    @Override
    public void changePanelState(EPanelState panelState) {
        mainPanel.changePanelState(panelState);
    }


    @Override
    public List<KeyListener> getKeyListeners() {
        return keyListeners;
    }

    private void createButtons(){
        GameButton backButton = new GameButton("BACK", 325, 575, new MenuButtonAction(EPanelState.MAINMENU, this));
        backButton.setIsSelected(true);
        buttons.add(backButton);
    }
}
