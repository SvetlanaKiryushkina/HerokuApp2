import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TyposTest {

    WebDriver driver; //объявляем driver

    //инициализируем драйвер
    @BeforeMethod //этот метод будет выполняться перед каждым тестовым методом в этом классе.
    // Грубо говоря предусловия перед текст-кейсом
    public void setip() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkTypos() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/typos");
        //Ищу элемент не по имени тега, а по xpath. Т.к по имени тега находило другую строчку
        WebElement typos = driver.findElement(By.xpath("//*[@id='content']/div/p[2]"));
        String firstText = typos.getText();
        for (int i = 0; i <= 10; i++) { //используем цикл, который будет обновлять страницу 11 раз. Указываю 10,
            // т.к 0-10 = 11 раз
            driver.navigate().refresh();//стандартная конструкция для обновления страницы
            //нахожу текст после обновления
            WebElement typosAfterRefresh = driver.findElement(By.xpath("//*[@id='content']/div/p[2]"));
            String currentText = typosAfterRefresh.getText();

            // сравниваем с эталонным, т.е найденным сразу при переходе на страницу
            softAssert.assertEquals(firstText, currentText, "Текст не соответствует правильному после обновления страницы");
        }
        softAssert.assertAll();//закрываем софтассерт
    }

    @AfterMethod(alwaysRun = true)//постусловие после тест-кейса. alwaysRun=true говорит о том, что
    //этот метод нужно выполнять всегда, вне зависимости от выполнения теста
    public void tearDown() {
        driver.quit();
    }
}
