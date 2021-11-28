package com.example.SDETExercise.pages;

import com.example.SDETExercise.config.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BasePage {

    private static final String chromeDriverPath = Configuration.getInstance("application").getValue("chromeDriverPath");

    public static WebDriver driver;

    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void navigate(String url) {
        setDriver();
        driver.navigate().to(url);
    }

    public void closeDriver() {
        driver.close();
    }

}
