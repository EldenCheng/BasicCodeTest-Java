package filepath;

import java.io.File; 

public class GetTheRootFolderPath {

	public static void main(String[] args) {
		//将根目录的相对路径赋值于一个变量
		File directory = new File("./");
		//获取根目录的绝对路径
		System.out.println(directory.getAbsolutePath());

	}

}
