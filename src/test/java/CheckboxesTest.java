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

public class CheckboxesTest {
    WebDriver driver; //объявляем driver
    //инициализируем драйвер
    @BeforeMethod //этот метод будет выполняться перед каждым тестовым методом в этом классе.
    // Грубо говоря предусловия перед текст-кейсом
    public void setip() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkCheckBoxes() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("input[type=checkbox]"));//коллекцией запросили
        //элементы с типом чек бокс\
        softAssert.assertFalse(checkBoxes.get(0).isSelected());//проверяем что первый чекбокс не нажат использую комбинацию
        //assertFalse + isSelected
        softAssert.assertTrue(checkBoxes.get(1).isSelected());//со вторым чекбоксом аналогично, только true
        checkBoxes.get(0).click();//нажимаем на чек бокс
        softAssert.assertTrue(checkBoxes.get(0).isSelected());//снова проверяем
        checkBoxes.get(1).click();//повторяю для второго
        softAssert.assertFalse(checkBoxes.get(1).isSelected());
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)//постусловие после тест-кейса. alwaysRun=true говорит о том, что
    //этот метод нужно выполнять всегда, вне зависимости от выполнения теста
    public void tearDown() {
        driver.quit();
    }
}
