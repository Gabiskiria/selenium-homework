import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CommandsTest {
    WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void commandCheck() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();
        WebElement button =  driver.findElement(By.xpath("//form[@id='input-example']//button"));
        button.click();

//        Thread.sleep(5000); // bad wait to wait
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // normal way to wait
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); // the best way to wait

        WebElement inputField = driver.findElement(By.xpath("//form[@id='input-example']/input"));
        WebElement text = driver.findElement(By.xpath("//form[@id='input-example']/p"));
        System.out.println("Enabled: " + inputField.isEnabled());
        System.out.println("Text displayed: " + text.isDisplayed());
        System.out.println("Button text: " + button.getText());
        inputField.sendKeys("Bootcamp");
        inputField.clear();
    }

    @Test
    public void cordinateCheck(){
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
        WebElement columnA= driver.findElement(By.id("column-a"));
        WebElement columnB= driver.findElement(By.id("column-b"));
        Point pointA = columnA.getLocation();
        Point pointB = columnB.getLocation();
        if(pointA.y == pointB.y){
            System.out.println("Cordinate: Same");
        }else{
            System.out.println("Cordinate: Not same");
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
