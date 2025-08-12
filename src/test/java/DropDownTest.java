import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class DropDownTest {
    WebDriver driver; //объявляем driver

    //инициализируем драйвер
    @BeforeMethod //этот метод будет выполняться перед каждым тестовым методом в этом классе.
    // Грубо говоря предусловия перед текст-кейсом
    public void setip() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkDropDown() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropDown = driver.findElement(By.id("dropdown"));//создаем элемент, который идентифицирую по дропдаун
        Select select = new Select(dropDown);//экземпляр класса селект, у которого есть множество
        // методов с которыми можно взаимодействовать
        List<WebElement> option = select.getOptions(); //получили все опции выпадающего списка используя коллекцию
        Assert.assertEquals(option.get(0).getText(), "Please select an option");//в скобках указываем номер
        //номер элемента из полученной коллекции (начинаем с 0) и запрашиваем текст опции. И проверяем ассертом, что
        //соответствует ожидаемому результату
        softAssert.assertEquals(option.get(1).getText(), "Option 1");
        softAssert.assertEquals(option.get(2).getText(), "Option 2");
        //т.е мы сейчас запросили список из выпадашки и проверили, что текст этого списка совпадает с ожиданиями
        select.selectByVisibleText("Option 1");//выбираем опцию из выпадающего списка
        softAssert.assertTrue(select.getFirstSelectedOption().isSelected());//проверяем то что действительно выбрано нужное значение
        select.selectByVisibleText("Option 2");//выбираем вторую опцию из выпадающего списка
        softAssert.assertTrue(select.getFirstSelectedOption().isSelected());
        softAssert.assertAll();//закрываем софтассерт
    }

    @AfterMethod(alwaysRun = true)//постусловие после тест-кейса. alwaysRun=true говорит о том, что
    //этот метод нужно выполнять всегда, вне зависимости от выполнения теста
    public void tearDown() {
        driver.quit();
    }
}
