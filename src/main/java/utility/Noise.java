package utility;

import model.mapclasses.GameMap;
import model.mapclasses.Tile;

import java.util.Random;

/**
 * Noise generation based on the Random displacement fractal.
 * Random displacement fractal: <a href="https://old.cescg.org/CESCG97/marak/node3.html">...</a>
 */
public class Noise {
    private final Random random;
    private final float roughness;
    private final float[][] grid;
    public Noise(float roughness, GameMap gameMap){
        this.random = new Random();
        this.roughness = roughness/gameMap.getWidth();
        this.grid = new float[gameMap.getWidth()][gameMap.getHeight()];
        init();
    }

    /**
     *
     */
    private void init(){
        int xh = grid.length - 1;
        int yh = grid[0].length - 1;

        grid[0][0] = random.nextFloat() - 0.5f;
        grid[xh][0] = random.nextFloat() - 0.5f;
        grid[0][yh] = random.nextFloat() - 0.5f;
        grid[xh][yh] = random.nextFloat() - 0.5f;

        generateFractal(0, 0, xh, yh);
    }

    private void generateFractal(int x1, int y1, int xh, int yh) {
        int xm = (x1 + xh) /2;
        int ym = (y1 + yh) /2;
        if((x1 == xm) && (y1 == ym)) return;


        grid[xm][y1] = (grid[x1][y1] + grid[xh][y1]) * 0.2f;
        grid[xm][yh] = (grid[x1][yh] + grid[xh][yh]) * 0.2f;
        grid[x1][ym] = (grid[x1][y1] + grid[x1][yh]) * 0.2f;
        grid[xh][ym] = (grid[xh][y1] + grid[xh][yh]) * 0.2f;

        float v = roughen(0.2f * (grid[xm][y1] + grid[xm][yh]), x1 + y1, yh + xh);

        grid[xm][ym] = v;

        grid[xm][y1] = roughen(grid[xm][y1], x1, xh);
        grid[xm][yh] = roughen(grid[xm][yh], x1, xh);
        grid[x1][ym] = roughen(grid[x1][ym], y1, yh);
        grid[xh][ym] = roughen(grid[xh][ym], y1, yh);

        generateFractal(x1, y1, xm, ym);
        generateFractal(xm, y1, xh, ym);
        generateFractal(x1, ym, xm, yh);
        generateFractal(xm, ym, xh, yh);

    }

    public void setNoise(Tile[][] tiles, float crowding){
        int w = grid.length;
        int h = grid[0].length;
        for(int i = 0;i < w;i++) {
            for(int j = 0;j < h;j++) {
                if(grid[i][j] < crowding){
                    tiles[i][j].setPassable(true);
                }else if(grid[i][j] < crowding + 1){
                    tiles[i][j].setPassable(false);
                }
                else if(grid[i][j] < crowding + 2){
                    tiles[i][j].setPassable(false);
                }
                else {
                    tiles[i][j].setPassable(false);
                }

            }
        }
    }

    private float roughen(float v, int l, int h) {
        return v + roughness * (float) (random.nextGaussian() * (h - l));
    }

    /**
     * Method used to print grid containing tiles.
     * @param tiles grid of type Tile.
     */
    public void printTileGrid(Tile[][] tiles){
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++){
            }
        }
    }

    /**
     * Method used to print out grid containing booleans.
     * @param booleans Grid of type boolean.
     */
    public void printBooleanGrid(boolean[][] booleans){
        for(int i = 0; i < booleans.length; i++){
            for(int j = 0; j < booleans[0].length; j++){
            }
        }
    }

    /**
     * Index set to true if terrainType < v, and false if terrainType > v
     * @param v value to compare value in the grid.
     * @return Returns a new grid containing booleans.
     */
    public boolean[][] toBooleans(float v) {
        int width = grid.length;
        int height= grid[0].length;

        boolean[][] booleans = new boolean[width][height];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height;j++) {
                booleans[i][j] = grid[i][j] < v;
            }
        }

        return booleans;
    }
}
