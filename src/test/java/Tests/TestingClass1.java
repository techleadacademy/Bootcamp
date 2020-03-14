package Tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TestingClass1 extends TestBase{

    @Test(description = "Testing google search")
    public void FirstTest(Method method) throws InterruptedException {
        driver.navigate().to("https://google.com");
        logger.info("Title: " + driver.getTitle());
        logger.info(("Description: " + test.description()));
        screenshot.takeScreenshotAndLog();
        driver.findElement(By.name("q")).sendKeys("selenium");
        Thread.sleep(1000);
        driver.findElement(By.name("btnK")).click();
        Thread.sleep(3000);
        logger.info("Title: " + driver.getTitle());
        logger.info("Scenario name: "+method.getName());
        screenshot.takeScreenshotAndLog();

    }

    @Test(description = "Testing Amazon website")
    public void SecondTest(){
        driver.get("https://amazon.com");
        logger.info("Title: " + driver.getTitle());
        screenshot.takeScreenshotAndLog();
    }

    @Test(description = "Tech Lead Academy website testing")
    public void ThirdTest(){
        driver.get("https://techleadacademy.io");
        logger.info("Title: " + driver.getTitle());
        screenshot.takeScreenshotAndLog();
    }

    @Test(description = "Failed test example")
    public void FailTest(){
        Assert.fail();
    }

    @Test(description = "Skipped option")
    public void SkipTest(){
        throw new SkipException("Test skipped");
    }




}
