import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class WebElementsTest {
    WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void elements(){
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        driver.manage().window().maximize();

        WebElement addButton = driver.findElement(By.cssSelector("button[onClick='addElement()']"));
        for (int i = 0; i < 3; i++){
            addButton.click();
        }

        // css selector
        System.out.println(driver.findElement(By.cssSelector("#elements :last-child")).getAttribute("outerHTML"));
        // xpath
//        System.out.println(driver.findElement(By.xpath("//div[@id='elements']//button[last()]")).getAttribute("outerHTML"));

        List<WebElement> lastButton= driver.findElements(By.cssSelector("[class^='added']"));
        System.out.println(lastButton.get(lastButton.size() - 1).getAttribute("outerHTML"));

        System.out.println(driver.findElement(By.xpath("//button[contains(@class, 'manually') and text()='Delete'][last()]")).getAttribute("outerHTML"));
    }

    @Test
    public void xpath(){
        driver.get("http://the-internet.herokuapp.com/challenging_dom");

        System.out.println(driver.findElement(By.xpath("//div[@class='large-10 columns']/table/tbody/tr/td[text()='Apeirian9']/preceding-sibling::td")).getText());
        System.out.println(driver.findElement(By.xpath("//div[@class='large-10 columns']/table/tbody/tr/td[text()='Apeirian9']/following-sibling::td")).getText());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
