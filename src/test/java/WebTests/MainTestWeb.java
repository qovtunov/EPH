package WebTests;

import Data.ConfigProperties;
import Driver.MainMethods;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class MainTestWeb extends MainMethods {

    String operationSystem = System.getProperty("os.name").toLowerCase();

    File dir = new File("src");
    File driverChromeWin = new File(dir, "chromedriver.exe");
    File driverChromeLinux = new File(dir, "chromedriverLinux32");
    File driverChromeMac = new File(dir, "chromedriverMac");

    @BeforeSuite
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

    @BeforeTest //try to ignore it in report
    @Parameters({"x","y"})
    public void setSize(int x, int y){
        setSize(x, y);
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        driver.quit();
        logger.info("[TEST FINISHED]" + "\n");
    }

}


