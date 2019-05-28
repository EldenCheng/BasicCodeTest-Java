package stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToStringBasic {

	public static void main(String[] args) throws IOException {
		//获取一个String Stream，因为Java没有专门的String Stream类，所以把字放到父类Stream中
		Stream stream = Files.lines(Paths.get("../0.txt"), StandardCharsets.UTF_8);
		//使用toArray方法转换成字符串数组，由于上面读取时是以行为单位，所以转换也以行为单位
		//不知道为什么前面一定要加强制类型转换
		String[] strArray1 = (String[]) stream.toArray(String[]::new);
		System.out.println(strArray1[0]);
		
		//转换成List
		stream = Files.lines(Paths.get("../0.txt"), StandardCharsets.UTF_8);
		List<String> list1 = (List<String>) stream.collect(Collectors.toList());
		System.out.println(list1.get(0));
		
		//由于Stream操作一次之后就失效了，所以如果需要再使用，必有重新获取
		stream = Files.lines(Paths.get("../0.txt"), StandardCharsets.UTF_8);
		//实际上是先转化为一个字符串集合，然后将人字符串集体合并后直接转换为字符串
		String str = stream.collect(Collectors.joining()).toString();
		System.out.println(str);

	}

}
