import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SwitchToTest {
    WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void framesTest() {
        driver.get("http://the-internet.herokuapp.com/iframe");
        driver.manage().window().maximize();

        driver.switchTo().frame("mce_0_ifr");
        WebElement inputElement = driver.findElement(By.id("tinymce"));
        inputElement.clear();  // გვერძე უწერდა არსებულ ტექსტს Here Goes, ამიტომ ჯერ გავასუფთავე და მერე ჩავწერე ტექსტი
        inputElement.sendKeys(" Here Goes");

        driver.switchTo().parentFrame();
//        driver.switchTo().defaultContent(); // ორივე მუშაობს ამ შემთხვევაში
        driver.findElement(By.cssSelector("button[title='Align center']")).click();
    }

    @Test
    public void alertTest() {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        driver.switchTo().alert();
        driver.switchTo().alert().accept();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
