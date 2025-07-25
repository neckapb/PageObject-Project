package com.example.demo.tests;

import com.example.demo.pages.MainPage;
import com.example.demo.pages.ResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingSearchTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() { driver.quit(); }

    @Test
    public void searchResults() {
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        By link = By.xpath("//a[contains(@class, 'tilk')][contains(@href, 'selenium.dev')]");
        wait.until(ExpectedConditions.and(
                ExpectedConditions.attributeContains((link), "href", "selenium"),
                ExpectedConditions.elementToBeClickable(link)));

        ResultsPage rp = new ResultsPage(driver);
        rp.clickElement(0);

        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "открылась неверная ссылка");
    }

    @Test
    public void searchField() {
        String input = "Selenium";

        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage rp = new ResultsPage(driver);
        assertEquals(input, rp.getTextFromSearchField(), "Текст не совпал");
    }
}
