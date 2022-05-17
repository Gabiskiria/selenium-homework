import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class FileUploadTest {
    WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        driver = new HtmlUnitDriver(BrowserVersion.FIREFOX);

        //headless opera
//        WebDriverManager.operadriver().setup();
//        OperaOptions options = new OperaOptions();
//        options.addArguments("headless");
//        driver = new OperaDriver(options);
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

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
