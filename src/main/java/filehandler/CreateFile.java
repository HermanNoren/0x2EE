package filehandler;

import java.io.File;
import java.io.IOException;

public class CreateFile {
    String path;
    File file;

    public CreateFile(String path){
        this.path=path;
        try{
            file = new File(path);
            if(file.createNewFile()){ //returns true if successful
                System.out.println("New file created: " + file.getName());
            }else {
                System.out.println("File already exists");
            }
        }catch (IOException e){
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }

}
