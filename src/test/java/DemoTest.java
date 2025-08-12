import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class DemoTest {

    @Test
    public void seleniumTest() {
        ChromeOptions options = new ChromeOptions();//объявляем опции для нашего драйвера
        options.addArguments("--start-maximized");//добавляем аргументы при помощи addArguments, например
        //start-maximized для открытия браузера в мах размере
        options.addArguments("--incognito");//открыть браузер инкогнито, без кэша и т.д
        options.addArguments("--disable-notification");//блокируем всплывающие окна/уведомления сайта
        //options.addArguments("--headless"); //запуск тестов без визуального запуска браузера, например для
        // удаленных серверов
        HashMap<String, Object> chromePrefs = new HashMap<>();//создается словарик настроек хрома
        chromePrefs.put("credentials_enable_service", false);//в этот словарик добавляются сложные настройки
        chromePrefs.put("profile.password_manager_enabled", false);//типа отключение менеджера проверки пароля
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

//после настройки опций, их необходимо передать в наш драйвер. Для этого добавляем слово options
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//не явное ожидание/таймаут

        /*String token = "mfesmrfdoegrm529596form"; //объявляем переменную значения токена
        Cookie cookie = new Cookie("authToken",token);//прокидываем куку
        driver.manage().addCookie(cookie);//после этого добавляем эту куку в наш браузер

         */
        driver.switchTo().newWindow(WindowType.TAB);//открывает новую вкладку

        driver.get("https://the-internet.herokuapp.com/"); //запускаем наш сайт через выбранный браузер
        driver.quit();//закрывает браузер полностью
        //driver.close();//закрывает вкладку браузера
    }
}
