import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by yxwang on 20/06/2017.
 */
public class ToDoListTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Device");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("app", "/Users/yxwang/IdeaProjects/FirstAppiumDemo/src/main/java/apps/ToDoList.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void addItem(){
        //add new item
        String itemText = "Use Appium to write test";
        WebElement editText = driver.findElement(By.id("com.testerhome.appiumgirl.todolist:id/etNewItem"));
        editText.sendKeys(itemText);
        WebElement addBtn = driver.findElement(By.id("com.testerhome.appiumgirl.todolist:id/btnAddItem"));
        addBtn.click();

        //check if item is added
        List<AndroidElement> appiumItems = driver.findElementsByXPath("//android.widget.TextView[@text='" + itemText + "']");
        Assert.assertEquals("找不到待办事项"+itemText, false, appiumItems.isEmpty());
    }

    @Test
    public void addItemInWeekNetwork(){
        String itemText = "模拟弱网";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Add new item
        WebElement editText = driver.findElement(By.id("com.testerhome.appiumgirl.todolist:id/etNewItem"));
        editText.sendKeys(itemText);
        WebElement addItemBtn = driver.findElement(By.id("com.testerhome.appiumgirl.todolist:id/btnAddItem"));
        addItemBtn.click();

        // Check if item is added
        try{
            driver.findElementByXPath("//android.widget.TextView[@text='"+itemText+"']");
        }catch (Exception e) {
            throw new AssertionError("找不到待办事项 '" + itemText + "'");
        }finally {
            driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
        }
    }

}
