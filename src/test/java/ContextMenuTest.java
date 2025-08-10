import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContextMenuTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkContextMenuValidationText() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
        WebElement contextMenu = driver.findElement(By.xpath("//*[@id='hot-spot']"));
        Actions actions = new Actions(driver);
        actions.contextClick(contextMenu).perform();//кликаем правой клавишей мыши
        Alert alert = driver.switchTo().alert();//переходим в алерт
        //alert.getText();//получаю текст из алерта
        Assert.assertEquals(alert.getText(), "You selected a context menu",
                "Текст не соответствует");//сразу получаю текст и сравниваю его
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}