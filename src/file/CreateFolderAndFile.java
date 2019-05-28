package file;

import java.io.File;
import java.io.IOException;

public class CreateFolderAndFile {

    public static void main(String[] args) {
        File file = new File("./test_file.txt");
        File dir = new File("./test_dir");
        if(file.exists() == false){
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        if(dir.isDirectory() == false){
            dir.mkdir();
        }

    }
}
