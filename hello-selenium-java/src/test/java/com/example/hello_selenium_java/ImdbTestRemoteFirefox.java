package com.example.hello_selenium_java;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class ImdbTestRemoteFirefox {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @BeforeEach
    public void setUp() {
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void imdb() throws MalformedURLException{

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444"), firefoxOptions);
        driver.get("https://www.imdb.com/");
        driver.manage().window().setSize(new Dimension(907, 886));
        driver.findElement(By.id("suggestion-search")).sendKeys("wandavision");
        driver.findElement(By.id("suggestion-search")).sendKeys(Keys.ENTER);
        WebElement we = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(By.linkText("WandaVision")));
        we.click();
        //driver.findElement(By.linkText("WandaVision")).click();
        we = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(By.linkText("TRIVIA")));
        we.click();
    }
}
