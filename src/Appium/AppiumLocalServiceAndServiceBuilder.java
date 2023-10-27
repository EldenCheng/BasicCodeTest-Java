package Appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

public class AppiumLocalServiceAndServiceBuilder {
    public static void main(String[] args) {
        File app_path = new File("./app-bitrise-signed.apk"); // 为了使用相对路径

        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("R52R80LED2J")  // udid是设备唯一的识别号, 如果要指定设备, 就一定要定义这一项
                .setApp(app_path.getAbsolutePath());  // setApp会自动安装指定的apk文件

        /*
        有时我们需要在程序中控制Appium服务的开启与关闭
        一般情况下我们会直接调用命令行来启动一个appium server
        但appium server启动之后相对来说不容易控制
        appium java client提供了另一个方法就是使用
        AppiumDriverLocalService与AppiumServiceBuilder来启动appium server
        这样就可以更加方便地使用程序来控制appium server了
         */
        AppiumDriverLocalService service;
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                // 指定一些appium服务运行参数
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info:debug")
                .withArgument(GeneralServerFlag.LOG_NO_COLORS)
                // 使Appium log输出到文件
                // 注意这里有汇报说有错误(EBusy)发生, 使用较低版本的appium-client与selenium可以解决
                .withLogFile(new File("appium_log"))
                .build();

        // 默认Appium log会输出到控制台的, 如果不想log输出到控制台, 可以加下面这一句
        service.clearOutPutStreams();
        service.start();  // 开启Appium服务

        AndroidDriver driver = new AndroidDriver(service.getUrl(), options);
        try {
//            WebElement el = driver.findElement(AppiumBy.xpath("//Button"));
//            el.click();
            System.out.print(driver.getPageSource());
        } finally {
            driver.quit();
            service.stop();
        }
    }
}
