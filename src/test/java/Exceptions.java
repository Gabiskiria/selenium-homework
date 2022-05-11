import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exceptions {
    WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void exceptionTest() {
        driver.get("https://jqueryui.com/datepicker/");
        driver.manage().window().maximize();

        try{
            driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
            driver.findElement(By.id("datepicker")).click();
            driver.findElement(By.cssSelector("a[data-handler='prev']")).click();
            WebElement lastDay = driver.findElement(By.xpath("//tbody//tr[last()]//td[contains(@data-event,'click')][last()]"));
            lastDay.click();
            System.out.println(lastDay.getText());
        }catch (NoSuchElementException e){
            System.out.println(e);
        }catch(NoSuchFrameException e){
            System.out.println(e);
        }
    }

    @Test
    public void exceptionTest2() {
        driver.get("https://demoqa.com/alerts");
        WebElement startAlert =  driver.findElement(By.id("timerAlertButton"));
        startAlert.click();

        // Timeout exception - გამოწვევა და მოგვარება
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            System.out.println(e);
        }

        // NoAlertPresentException თავის არიდება
        try {
            WebDriverWait wait = new WebDriverWait(driver,7);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        }catch (NoAlertPresentException e){
            System.out.println(e);
        }catch (TimeoutException e){
            System.out.println(e);
        }

        // StaleElementReferenceException გამოწვევა და მოგვარება
        try {
            driver.navigate().refresh();
            startAlert.click();
        }catch(StaleElementReferenceException e) {
            System.out.println(e);
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
