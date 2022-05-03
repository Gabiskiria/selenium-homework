import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WaitsTest {
    WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void commandCheck(){
        driver.get("https://demoqa.com/progress-bar");
        driver.manage().window().maximize();

        driver.findElement(By.id("startStopButton")).click();
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class$='bg-success']")));

        String expectedBar = "100%";
        String progressBar =  driver.findElement(By.cssSelector("div[class$='bg-success']")).getText();
        if(progressBar.equals(expectedBar)){
            System.out.println(progressBar);
        }else{
            System.out.println("Not fully loaded bar");
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
