import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class AddRemoveElementsTest {
    WebDriver driver; //объявляем driver
    //инициализируем драйвер
    @BeforeMethod //этот метод будет выполняться перед каждым тестовым методом в этом классе.
    // Грубо говоря предусловия перед текст-кейсом
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkAddRemoveElements() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        //нажимаем два раза на кнопку Add Element
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        //далее нужно получить коллекцию элементов кнопок Делит, что бы проверить что их две
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//button[text()='Delete']"));
        //далее используя ассерт проверяем, что действительно две кнопки
        softAssert.assertEquals(deleteButtons.size(), 2);
        deleteButtons.get(1).click();//нажимаем на кнопку делит. Т.е используем уже название коллекции и позицию
        // элемента (помним, что начинается с 0)
        //Далее снова запрашиваем коллекцию элементов с названием делит
        List<WebElement> deleteButtonsAfterDelete = driver.findElements(By.xpath("//button[text()='Delete']"));
        //и снова проверяем, что их 1
        softAssert.assertEquals(deleteButtonsAfterDelete.size(), 1);
        softAssert.assertAll();//закрываем софтассерт
    }

    @AfterMethod(alwaysRun = true)//постусловие после тест-кейса. alwaysRun=true говорит о том, что
    //этот метод нужно выполнять всегда, вне зависимости от выполнения теста
    public void tearDown() {
        driver.quit();
    }
}
