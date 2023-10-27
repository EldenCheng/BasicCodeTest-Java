package Appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.*;

class Task implements Runnable{

    private final URL url;
    private final UiAutomator2Options options;
    private BlockingQueue<Object> queue;

    Task(URL url, UiAutomator2Options op, BlockingQueue<Object> q){
        this.url = url;
        this.options = op;
        this.queue = q;

    }
    public void run(){
        AndroidDriver driver = new AndroidDriver(this.url, this.options);

        try {
//            WebElement el = driver.findElement(AppiumBy.xpath("//Button"));
//            el.click();
            // System.out.print(driver.getPageSource());
            driver.activateApp("com.sec.android.app.clockpackage");
            Thread.sleep(2000);
            WebElement alarmButton = driver.findElement(AppiumBy.xpath("//*[contains(@content-desc, \"ALARM\") or contains(@content-desc, \"Alarm\")]"));
            WebElement worldClockButton = driver.findElement(AppiumBy.xpath("//*[contains(@content-desc, \"WORLD CLOCK\") or contains(@content-desc, \"World clock\")]"));
            for(int i = 0; i < 5; i++){
                alarmButton.click();
                Thread.sleep(1000);
//                this.queue.notify();
                this.queue.put("Alarm Button is clicked on " + Thread.currentThread().getName());
                Thread.sleep(1000);
                worldClockButton.click();
                Thread.sleep(1000);
                this.queue.put("WorldClock Button is clicked on " + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
            this.queue.put("QUIT");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.terminateApp("com.sec.android.app.clockpackage");
            driver.quit();
        }
    }
}

// 消息队列处理类
class QueueWork implements Runnable{

    private BlockingQueue<Object> queue;
    private final int threadNumber;
    private int quitThreadCount;

    QueueWork(BlockingQueue<Object> q, int threads){
        this.queue = q;
        this.quitThreadCount = 0;
        this.threadNumber = threads;

    }

    public void run(){
        try {
            while (true) {
                // 从队列读取消息
                String message = this.queue.take().toString();

                if(Objects.equals(message, "QUIT"))  // 如果有线程发出了退出消息, 退出计数器增加
                    this.quitThreadCount++;

                if(this.quitThreadCount >= this.threadNumber)  // 如果所有其它线程都已退出, 消息处理线程也同样退出
                    break;

                System.out.print(message + "\n");  // 处理获取到的消息
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

public class AppiumAndMultiThreading {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        UiAutomator2Options options_s7 = new UiAutomator2Options()
                .setAutomationName("uiautomator2")
                .setPlatformName("Android")
                .setDeviceName("Galaxy Tab S7+")
                .setPlatformVersion("11")
                .setUdid("R52R80LED2J")
                //.setApp("./app-bitrise-signed.apk");
                // .setApp(app_path.getAbsolutePath());
                .setAppPackage("com.android.settings")
                .setAppActivity(".Settings")
                .setNoReset(true);

        UiAutomator2Options options_s4 = new UiAutomator2Options()
                .setAutomationName("uiautomator2")
                .setPlatformName("Android")
                .setDeviceName("Galaxy Tab S4")
                .setPlatformVersion("8.1")
                .setUdid("988c0f59314b4a475833")
                //.setApp("./app-bitrise-signed.apk");
                // .setApp(app_path.getAbsolutePath());
                .setAppPackage("com.android.settings")
                .setAppActivity(".Settings")
                .setNoReset(true);

        int threadsNumber = 2;
        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(1);  // 初始化阻塞队列

        Task task_s7 = new Task(new URL("http://127.0.0.1:4722/"), options_s7, queue);
        Task task_s4 = new Task(new URL("http://127.0.0.1:4723/"), options_s4, queue);

        QueueWork message_queue = new QueueWork(queue, threadsNumber);

        // 建立线程池
        // +1是因为消息队列也需要一个线程来处理
        ExecutorService pool = Executors.newFixedThreadPool(threadsNumber + 1);

        // 将线程添加进入线程池运行, 添加进线程池的对象必须为继承了Runnable接口或者Callable接口的对象
        // 其中Runnable接口对象无返回, Callable接口对象有返回
        pool.submit(task_s7);
        pool.submit(task_s4);
        pool.submit(message_queue);

        pool.shutdown();
        if(pool.awaitTermination(5, TimeUnit.MINUTES))
            pool.shutdownNow();
    }
}