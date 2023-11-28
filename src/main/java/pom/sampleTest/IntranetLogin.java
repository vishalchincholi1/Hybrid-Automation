package pom.sampleTest;

import com.aventstack.extentreports.Status;
import lombok.extern.flogger.Flogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.baseutils.BrowserManager;
import utils.javautils.LoggerUtil;
import utils.javautils.PropertiesFileManager;
import utils.javautils.Reporter;

import java.util.Properties;
import java.util.logging.Logger;

public class IntranetLogin {
    BrowserManager bm=new BrowserManager();


    static String locatorsPath=System.getProperty("user.dir")+"/src/test/java/test/resources/locators/";


    public IntranetLogin(WebDriver driver)
    {
        bm.driver=driver;
    }
    //..............load properties file

    static {
        PropertiesFileManager.getInstance().setPath(locatorsPath);
    }

    private static final Logger LOGGER = LoggerUtil.getLogger();
    PropertiesFileManager loader = PropertiesFileManager.getInstance();

    Properties loginLocators = loader.getProperties("login.properties");


    //............Pom elements fetched from properties file


//    By signInWithGoogle=By.className(loginLocators.getProperty("sc_gologin"));
    By userName=By.id(loginLocators.getProperty("userName"));
    By password=By.id(loginLocators.getProperty("pass"));
    By logIn=By.xpath(loginLocators.getProperty("logIn"));

    //..........Actions taken on the webElements

    public void userName(String uid) {
        LOGGER.info("Entering User ID");
        Reporter.logStep(Status.INFO, "Entering User ID");
        bm.driver.findElement(userName).sendKeys(uid);
    }
    public void password(String pass){
        LOGGER.info("Entering password");
        Reporter.logStep(Status.INFO, "clicking on next button");
        bm.driver.findElement(password).sendKeys(pass);
    }
        public void logIn() {
        LOGGER.info("Clicking on the log in button");
        Reporter.logStep(Status.INFO, "Clicking on the log in button");
        bm.driver.findElement(logIn).click();
    }

    //-----Test cases----
    public void tc_01_Login(String un, String pass){
        try{
//           signIn();
            userName(un);
            password(pass);
            logIn();

        }catch(RuntimeException re){
            LOGGER.warning("Element not found");
        }
    }
    }
