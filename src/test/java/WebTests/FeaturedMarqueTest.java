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
    @Parameters({"x","y","browser"})
    public void componentExists(int x, int y, String browser) throws IOException, InterruptedException {
        featuredMarque = new FeaturedMarque(driver);
        Assert.assertTrue(isElementVisible(featuredMarque.banner));  //component exists or not
        scrollIntoView(featuredMarque.banner);
        Thread.sleep(3000);
        getScreenshot(featuredMarque.banner, "/Screenshots/" + browser + "/" + x + "x" + y + ".png");
        compareScreenshots(featuredMarque.banner, "/Screenshots/" + browser + "/" + x + "x" + y + ".png", "/Screenshots/" + browser + "/" + x + "x" + y + ".png");
    }

    @Test
    public void playButtonOpacity() {
        waitForElement(featuredMarque.playButton);
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
}
