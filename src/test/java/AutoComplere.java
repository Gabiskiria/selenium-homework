import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AutoComplere {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void autoComplete(){
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();

        driver.findElement(By.name("q")).sendKeys("Automation");
        wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='OBMEnb']//ul//li")));
        List autoSuggestion = driver.findElements(By.xpath("//div[@class='OBMEnb']//ul//li"));
        driver.findElements(By.xpath("//div[@class='OBMEnb']//ul//li")).get(autoSuggestion.size()-1).click();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
