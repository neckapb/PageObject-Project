package com.example.demo.tests;

import com.example.demo.pages.MainPage;
import com.example.demo.pages.ResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingSearchTests {

    private WebDriver driver;
    MainPage mp;
    ResultsPage rp;
    String input = "Selenium";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
        mp = new MainPage(driver);
        rp = new ResultsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchResults() {
        mp.sendText(input);
        rp.bingFix();
        rp.clickElement(0);
        rp.nepexog();
        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "открылась неверная ссылка");
    }

    @Test
    public void searchField() {
        mp.sendText(input);
        assertEquals(input, rp.getTextFromSearchField(), "Текст не совпал");
    }
}
