import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by yxwang on 20/06/2017.
 */
public class DemoWithoutJunit{
    public static void main(String[] args) throws Exception{
        System.out.println("start session...");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Android Device");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion","5.1");
        capabilities.setCapability("app","/Users/yxwang/JavaProjects/ilogistics-e2e-master/MyApplication/Haoguanjia/src/main/java/apps/app-sit-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        System.out.println("one");
        System.out.println("two");
        System.out.println("three");

        driver.quit();
    }
}
