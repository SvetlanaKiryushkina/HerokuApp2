import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class UploadFileTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkUploadFile() {
        driver.get("https://the-internet.herokuapp.com/upload");
        /*driver.findElement(By.cssSelector("[type=file]")).sendKeys("C:/Users/svetlana."+
                "kiryushkina/IdeaProjects/HerokuApp2/src/test/resources/1.txt");//здесь указан абсолютный путь и
        он нам не подходит, т.к может работать только на конкретном компе. Нужен относительный путь
         */
        /*driver.findElement(By.cssSelector("[type=file]")).sendKeys("src/test/resources/1.txt");//здесь
        указан относительный путь. НО SENDKEYS не работает с относительными путями
        */
        File file = new File("src/test/resources/1.txt");//создали файл с относительным путем
        driver.findElement(By.cssSelector("[type=file]")).sendKeys(file.getAbsolutePath());//т.е говорим,
        // что надо работать с абсолютным путем
        driver.findElement(By.xpath("//*[@id='file-submit']")).click();
        WebElement nameFile = driver.findElement(By.id("uploaded-files"));
        Assert.assertEquals(nameFile.getText(), "1.txt", "Не соответствует");
    }

    @AfterMethod(alwaysRun = true)//постусловие после тест-кейса. alwaysRun=true говорит о том, что
    //этот метод нужно выполнять всегда, вне зависимости от выполнения теста
    public void tearDown() {
        driver.quit();
    }
}
