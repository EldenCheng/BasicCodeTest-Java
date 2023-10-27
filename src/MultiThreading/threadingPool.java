package MultiThreading;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable{

    private String name;
    Task(String task_name){
        name = task_name;
    }
    public void run(){
        try
        {
            for (int i = 0; i<=5; i++)
            {
                Date d = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                if (i==0)
                {
                    System.out.println("Initialization Time for"
                            + " task name - "+ name +" = " +ft.format(d));
                    //prints the initialization time for every task
                }
                else
                {
                    System.out.println("Executing Time for task name - "+
                            name +" = " +ft.format(d));
                    // prints the execution time for every task
                }
                Thread.sleep(1000);
            }
            System.out.println(name+" complete");
        }

        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

public class threadingPool {

    public static void main(String[] args) throws InterruptedException {
        // creates five tasks
        Runnable r1 = new Task("task 1");
        Runnable r2 = new Task("task 2");
        Runnable r3 = new Task("task 3");
        Runnable r4 = new Task("task 4");
        Runnable r5 = new Task("task 5");

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(4);

        // passes the Task objects to the pool to execute (Step 3)
        // 将线程添加进入线程池运行, 添加进线程池的对象必须为继承了Runnable接口或者Callable接口的对象
        // 其中Runnable接口对象无返回, Callable接口对象有返回
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        // pool shutdown ( Step 4)
        /*
        要注意这里几个方法的区别
        pool.shutdown()并不会终止已经在执行的线程, 而是关闭了线程池的入口, 线程池shutdown之后就不会再接受新的线程加入(submit)
        pool.awaitTermination(时间)是在pool.shutdown()之后才生效的, 同样这个方法也不会终止已经在执行的线程, 而是监测所有线程是否已经
        全部终止, 直到timeout设定的时间, 方法调用结束后返回一个bool值
        pool.shutdownNow()会尝试向所有仍在运行的线程发送terminal消息, 一般来说可以关闭所有正在运行的线程, 但如果有些线程定义了
        ignore interrupt消息的话, 这些线程也不能通过这个方法关闭, 就只能调用线程自己的terminate方法来关闭
         */
        pool.shutdown();
        if(pool.awaitTermination(5, TimeUnit.MINUTES))  // 这个的timeout时间可能要设置为你预估的整个程序运行的时间
            pool.shutdownNow();
    }

}



