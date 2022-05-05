import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class WebFormsTest {
    WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void commandCheck(){
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        driver.manage().window().maximize();

        Select selectLanguage = new Select(driver.findElement(By.id("dropdowm-menu-1")));
        selectLanguage.selectByValue("sql");

        List<WebElement> checkboxSelection = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (int i = 0; i<checkboxSelection.size(); i++){
            if(!checkboxSelection.get(i).isSelected()){
                checkboxSelection.get(i).click();
            }else{
                System.out.println("It was already selected!");
            }
        }

        List<WebElement> radioButtonSelection = driver.findElements(By.cssSelector("input[type='radio'][name='color']"));
        for(WebElement radioButton : radioButtonSelection) {
            if (radioButton.getAttribute("value").equals("yellow")) {
                radioButton.click();
                break;
            }else{
                System.out.println("Not yellow");
            }
        }

        WebElement disabledSelection = driver.findElement(By.cssSelector("option[value='orange']"));
        if (!disabledSelection.isEnabled()) {
            System.out.println("Disabled");
        } else {
            System.out.println("Not disabled");
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
