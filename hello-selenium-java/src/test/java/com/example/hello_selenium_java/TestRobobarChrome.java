package com.example.hello_selenium_java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class TestRobobarChrome {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test3robobar() throws MalformedURLException {

        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444"), chromeOptions);

        driver.get("http://10.250.10.2:3000/");
        driver.manage().window().setSize(new Dimension(907, 886));

        //driver.findElement(By.cssSelector(".ng-scope:nth-child(1) > td .input-group-append > .btn")).click();
        WebElement we = new WebDriverWait(driver, 10)
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ng-scope:nth-child(1) > td .input-group-append > .btn")));
        we.click();
        vars.put("total", driver.findElement(By.cssSelector("tr:nth-child(4) > .ng-binding")).getText());
        System.out.println("total=" + vars.get("total").toString());
        assertEquals(vars.get("total").toString(), "€1.25");
        driver.findElement(By.cssSelector(".ng-scope:nth-child(2) > td .input-group-append > .btn")).click();
        vars.put("total", driver.findElement(By.cssSelector("tr:nth-child(4) > .ng-binding")).getText());
        System.out.println("total=" + vars.get("total").toString());
        assertEquals(vars.get("total").toString(), "€3.25");
        driver.findElement(By.cssSelector(".ng-scope:nth-child(3) .input-group-append > .btn")).click();
        vars.put("total", driver.findElement(By.cssSelector("tr:nth-child(4) > .ng-binding")).getText());
        System.out.println("total=" + vars.get("total").toString());
        assertEquals(vars.get("total").toString(), "€6.25");

        //driver.findElement(By.cssSelector(".btn-success")).click();
        we = new WebDriverWait(driver, 10)
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-success")));
        we.click();

        vars.put("total", driver.findElement(By.cssSelector(".ng-scope:nth-child(1) > .ng-binding:nth-child(4)")).getText());
        System.out.println("Roba Cola total=" + vars.get("total").toString());
        assertEquals(vars.get("total").toString(), "€1.25");
        vars.put("total", driver.findElement(By.cssSelector(".ng-scope:nth-child(2) > .ng-binding:nth-child(4)")).getText());
        System.out.println("Robo Beer total=" + vars.get("total").toString());
        assertEquals(vars.get("total").toString(), "€2.00");
        vars.put("total", driver.findElement(By.cssSelector(".ng-scope:nth-child(3) > .ng-binding:nth-child(4)")).getText());
        System.out.println("Rob Wine total=" + vars.get("total").toString());
        assertEquals(vars.get("total").toString(), "€3.00");
        driver.findElement(By.id("ageInput")).click();
        driver.findElement(By.id("ageInput")).sendKeys("21");
        driver.findElement(By.cssSelector(".btn-success")).click();
    }

}
