package parallelRun;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TestBase_parallel {

    public Test test;
    public static final Logger logger = LogManager.getLogger("Log");
    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    @BeforeMethod
    public void setUp(Method method){
        test = method.getAnnotation(Test.class);
        WebDriverManager.chromedriver().setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        WebDriver driver = new ChromeDriver();
        drivers.set(driver);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        logger.info("Starting test execution of Test");
    }

    @AfterMethod
    public void tearDown(Method method, ITestResult result){
        getDriver().close();
    }

    public WebDriver getDriver(){
        WebDriver driver = drivers.get();
        return driver;
    }



}
