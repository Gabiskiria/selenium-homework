import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class CookieTest {
    WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @Test
    public void coockie(){
        driver.get("http://demo.guru99.com/test/cookie/selenium_aut.php");
        driver.manage().window().maximize();

        driver.findElement(By.name("username")).sendKeys("Natia");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.name("submit")).click();
        new WebDriverWait(driver, 3).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        driver.navigate().refresh();
        Assert.assertEquals(driver.findElement(By.cssSelector("h2.form-signin-heading")).getText(),"You are logged In");
        Set<Cookie> cookies = driver.manage().getCookies();

        for(Cookie cookie: cookies) {
            if(cookie.getName().equals("Selenium")){
                driver.manage().deleteCookie(cookie);
            }else if(cookie.getExpiry() == null){
                driver.manage().deleteCookie(cookie);
            }else{
                System.out.println("განსხვავებული ქუქიები");
            }
        }

        Set<Cookie> newCookies = driver.manage().getCookies();
        for(Cookie cookie: newCookies) {
            Assert.assertFalse(cookie.getName().equals("Selenium") || cookie.getExpiry() == null);
        }
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
