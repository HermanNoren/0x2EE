package utility;

import model.mapclasses.GameMap;
import model.mapclasses.Terrain;

import java.util.Random;

/**
 * Noise generation based on the Random displacement fractal.
 * Random displacement fractal: <a href="https://old.cescg.org/CESCG97/marak/node3.html">...</a>
 */
public class Noise {
    private final Random random;
    private final float roughness;
    private Terrain[][] grid;
    private float[][] grid1;

    public Noise(float roughness, GameMap gameMap){

        this.random = new Random();
        int width = gameMap.getWidth();
        int height = gameMap.getHeight();
        this.roughness = roughness/width;
        this.grid1 = new float[width][height];
    }

    public void init(){
        int xh = grid1.length - 1;
        int yh = grid1[0].length - 1;
        //Setting corners
        grid1[0][0] = random.nextFloat() - 0.5f;
        grid1[xh][0] = random.nextFloat() - 0.5f;
        grid1[0][yh] = random.nextFloat() - 0.5f;
        grid1[xh][yh] = random.nextFloat() - 0.5f;
        generateFractal(0, 0, xh, yh);
    }

    private void generateFractal(int x1, int y1, int xh, int yh) {
        int xm = (x1 + xh) /2;
        int ym = (y1 + yh) /2;
        if((x1 == xm) && (y1 == ym)) return;

        grid1[xm][y1] = (grid1[x1][y1] + grid1[xh][y1]) * 0.2f;
        grid1[xm][yh] = (grid1[x1][yh] + grid1[xh][yh]) * 0.2f;
        grid1[x1][ym] = (grid1[x1][y1] + grid1[x1][yh]) * 0.2f;
        grid1[xh][ym] = (grid1[xh][y1] + grid1[xh][yh]) * 0.2f;

        float v = roughen(0.2f * (grid1[xm][y1] + grid1[xm][yh]), x1 + y1, yh
                + xh);

        grid1[xm][ym] = v;

        grid1[xm][y1] = roughen(grid1[xm][y1], x1, xh);
        grid1[xm][yh] = roughen(grid1[xm][yh], x1, xh);
        grid1[x1][ym] = roughen(grid1[x1][ym], y1, yh);
        grid1[xh][ym] = roughen(grid1[xh][ym], y1, yh);

        generateFractal(x1, y1, xm, ym);
        generateFractal(xm, y1, xh, ym);
        generateFractal(x1, ym, xm, yh);
        generateFractal(xm, ym, xh, yh);
    }

    public void setTerrainTypes(Terrain[][] terrains){
        int w = grid1.length;
        int h = grid1[0].length;
        for(int i = 0;i < w;i++) {
            for(int j = 0;j < h;j++) {
                if(grid1[i][j] < 3){
                    terrains[i][j].setTerrainType(0);
                }else{
                    terrains[i][j].setTerrainType(2);
                    terrains[i][j].setPassable(false);
                }
            }
        }
    }

    private float roughen(float v, int l, int h) {
        return v + roughness * (float) (random.nextGaussian() * (h - l));
    }

    /**
     * Method used to print grid containing terrains.
     * @param terrains grid of type Terrain.
     */
    public void printTerrainGrid(Terrain[][] terrains){
        for(int i = 0; i < terrains.length; i++){
            for(int j = 0; j < terrains[0].length; j++){
                System.out.print(terrains[i][j].getTerrainType());
                System.out.print(",");
            }
            System.out.println();
        }
    }

    /**
     * Method used to print out grid containing booleans.
     * @param booleans Grid of type boolean.
     */
    public void printBooleanGrid(boolean[][] booleans){
        for(int i = 0; i < booleans.length; i++){
            for(int j = 0; j < booleans[0].length; j++){
                System.out.print(booleans[i][j]);
                System.out.print(",");
            }
            System.out.println();
        }
    }

    /**
     * Index set to true if terrainType < v, and false if terrainType > v
     * @param v value to compare value in the grid.
     * @return Returns a new grid containing booleans.
     */
    public boolean[][] toBooleans(float v) {
        int w = grid.length;
        int h = grid[0].length;

        boolean[][] ret = new boolean[w][h];
        for(int i = 0;i < w;i++) {
            for(int j = 0;j < h;j++) {
                ret[i][j] = grid[i][j].getTerrainType() < v;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        GameMap gameMap = new GameMap(30, 30);
        Noise n = new Noise(1, gameMap);
        n.printTerrainGrid(gameMap.getGameMapCoordinates());
    }


}
