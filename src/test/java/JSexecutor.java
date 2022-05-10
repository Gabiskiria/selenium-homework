import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class JSexecutor {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor)driver;
    }

    @Test
    public void jsExecutorTest() {
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");
        driver.manage().window().maximize();

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//li[text()=' Practice magic']"))).perform();
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[text()=' Practice magic']/span")));

        WebDriverWait wait = new WebDriverWait(driver,3);
        if(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[text()=' Practice magic']")))){
            System.out.println("deleted");
        }else{
            System.out.println("not deleted");
        }
    }

    @Test
    public void scrollTest() {
        driver.get("http://webdriveruniversity.com/Scrolling/index.html");
        driver.manage().window().maximize();

        js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("zone2-entries")));
        String text = (String) js.executeScript("return document.getElementById('zone2-entries').innerText;");
        if(text.equals("0 Entries")){
            System.out.println("equal");
        }else{
            System.out.println("not equal");
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
