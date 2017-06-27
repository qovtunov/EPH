package WebTests;

import Components.FeaturedMarque;
import Data.ConfigProperties;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class FeaturedMarqueTest extends MainTestWeb {

    public FeaturedMarque featuredMarque;

    @Test
    @Parameters({"x","y"})
    public void componentExists(int x, int y) throws IOException, InterruptedException {
        featuredMarque = new FeaturedMarque(driver);
        Assert.assertTrue(isElementVisible(featuredMarque.banner));  //component exists or not
        scrollIntoView(featuredMarque.banner);
        Thread.sleep(3000);
        //getScreenshot(featuredMarque.banner, "/Screenshots/ElementScreenshot" + x + "x" + y + ".png");
        compareScreenshots(featuredMarque.banner, "/Screenshots/ElementScreenshot" + x + "x" + y + ".png", "/Screenshots/ElementScreenshotActual" + x + "x" + y + ".png");
    }

    @Test
    public void playButtonOpacity() {
        hoverOverElement(featuredMarque.playButton);
        Assert.assertEquals(featuredMarque.playButton.getCssValue("opacity"), "0.6"); // opacity check
    }

    @Test
    public void videoContainer() {
        clickElement(featuredMarque.playButton);
        Assert.assertTrue(isElementVisible(featuredMarque.videoContainer)); // open player
    }

    @Test
    public void videoContainerClose() {
        clickElement(featuredMarque.videoContainerCloseButton);
        Assert.assertTrue(isElementNotVisible(featuredMarque.videoContainer)); // close player
    }

    @Test
    public void downButton() {
        clickElement(featuredMarque.downButton);
        Assert.assertEquals(featuredMarque.downButton.getAttribute("href"), ConfigProperties.getProperty("baseUrl") + '#'); //down arrow check

    }


   /* @Test
    public void Large1200_1599px() {
        featuredMarque = new FeaturedMarque(driver);

        setSize(1200, 768);
        Assert.assertTrue(isElementVisible(featuredMarque.banner)); //component exists or not

        // screenshot assert here

        hoverOverElement(featuredMarque.playButton);
        Assert.assertEquals(featuredMarque.playButton.getCssValue("opacity"), "0.6"); // opacity check

        clickElement(featuredMarque.playButton);
        Assert.assertTrue(isElementVisible(featuredMarque.videoContainer)); // open player

        clickElement(featuredMarque.videoContainerCloseButton);
        Assert.assertTrue(isElementNotVisible(featuredMarque.videoContainer)); // close player

        clickElement(featuredMarque.downButton);
        Assert.assertEquals(featuredMarque.downButton.getAttribute("href"), ConfigProperties.getProperty("baseUrl")+'#'); //down arrow check


        //NEXT BREAKPOINT:

        *//*setSize(992, 768); //set size
        Assert.assertTrue(isElementVisible(featuredMarque.banner)); //checking that banner appeared

        // screenshot assert here

        hoverOverElement(featuredMarque.playButton);
        Assert.assertEquals(featuredMarque.playButton.getCssValue("opacity"), "0.6"); // opacity check

        clickElement(featuredMarque.playButton);
        Assert.assertTrue(isElementVisible(featuredMarque.videoContainer)); // open player

        clickElement(featuredMarque.videoContainerCloseButton);
        Assert.assertTrue(isElementNotVisible(featuredMarque.videoContainer)); // close player

        clickElement(featuredMarque.downButton);
        Assert.assertEquals(featuredMarque.downButton.getAttribute("href"), ConfigProperties.getProperty("baseUrl")+'#'); //down arrow check*//*

    }

    @Test
    public void Medium992_1199px() {
        featuredMarque = new FeaturedMarque(driver);

        setSize(992, 768); //set size
        Assert.assertTrue(isElementVisible(featuredMarque.banner)); //component exists or not

        // screenshot assert here

        hoverOverElement(featuredMarque.playButton);
        Assert.assertEquals(featuredMarque.playButton.getCssValue("opacity"), "0.6"); // opacity check

        clickElement(featuredMarque.playButton);
        Assert.assertTrue(isElementVisible(featuredMarque.videoContainer)); // open player

        clickElement(featuredMarque.videoContainerCloseButton);
        Assert.assertTrue(isElementNotVisible(featuredMarque.videoContainer)); // close player

        clickElement(featuredMarque.downButton);
        Assert.assertEquals(featuredMarque.downButton.getAttribute("href"), ConfigProperties.getProperty("baseUrl")+'#'); //down arrow check

    }*/

    /*@Test
    public void getScreenshot() throws IOException, InterruptedException {

        //Thread.sleep(10000);
        WebElement webElement = driver.findElement(By.cssSelector("#content > div > div > div.featuredMarqueeComponent.parbase.section"));

        Screenshot screenshot = new AShot().takeScreenshot(driver, webElement);
        ImageIO.write(screenshot.getImage(), "PNG", new File(System.getProperty("user.dir") + "/Screenshots/ElementScreenshot.png"));
        //Thread.sleep(2000);
    }*/

   /* @Test
    public void compareScreenshots() throws IOException, InterruptedException {

        WebElement logoImage = driver.findElement(By.cssSelector("#content > div > div > div.featuredMarqueeComponent.parbase.section"));


        BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") + "/Screenshots/ElementScreenshot.png"));
        Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImage);
        ImageIO.write(logoImageScreenshot.getImage(), "PNG", new File(System.getProperty("user.dir") + "/Screenshots/ElementScreenshotActual.png"));


        BufferedImage actualImage = logoImageScreenshot.getImage();


        Thread.sleep(3000);

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        Assert.assertFalse(diff.hasDiff(), "Images are Same");
        logger.info("NO DIFF");

    }*/


}
