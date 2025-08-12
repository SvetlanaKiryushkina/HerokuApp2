import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicControlsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkDynamicControls() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");//открываем страницу
        driver.findElement(By.xpath("//*[text()='Remove']")).click();//нажимаем кнопку ремув
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));//обьявляем явное ожидпние,
        // устанавливаем явное ожидание
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));//ждем что появилось сообщение,
        // как только появилось переходим дальше
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[type=checkbox]")));//в течении
        // 10 сек проверяем что пропал чек бокс на странице
        WebElement input = driver.findElement(By.xpath("//*[@id='input-example']/input"));//находим импут
        wait.until(ExpectedConditions.attributeToBe(input, "disabled", "true"));//проверяем что импут дизайбл
        driver.findElement(By.xpath("//*[text()='Enable']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));//ждем что появилось сообщение
        wait.until(ExpectedConditions.elementToBeClickable(input));//проверяем что импут активный, т.е enable
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
