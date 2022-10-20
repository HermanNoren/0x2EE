package view.buttons;
import model.Vector2;
import config.Config;
import controllers.buttonactions.IButtonAction;

/**
 * The game button is used as a blueprint for the majority of the games buttons.
 * @author Herman Noren
 */
public class GameButton {

    private String buttonText;
    private Vector2 pos;
    private int width = Config.SPRITE_SIZE * 18;
    private int height = Config.SPRITE_SIZE * 4;
    private IButtonAction action;
    private boolean isSelected;

    public GameButton(String buttonText, int x, int y, IButtonAction action) {
        this.buttonText = buttonText;
        pos = new Vector2(x, y);
        isSelected = false;
        this.action = action;
    }

    public void isClicked() {
        action.performAction();
    }

    public String getButtonText() {
        return buttonText;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean value) {
        isSelected = value;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector2 getPos() {
        return new Vector2(pos);
    }
}
