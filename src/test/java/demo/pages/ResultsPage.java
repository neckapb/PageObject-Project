package demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ResultsPage {

    private WebDriver driver;

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    @FindBy(css = ".b_attribution")
    private List<WebElement> results;

    public void clickElement(int num) {
        results.get(num).click();
        System.out.println("Нажатие на результат под номером " + num);
    }

    public void nepexog() {
        ArrayList tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) driver.switchTo().window(tabs.get(1).toString());
    }

    public void bingFix() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        By link = By.xpath("//a[contains(@class, 'tilk')][contains(@href, 'selenium.dev')]");
        wait.until(ExpectedConditions.and(
                ExpectedConditions.attributeContains((link), "href", "selenium"),
                ExpectedConditions.elementToBeClickable(link)));

    }

    public String getTextFromSearchField() {
        String val = searchField.getAttribute("value");
        System.out.println("В строке поиска текст: " + val);
        return val;
    }

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
