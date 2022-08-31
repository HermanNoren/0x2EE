package main;

public class Program {
    public static void main(String[] args) {
        Game game = new Game();
        GamePanel gamePanel = new GamePanel(game);
        game.addObserver(gamePanel);
        game.addWindow(new Window(gamePanel));
        game.startGame();
    }
}



