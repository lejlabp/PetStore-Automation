package com.example.SDETExercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BasePage {

    @Autowired
    public WebDriver driver;

    public void navigate(String url) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
        this.driver.navigate().to(url);
    }
}
