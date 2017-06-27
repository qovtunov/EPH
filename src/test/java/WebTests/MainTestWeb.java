package WebTests;

import Data.ConfigProperties;
import Driver.MainMethods;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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

        FirefoxOptions options = new FirefoxOptions();

        if (operationSystem.contains("win")){
            System.setProperty("webdriver.gecko.driver", String.valueOf(driverFirefoxWin));
            options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        }else if (operationSystem.contains("nux") || operationSystem.contains("nix")) {
            System.setProperty("webdriver.gecko.driver", String.valueOf(driverFirefoxLinux));
        }else if (operationSystem.contains("mac")) {
            System.setProperty("webdriver.gecko.driver", String.valueOf(driverFirefoxMac));
            options.setBinary("PLEASE INSERT FF LAUNCHER HERE");
        }

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("moz:firefoxOptions", options);

        driver = new FirefoxDriver(capabilities);

        driver.navigate().to(ConfigProperties.getProperty("baseUrl"));
    }

    @BeforeSuite(groups = "InternetExplorer")
    public void  setUpDriverIE() throws InterruptedException {
        logger.info("[TEST STARTED]");
        logger.info("OS: "+ operationSystem);
        System.setProperty("webdriver.ie.driver", String.valueOf(driverIEWin));

        driver = new InternetExplorerDriver();

        driver.navigate().to(ConfigProperties.getProperty("baseUrl"));
    }

    @BeforeTest(groups = {"Chrome", "Firefox", "InternetExplorer"})
    @Parameters({"x","y"})
    public void setBrowserSize(@Optional("1024") int x, @Optional("768") int y){
        logger.info("DIMENSION IS: " + x + "x" + y + "px" + "\n");
        setSize(x,y);
    }

    @AfterSuite(groups = {"Chrome", "Firefox", "InternetExplorer"})
    public void tearDown() throws InterruptedException {
        driver.quit();
        logger.info("[TEST FINISHED]" + "\n");
    }

}


