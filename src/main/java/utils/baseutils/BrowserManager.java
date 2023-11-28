package utils.baseutils;
import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;




public class BrowserManager {
    public  WebDriver driver;


    //	@SuppressWarnings("deprecation")
    public  void browserRun() throws Exception {


        FileReader fr= new FileReader(System.getProperty("user.dir")+"/src/test/java/test/resources/Test_data/browserConfig.properties");

        Properties prop = new Properties();

        prop.load(fr);


        if(prop.getProperty("Browser").equalsIgnoreCase("chrome")) {

            ChromeOptions opt=new ChromeOptions();
//			opt.setHeadless(true);
            opt.addArguments("--remote-allow-origins=*");
            opt.addArguments("--incognito");
            opt.setBrowserVersion("116");

            driver = new ChromeDriver(opt);

        }

        else if(prop.getProperty("Browser").equalsIgnoreCase("firefox")) {

            FirefoxOptions opt=new FirefoxOptions();

            opt.setBinary("/usr/bin/firefox");

            driver = new FirefoxDriver(opt);


        }
        else if(prop.getProperty("Browser").equalsIgnoreCase("edge")) {

            EdgeOptions opt=new EdgeOptions();

            opt.addArguments("--remote-allow-origins=*");
            driver= new EdgeDriver();

        }
        else {

            System.out.println("Invalid Browser Selection");

        }


        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));

    }

    public void openNewWindow(String url) {
        // driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
        ((JavascriptExecutor) driver).executeScript("window.open()");

        // Switch to the new tab
        String originalHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        driver.get(url);
    }

}

