package filepath;

import java.io.IOException;

//从JDK7开始引入了新的文件操作类nio.file
//从JDK8开始，又在nio包里引入了Files.lines()方法使用文件流来读取文件内容，
//并且可以在文件流中直接使用StandardCharsets直接转换字符集
import java.nio.charset.StandardCharsets;  
import java.nio.file.Files;  
import java.nio.file.Paths;   

public class RelativePath {

	public static void main(String[] args) throws IOException {
		//将文件读入一个字符串流，编码为UTF_8,再使用JDK8新增加的专用于流的forEach方法来输出内容
		//要注意使用forEach方法要将JRE的版本选择为1.8之后的
		//可以使用./代表当前目录; ../代表上一级目录
		Files.lines(Paths.get("../0.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
		
		Files.lines(Paths.get("./1.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
		
		Files.lines(Paths.get("./src/3.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
		
	}

}
