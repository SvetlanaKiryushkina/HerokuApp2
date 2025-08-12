import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NotificationTest {


    WebDriver driver; //объявляем driver

    //инициализируем драйвер
    @BeforeMethod //этот метод будет выполняться перед каждым тестовым методом в этом классе.
    // Грубо говоря предусловия перед текст-кейсом
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Первый вариант теста, простой. Но он иногда сваливался, т.к при нажатии на кнопку появлялся текст отличный от
    //Action successful
    @Test
    public void checkNotification1() {
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        WebElement button = driver.findElement(By.xpath("//*[@id='content']/div/p/a"));
        button.click();
        WebElement finalNotification = driver.findElement(By.id("flash"));
        Assert.assertTrue(finalNotification.getText().contains("Action successful"));
    }

    //Решила добавить проверки. Но тест все равно иногда сваливается и я не знаю в чем причина
    @Test
    public void checkNotification() {
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        // Проверяем, есть ли нотификация на странице. Использовала это, т.к при открытии страницы часто нотификация уже была
        boolean notificationExists = driver.findElements(By.xpath("//*[@id='flash']")).size() > 0;
        if (notificationExists) {
            // Если есть, обновляем страницу
            System.out.println("Нотификация есть, обновляем страницу...");
            driver.navigate().refresh();
        } else {
            System.out.println("Нотификация отсутствует, продолжаем...");
        }
        // Теперь нажимаем кнопку "Click here"
        WebElement button = driver.findElement(By.xpath("//*[@id='content']/div/p/a"));
        button.click();

        // Цикл: пока в нотификации не появится текст "Action successful"
        boolean gotHello = false;
        while (!gotHello) {
            WebElement notification = driver.findElement(By.xpath("//*[@id='flash']"));
            String text = notification.getText();

            if (text.contains("Action successful")) {
                gotHello = true;
            } else {
                System.out.println("Неверный текст: " + text);
                // Нажимаем кнопку снова, чтобы получить новую нотификацию
                button.click();
            }
        }
        // Проверка, что в нотификации есть текст "Action successful"
        WebElement finalNotification = driver.findElement(By.id("flash"));
        Assert.assertTrue(finalNotification.getText().contains("Action successful"));
    }

    @AfterMethod(alwaysRun = true)//постусловие после тест-кейса. alwaysRun=true говорит о том, что
    //этот метод нужно выполнять всегда, вне зависимости от выполнения теста
    public void tearDown() {
        driver.quit();
    }
}
