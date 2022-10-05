package View.buttons;
import Model.helperclasses.Vector2;
import config.Config;
import View.buttons.buttonactions.IButtonAction;

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
