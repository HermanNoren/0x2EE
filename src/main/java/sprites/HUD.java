package sprites;

import main.Observer;

public class HUD implements Observer {

    private Player player;
    private int score;
    private int money;
    private int health;

    public HUD(Player player){
        this.score = player.getScore();
        this.money = player.getMoney();
        this.health = player.getHealth();
    }


    @Override
    public void update() {

    }
}
