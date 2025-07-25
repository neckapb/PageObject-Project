package com.example.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    public void sendText(String text) {
        searchField.sendKeys(text);
        searchField.submit();
        System.out.println("Bведен текст: " + text);
    }

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
