import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FramesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkIframeValidationText() {
        driver.get("https://the-internet.herokuapp.com/frames");
        driver.findElement(By.xpath("//*[text()='iFrame']")).click();
        driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));//Переключаемся во фрейм
        String textActual = driver.findElement(By.id("tinymce")).getText();
        Assert.assertEquals(textActual, "Your content goes here.", "Текст не соответствует");
        driver.switchTo().defaultContent();//вышли с фрейма
    }

    @AfterMethod(alwaysRun = true)//постусловие после тест-кейса. alwaysRun=true говорит о том, что
    //этот метод нужно выполнять всегда, вне зависимости от выполнения теста
    public void tearDown() {
        driver.quit();
    }
}
