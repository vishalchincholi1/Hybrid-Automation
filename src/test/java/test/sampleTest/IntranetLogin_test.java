package test.sampleTest;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pom.sampleTest.IntranetLogin;
import utils.baseutils.BrowserManager;
import utils.javautils.LoggerUtil;
import utils.javautils.ReadExcelData;
import utils.javautils.Reporter;
import utils.javautils.TakeScreenshot;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public class IntranetLogin_test {
    Logger logger = LoggerUtil.getLogger();

    BrowserManager bm = new BrowserManager();

    TakeScreenshot tss = new TakeScreenshot();


    static {
        ReadExcelData.getInstance().setPath(System.getProperty("user.dir") + "/src/test/java/test/resources/Test_data/");

    }

    @BeforeSuite
    public void generateReport() {
        logger.info("Generating Test Report");
        Reporter.setupReport("Test_Report");
    }

    @BeforeMethod
    public void setupOpen() throws Exception {
        logger.info("Setting up the browser...");
        bm.browserRun();

    }

    @AfterMethod
    public void setupClose(ITestResult result) throws Exception {
        if (result.FAILURE == result.getStatus()) {
            logger.warning("Test LM_L_LIP failed. Capturing Screenshot...");
            tss.screenshot(bm.driver, "Intranet login test case");
        }
        Reporter.reportonTestResult(result, bm.driver);

      bm.driver.close();
    }

    @AfterClass
    public void reportFlush() {

        Reporter.flushReport();
    }

    @DataProvider(name = "Intranet_Login")
    public Object[][] creator_tcdata() throws Exception {
        logger.info("Fetching data from the data provider 'EG_login'");
        Object[][] testData = ReadExcelData.getExcelDataIn2DArray("sampleTest.xlsx", "login");
        return testData;
    }

    @Test(dataProvider ="Intranet_Login" )
    public void tc_01_Login(String username, String pass,Method testMethodName) {
        LoggerUtil.startTimeMeasurement();
        logger.info("Executing tc_01_Login Test Cases");
        Reporter.createTest(testMethodName.getName());
         IntranetLogin intranetLogin = PageFactory.initElements(bm.driver, IntranetLogin.class);
        Reporter.logStep(Status.INFO, "Steps performed according to tc_01_Login Test Cases");
        intranetLogin.tc_01_Login(username,pass);
        LoggerUtil.stopTimeMeasurement("tc_01_Login Test Cases Execution Completed");
    }

}
