package ru.netology.service;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


class CreditCardAppTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }


    @Test
    void ifEverythingIsCorrect() {
        Configuration.holdBrowserOpen = true;
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Бендер-Задунайский Остап");
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("+79313363998");
        //driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        //      driver = new ChromeDriver();

        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

}