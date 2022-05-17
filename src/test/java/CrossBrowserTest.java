import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;

public class CrossBrowserTest {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass
    @Parameters("browser")
    public void setupDriver(String browser) throws Exception {
        if(browser.equalsIgnoreCase("Chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            js = (JavascriptExecutor)driver;
        }
        else if(browser.equalsIgnoreCase("Opera")){
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
            js = (JavascriptExecutor)driver;
        }
        else{
            throw new Exception("Browser is not correct");
        }
    }

    @Test
    public void fileUploadTest() {
        driver.get("http://the-internet.herokuapp.com/upload");
        driver.manage().window().maximize();


        WebElement upload = driver.findElement(By.id("file-submit"));
        File path = new File("src/test/resource/image.png");
        driver.findElement(By.id("file-upload")).sendKeys(path.getAbsolutePath());
        upload.click();
        System.out.println(driver.findElement(By.tagName("h3")).getText());

        // StaleElementReferenceException გამოწვევა და მოგვარება
        try{
            upload.click();
        }catch(StaleElementReferenceException e){
            System.out.println(e);
        }
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
