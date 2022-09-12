package view;

import sprites.Player;

public class HUD implements IObserver {

    private int score;
    private int money;
    private int health;

    public HUD(Player player){
        this.score = player.getScore();
        this.money = player.getMoney();
        this.health = player.getHealth();
    }

    @Override
    public void draw() {

    }
}
