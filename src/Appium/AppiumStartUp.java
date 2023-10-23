package Appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class AppiumStartUp {
    public static void main(String[] args) throws MalformedURLException {
        /*
        下面这个是Appium2的写法, 与Appium1.x不一样, 使用options类代替了DesiredCapabilities类
        但其实定义的项目是差不多的, 具体可以设置什么请参考UiAutomator2Options文档中的各种set方法
         */

        File app_path = new File("./app-bitrise-signed.apk"); // 为了使用相对路径

        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("R52R80LED2J")  // udid是设备唯一的识别号, 如果要指定设备, 就一定要定义这一项
                .setApp(app_path.getAbsolutePath());  // setApp会自动安装指定的apk文件

        // Appium2中需要指定driver的种类, 如果无问题, 会连接手机并启动App
        // 这里有一个很常见的问题是连接手机时会出现NoMethodFound异常, 这个是Appium版本与Selenium版本
        // 不对应造成的, 具体版本对应表格可以参考appium java client页面里的兼容列表
        // 怎么在build tools中指定selenium版本可以参考:
        // https://github.com/appium/java-client/blob/master/docs/transitive-dependencies-management.md
        AndroidDriver driver = new AndroidDriver(
                // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
                new URL("http://127.0.0.1:4723/"), options
        );

        try {
            System.out.print(driver.getPageSource());
        } finally {
            driver.quit();
        }

    }
}