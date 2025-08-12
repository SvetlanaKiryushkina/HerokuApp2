import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {

    WebDriver driver; //объявляем driver
    //инициализируем драйвер
    @BeforeMethod //этот метод будет выполняться перед каждым тестовым методом в этом классе.
    // Грубо говоря предусловия перед текст-кейсом
    public void setip() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/inputs");//т.к несколько похожих проверок, вынесла переход на
        //страницу в предусловия
    }

    @Test
    public void checkInputsSpecialSymbols() {
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.sendKeys("!#$");
        // Проверяю, что значение поля пустое
        Assert.assertTrue(inputField.getAttribute("value").isEmpty(), "Ошибка: поле заполнилось" +
                "при вводе спец.символов");
    }

    @Test
    public void checkInputsLettersRUS() {
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.sendKeys("Проверка на буквы русского алфавита");
        // Проверяю, что значение поля пустое/
        Assert.assertTrue(inputField.getAttribute("value").isEmpty(), "Ошибка: поле заполнилось" +
                "при вводе букв");
    }

    @Test
    public void checkInputsLettersEng() {
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.sendKeys("Checking for letters of the English alphabet");
        // Проверяю, что значение поля пустое
        Assert.assertTrue(inputField.getAttribute("value").isEmpty(), "Ошибка: поле заполнилось" +
                "при вводе букв"); //Здесь нужна помощь. Я не знаю как написать этот тест, что бы тест падал с ошибкой,
        // т.к при таком вводе букв в поле вводится буква "е". При запуске этого теста, он все равно отрабатывает успешно
    }

    @Test
    public void checkInputsNumbers() {
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.sendKeys("123456");
        // Проверяю, что значение поля не пустое. Изменила на assertFalse, т.к поле должно быть заполнено
        Assert.assertFalse(inputField.getAttribute("value").isEmpty());
    }

    @Test
    public void checkInputsKeysARROW_UP_ARROW_DOWN() {
        SoftAssert softAssert = new SoftAssert();
        WebElement inputField = driver.findElement(By.tagName("input"));
        inputField.sendKeys("100");
        String oldValueStr = inputField.getAttribute("value");//получаю введенное значение и преобразую в число
        int oldValue = Integer.parseInt(oldValueStr);
        // Проверить, что значение поля не пустое. Но наверно можно не делать, т.к ранее уже выполняла тест с этой проверкой
        softAssert.assertFalse(inputField.getAttribute("value").isEmpty());
        inputField.sendKeys(Keys.ARROW_UP);//нажимаем на стрелочку вверх
        String newValueStr = inputField.getAttribute("value");//получаю новое значение из поля и преобразую в число
        int newValue = Integer.parseInt(newValueStr);
        //Проверяем, что введенное число увеличилось на 1
        softAssert.assertEquals(oldValue + 1, newValue, "Значение не увеличилось на 1");
        inputField.sendKeys(Keys.ARROW_DOWN);//нажимаем на стрелочку вниз
        String newValueStr2 = inputField.getAttribute("value");//получаю еще одно новое значение
        // из поля и преобразую в число
        int newValue2 = Integer.parseInt(newValueStr2);
        //Проверяем, что введенное число увеличилось на 1
        softAssert.assertEquals(newValue - 1, newValue2, "Значение не уменьшилось на 1");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)//постусловие после тест-кейса. alwaysRun=true говорит о том, что
    //этот метод нужно выполнять всегда, вне зависимости от выполнения теста
    public void tearDown() {
        driver.quit();
    }
}
