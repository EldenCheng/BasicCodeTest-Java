package file;

import java.nio.file.Files;
import java.io.File;

public class FileCopy {

    public static void main(String[] args) {
       try {
           File srcfile = new File("./test_file.txt");

           File desfile = new File("./test_dir/copiedfile.txt");

           Files.copy(srcfile.toPath(), desfile.toPath());
       } catch (Exception e){
           e.printStackTrace();
       }
    }

}
