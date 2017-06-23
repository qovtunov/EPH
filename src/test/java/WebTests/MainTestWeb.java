package WebTests;

import Data.ConfigProperties;
import Driver.MainMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;

public class MainTestWeb extends MainMethods {

    String operationSystem = System.getProperty("os.name").toLowerCase();

    File dir = new File("src");
    File driverChromeWin = new File(dir, "chromedriver.exe");
    File driverChromeLinux = new File(dir, "chromedriverLinux64");
    File driverChromeMac = new File(dir, "chromedriverMac");

    @BeforeTest
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

    @AfterTest
    public void tearDown() throws InterruptedException {
        driver.quit();
        logger.info("[TEST FINISHED]" + "\n");
    }

}


