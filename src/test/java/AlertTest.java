import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class AlertTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test//у арерта нет дом дерева
    public void checkDynamicControls() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");//открываем страницу
        driver.findElement(By.cssSelector("[onclick = 'jsAlert()']")).click();
        Alert alert = driver.switchTo().alert();
        /*alert.accept();
        alert.getText();
        alert.dismiss();
         */
        alert.sendKeys("ввод текста");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
