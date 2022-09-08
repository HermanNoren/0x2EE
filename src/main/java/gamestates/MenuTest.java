package gamestates;

import main.Game;
import sprites.Sprite;
import sprites.buttons.GameButton;
import sprites.buttons.buttonactions.EmptyButtonAction;
import sprites.buttons.buttonactions.StartGameButtonAction;

import java.io.IOException;
import java.util.ArrayList;

public class MenuTest implements GameState {

    private Game game;

    private GameButton button1;
    private GameButton button2;
    private GameButton button3;

    private int activePos;

    private boolean recentlyMovedUp;
    private boolean recentlyMovedDown;
    private ArrayList<GameButton> buttons;
    private ArrayList<Sprite> sprites;

    public MenuTest(Game game) {
        this.game = game;
        activePos = 0;
        recentlyMovedUp = false;
        recentlyMovedDown = false;
        button1 = new GameButton("Start Game", 50, 50, new StartGameButtonAction(game));
        button2 = new GameButton("Knapp 2", 50, 150, new EmptyButtonAction());
        button3 = new GameButton("Knapp 3", 50, 250, new EmptyButtonAction());
        buttons = new ArrayList<>();
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        sprites = new ArrayList<>();
        sprites.add(button1);
        sprites.add(button2);
        sprites.add(button3);
    }

    @Override
    public ArrayList<Sprite> getSprites() {
        return new ArrayList<>(sprites);
    }

    @Override
    public void update() {

        if (game.getSPressed() && !recentlyMovedDown) {
            recentlyMovedDown = true;
            activePos += 1;
            activePos %= buttons.size();
        }


        if (!game.getSPressed() && recentlyMovedDown) {
            recentlyMovedDown = false;
        }


        if (game.getWPressed() && !recentlyMovedUp) {
            recentlyMovedUp = true;
            activePos -= 1;
            if (activePos < 0) {
                activePos = buttons.size() - 1;
            }
        }


        if (!game.getWPressed() && recentlyMovedUp) {
            recentlyMovedUp = false;
        }

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setIsSelected(i == activePos);
        }

        if (game.getEnterPressed()) {
            buttons.get(activePos).isClicked();
        }
    }

    @Override
    public void draw() {

    }
}
