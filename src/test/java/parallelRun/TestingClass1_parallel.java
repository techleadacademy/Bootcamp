package parallelRun;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.RetryAnalyzer;

import java.lang.reflect.Method;

public class TestingClass1_parallel extends TestBase_parallel {

    @Test(description = "Testing google search")
    public void FirstTest(Method method) throws InterruptedException {
        getDriver().navigate().to("https://google.com");
        getDriver().findElement(By.name("q")).sendKeys("selenium");
        Thread.sleep(1000);
        getDriver().findElement(By.name("btnK")).click();
        Thread.sleep(3000);
        logger.info("Tittle of the page: " + getDriver().getTitle());
    }

    @Test(description = "Testing Amazon website")
    public void SecondTest(){
        getDriver().get("https://amazon.com");
        logger.info("Tittle of the page: " + getDriver().getTitle());
        Assert.fail();
    }

    @Test(description = "Tech Lead Academy website testing")
    public void ThirdTest(){
        getDriver().get("https://techleadacademy.io");
        logger.info("Tittle of the page: " + getDriver().getTitle());
        Assert.fail();
    }

//    @Test(description = "Tech Lead Academy website testing", retryAnalyzer = RetryAnalyzer.class)
//    public void FourthTest(){
//        getDriver().get("https://techleadacademy.io");
//        logger.info("Tittle of the page: " + getDriver().getTitle());
//        Assert.fail();
//    }






}
