package WebTests;

import Data.ConfigProperties;
import Components.FeaturedMarque;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FeaturedMarqueTest extends MainTestWeb{

    public FeaturedMarque featuredMarque;

    @Test
    public void Large1200_1599px() {  //please rename the test
        featuredMarque = new FeaturedMarque(driver);

        setSize(1200, 768); //set size
        Assert.assertTrue(isElementVisible(featuredMarque.banner)); //checking that banner appeared

        //please insert here screenshot test

        hoverOverElement(featuredMarque.playButton);
        //need an assert

        clickElement(featuredMarque.playButton);
        Assert.assertTrue(isElementVisible(featuredMarque.videoContainer)); // video test
        clickElement(featuredMarque.videoContainerCloseButton);

        clickElement(featuredMarque.downButton);
        Assert.assertEquals(featuredMarque.downButton.getAttribute("href"), ConfigProperties.getProperty("baseUrl")+'#'); //down arrow test


        //NEXT BREAKPOINT:

        /*setSize(992, 768); //set size
        Assert.assertTrue(isElementVisible(featuredMarque.banner)); //checking that banner appeared

        //please insert here screenshot test

        hoverOverElement(featuredMarque.playButton);
        //need an assert

        clickElement(featuredMarque.playButton);
        Assert.assertTrue(isElementVisible(featuredMarque.videoContainer)); // video test
        clickElement(featuredMarque.videoContainerCloseButton);

        clickElement(featuredMarque.downButton);
        Assert.assertEquals(featuredMarque.downButton.getAttribute("href"), ConfigProperties.getProperty("baseUrl")+'#'); //down arrow test*/

    }

    @Test
    public void Medium992_1199px() {  //please rename the test
        featuredMarque = new FeaturedMarque(driver);

        setSize(992, 768); //set size
        Assert.assertTrue(isElementVisible(featuredMarque.banner)); //checking that banner appeared

        //please insert here screenshot test

        hoverOverElement(featuredMarque.playButton);
        //need an assert

        clickElement(featuredMarque.playButton);
        Assert.assertTrue(isElementVisible(featuredMarque.videoContainer)); // video test
        clickElement(featuredMarque.videoContainerCloseButton);

        clickElement(featuredMarque.downButton);
        Assert.assertEquals(featuredMarque.downButton.getAttribute("href"), ConfigProperties.getProperty("baseUrl")+'#'); //down arrow test

    }

    @Test
    public void getScreenshot() throws IOException, InterruptedException {

        //Thread.sleep(10000);
        WebElement webElement = driver.findElement(By.cssSelector("#content > div > div > div.featuredMarqueeComponent.parbase.section"));

        Screenshot screenshot = new AShot().takeScreenshot(driver, webElement);
        ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") +"/Screenshots/ElementScreenshot.png"));
        //Thread.sleep(2000);
    }

    @Test
    public void compareScreenshots() throws IOException {

        WebElement logoImage = driver.findElement(By.cssSelector("#content > div > div > div.featuredMarqueeComponent.parbase.section"));

        BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") +"/Screenshots/ElementScreenshot.png"));
        Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImage);
        BufferedImage actualImage = logoImageScreenshot.getImage();

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        Assert.assertFalse(diff.hasDiff(),"Images are Same");
        logger.info("NO DIFF");

    }



}
