package WebTests;

import Data.ConfigProperties;
import Driver.MainMethods;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class MainTestWeb extends MainMethods {

    String operationSystem = System.getProperty("os.name").toLowerCase();


    File dir = new File("src");
    File driverChromeWin = new File(dir, "chromedriver.exe");
    File driverChromeLinux = new File(dir, "chromedriverLinux64");
    File driverChromeMac = new File(dir, "chromedriverMac");

    File driverFirefoxWin = new File(dir, "geckodriver.exe");
    File driverFirefoxLinux = new File(dir, "geckodriverLinux64");
    File driverFirefoxMac = new File(dir, "geckodriverMac");

    File driverIEWin = new File(dir, "IEDriverServer.exe");

    @BeforeSuite(groups = "Chrome")
    public void setUpDriverChrome() throws IOException {
        logger.info("[TEST STARTED]");
        logger.info("OS: "+ operationSystem);
        if (operationSystem.contains("win")){
            System.setProperty("webdriver.chrome.driver", String.valueOf(driverChromeWin));
        }else if (operationSystem.contains("nux") || operationSystem.contains("nix")) {
            System.setProperty("webdriver.chrome.driver", String.valueOf(driverChromeLinux));
        }else if (operationSystem.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", String.valueOf(driverChromeMac));
        }

        driver = new ChromeDriver();

        driver.navigate().to(ConfigProperties.getProperty("baseUrl"));
    }

    @BeforeSuite(groups = "Firefox")
    public void setUpDriverFirefox() throws IOException {
        logger.info("[TEST STARTED]");
        logger.info("OS: "+ operationSystem);
        if (operationSystem.contains("win")){
            System.setProperty("webdriver.gecko.driver", String.valueOf(driverFirefoxWin));
        }else if (operationSystem.contains("nux") || operationSystem.contains("nix")) {
            System.setProperty("webdriver.gecko.driver", String.valueOf(driverFirefoxLinux));
        }else if (operationSystem.contains("mac")) {
            System.setProperty("webdriver.gecko.driver", String.valueOf(driverFirefoxMac));
        }
        FirefoxOptions options = new FirefoxOptions().setLegacy(true).setLogLevel(Level.OFF);

        driver = new FirefoxDriver(options);

        driver.navigate().to(ConfigProperties.getProperty("baseUrl"));
    }

    @BeforeTest(groups = "InternetExplorer")

    public void  setUpDriverIE(String baseUrl) throws InterruptedException {
        logger.info("[TEST STARTED]");
        logger.info("OS: "+ operationSystem);
        System.setProperty("webdriver.ie.driver", String.valueOf(driverIEWin));

        driver = new InternetExplorerDriver();

        driver.navigate().to(ConfigProperties.getProperty("baseUrl"));
    }

    @BeforeTest(groups = {"Chrome", "Firefox", "InternetExplorer"})
    @Parameters({"x","y"})
    public void setSize(@Optional("1024") int x, @Optional("768") int y){
        Dimension d = new Dimension(x, y);
        logger.info("DIMENSION IS: " + x + "x" + y + "px" + "\n");
        driver.manage().window().setSize(d);
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        driver.quit();
        logger.info("[TEST FINISHED]" + "\n");
    }

}


