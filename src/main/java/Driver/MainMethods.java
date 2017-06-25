package Driver;

import Data.UserData;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void setSize(int x, int y) {
        Dimension d = new Dimension(x, y);
        driver.manage().window().setSize(d);
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
