package parallelRun;

import org.testng.annotations.Test;

public class TestingClass2_parallel extends TestBase_parallel {

    @Test(description = "Testing CNN website")
    public void SecondTest(){
        getDriver().get("https://cnn.com");
        logger.info("Tittle of the page: " + getDriver().getTitle());
    }

    @Test(description = "YouTube website testing")
    public void ThirdTest(){
        getDriver().get("https://youtube.com");
        logger.info("Tittle of the page: " + getDriver().getTitle());
    }
}
