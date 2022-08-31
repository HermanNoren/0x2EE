package buttons;

import helperclasses.Vector2;

public class Button {

    private Vector2 position;
    private ButtonAction action;

    public Button(int x, int y, ButtonAction action) {
        position = new Vector2(x, y);
        this.action = action;
    }

    public void isClicked() {
        action.performAction();
    }
}
