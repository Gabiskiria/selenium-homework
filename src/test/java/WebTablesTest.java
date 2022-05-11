import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class WebTablesTest {
    WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void tableTest(){
        driver.get("http://techcanvass.com/Examples/webtable.html");
        driver.manage().window().maximize();

        int companyNameIndex = 0;
        int companyPriceIndex = 0;

        List<WebElement> rows = driver.findElements(By.cssSelector("table[id='t01'] tbody tr"));
        List<WebElement> headerColumn = rows.get(0).findElements(By.cssSelector("th"));

        // ვპოულობ ინდექსებს - თეიბლჰედერების მიხედვით
        for(int i=0; i<headerColumn.size(); i++){
            if(headerColumn.get(i).getText().equals("Company Name")){
                companyNameIndex = i + 1;
            }else if(headerColumn.get(i).getText().equals("Ex-showroom Price(INR)")){
                companyPriceIndex = i + 1;
            }
        }

        List<WebElement> nameList = driver.findElements(By.xpath("//tbody/tr/td[" + companyNameIndex + "]"));

        for(int i = 0; i<nameList.size(); i++){
            if(nameList.get(i).getText().equals("Honda")){
                // iს ემატება 1 ინდექსის(0 დან რომ იწყება get_ში), 1ც თეიბლჰედერის, რომ ვიპოვოთ honda შესაბამისი tr
                WebElement hondaPrice = driver.findElement(By.xpath("//tbody/tr[" + (i + 2) + "]/td[" + companyPriceIndex + "]"));
                System.out.println(hondaPrice.getText());
            }
        }
    }

    @AfterClass
    public void tearDown(){
//        driver.quit();
    }
}
