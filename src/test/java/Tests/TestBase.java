package Tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestResult;
import org.testng.annotations.*;
import util.Screenshot;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public WebDriver driver;
    public static final Logger logger = LogManager.getLogger("Log");
    public ExtentReports extent;
    public ExtentTest reporter;
    public Test test;
    Screenshot screenshot;

    @BeforeSuite
    public void startReport(){
        extent = new ExtentReports("target/extentReports.html", true);
        extent
                .addSystemInfo("Host name", "TLA Bootcamp")
                .addSystemInfo("Environment", "Test Environment");
        extent.loadConfig(new File("src/main/resources/extent-config.xml"));
    }

    @BeforeMethod
    public void setUp(Method method){
        test = method.getAnnotation(Test.class);
        WebDriverManager.chromedriver().setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Starting test execution of Test");

        reporter = extent.startTest("Starting a test" + method.getName());
        reporter.assignCategory(test.description());
        screenshot = new Screenshot(driver, reporter);
    }

    @AfterMethod
    public void tearDown(Method method, ITestResult result){
        logger.info("ENDING test execution of Test: " + method.getName());
        if(result.getStatus() == ITestResult.FAILURE) {
            //test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            reporter.log(LogStatus.FAIL, result.getName(), "Test was Unsuccessful");
            reporter.log(LogStatus.FAIL, result.getThrowable());
        } else if(result.getStatus() == ITestResult.SUCCESS) {
            reporter.log(LogStatus.PASS, result.getName(), "Test was successful");
        }
        else {
            reporter.log(LogStatus.SKIP, result.getName(), "Test was SKIPPED");
            //test.skip(result.getThrowable());
            reporter.log(LogStatus.SKIP, result.getThrowable());
        }
        extent.endTest(reporter);
        driver.close();
    }

    @AfterSuite
    public void endReporter(){
        extent.flush();
        extent.close();
    }


}
