package stream;

import java.util.Random;
import java.util.function.IntConsumer;

public class forEachMethodOfStream {

	public static void main(String[] args) {
		Random random = new Random();
		random.ints().limit(10).forEach(System.out::println);
		//在流中使用forEach方法可以自动遍历所有元素，这里示例的是输出
		
		//也可以在forEach中使用lambda表达式
		random.ints().limit(10).forEach(x->{
			if(x >0)
			{
				System.out.println(x);
			}
		});
		
	}

}
