package config;

import java.awt.*;

public class Config {
    public static final int SPRITE_SIZE = 16;
    public static final int WALL_SIZE = 3;
    public static final int SCREEN_WIDTH = 60 * 16;
    public static final int SCREEN_HEIGHT = 45 * 16;
    public static final int SCREEN_WIDTH_IN_GAME = SCREEN_WIDTH * 5;
    public static final int SCREEN_HEIGHT_IN_GAME = SCREEN_HEIGHT * 4;

    public static final Font buttonFont = new Font("Public Pixel", Font.PLAIN, 12);
    public static final Font inGameTextFont = new Font("Public Pixel", Font.BOLD, 24);
    public static final Font titleFont = new Font("Public Pixel", Font.PLAIN, 64);
    public static final Font  infoFont = new Font("Public Pixel", Font.PLAIN, 32);
    public static  final Font nameFont = new Font("Public Pixel", Font.PLAIN, 56);
}

