package Driver;

import Data.UserData;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class MainMethods extends UserData {

    public static WebDriver driver;
    public WebDriverWait wait;

    public static final Logger logger = Logger.getLogger(MainMethods.class.getName());

    public void scrollIntoView(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void setSize(int x, int y) {
        driver.manage().window().setSize(new Dimension(x, y));
    }

    public void getScreenshot(WebElement element, String screenshotName) throws IOException, InterruptedException {
        Screenshot screenshot = new AShot().takeScreenshot(driver, element);
        ImageIO.write(screenshot.getImage(), "PNG", new File(System.getProperty("user.dir") + screenshotName));
    }

    public void compareScreenshots(WebElement element, String screenshotExpected, String screenshotActual) throws IOException, InterruptedException {

        BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") + screenshotExpected));
        Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, element);
        ImageIO.write(logoImageScreenshot.getImage(), "PNG", new File(System.getProperty("user.dir") + screenshotExpected));

        BufferedImage actualImage = logoImageScreenshot.getImage();

        Thread.sleep(3000);

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        Assert.assertFalse(diff.hasDiff(), "Images are Same");
        logger.info("NO DIFF");
    }


    public void waitForElement(WebElement element) {
        wait = new WebDriverWait(driver, 90);
        //logger.info("Waiting for visibility of element... 30 sec");
        wait.until(ExpectedConditions.visibilityOf(element));
        //logger.info("Element is found.");
    }

    public void waitForElementStaleness(WebElement element){
        wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public boolean isElementVisible(WebElement element) {
        try {
            waitForElement(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isElementNotVisible(WebElement element) {

        try {
            wait = new WebDriverWait(driver, 2);
            wait.until(stalenessOf(element));
        } catch (NoSuchElementException e) {
            return true;
        } catch (TimeoutException e) {
            return true;
        } catch (Exception e){
            return false;
        }
        return false;
    }


    public boolean isRedirectTo(String url) {
        wait = new WebDriverWait(driver, 90);
        try {
            wait.until(ExpectedConditions.urlContains(url));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void clickElement(WebElement button) {
        waitForElement(button);
        button.click();
    }

    public void inputDataField(WebElement element, String text) {
        waitForElement(element);
        element.clear();
        element.sendKeys(text);
    }


    public void hoverOverElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void dragToClick(WebElement drag, WebElement click) {
        Actions builder = new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(drag)
                .moveToElement(click)
                .release(click)
                .build();
        dragAndDrop.perform();
        click.click();
    }

    public static int getResponseCode(String urlString) throws MalformedURLException, IOException{
        URL url = new URL(urlString);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        return huc.getResponseCode();
    }
}
