package filehandler;
import model.mapclasses.GameMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteMapToFile {

    private BufferedWriter bw;
    private final String pathTo;
    public WriteMapToFile(String pathTo){
        this.pathTo = pathTo;
        try{
            bw = new BufferedWriter(new FileWriter(pathTo));
        }catch (IOException e){
            System.out.println("An error occured while writing to: " + pathTo);
            e.printStackTrace();
        }
    }

    public void writeToFile(GameMap gameMap){
        try {
            int col = 0;
            int row = 0;
            while (col< gameMap.getWidth() && row < gameMap.getHeight()){
                if(row != 0)bw.newLine();
                while (col< gameMap.getWidth()){
                    bw.write(gameMap.getGameMapCoordinates()[col][row].getTerrainType()+" ");
                    col++;
                }

                if (col == gameMap.getWidth()){
                    col=0;
                    row++;

                }
            }
//            for (int i = 0; i < terrains.length; i++){
//                if(i!=0) bw.newLine();
//                for (int j = 0; j < terrains.length; j++){
//                    bw.write(terrains[i][j].getTerrainType()+" ");
//                }
//            }
            bw.close();
            System.out.println("Successfully wrote to file: " + pathTo);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
