package Components;

import Driver.MainMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class FeaturedMarque extends MainMethods {
   @FindBy(css = ".featuredMarqueeComponent.parbase.section")
    public WebElement banner;

    @FindBy(css = ".btn-play.video-overlay>img")
    public WebElement playButton;

    @FindBy(css = ".down-arrow>a")
    public WebElement downButton;

    @FindBy(id = "video-container")
    public WebElement videoContainer;

    @FindBy (css = ".icon-link.close-video.close-video-btn.icon-nav-close-menu.close-btn")
    public WebElement videoContainerCloseButton;

    public FeaturedMarque(WebDriver driver) {
            PageFactory.initElements(driver, this);
    }
}
