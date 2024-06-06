package lippia.web.listeners.validator;

import com.crowdar.driver.DriverManager;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.crowdar.core.actions.ActionManager.getElement;

final public class AdValidator implements DriverEventListenerValidation {

    private static String CLOSE_AD_BUTTON = "xpath://*[@id='dismiss-button']";
    public static final String AD_IFRAME1 = "xpath:/html/ins/*/*[contains(@id, 'aswift_') and not(contains(@id, 'host'))]";
    public static final String AD_IFRAME2 = "xpath://*[@id='ad_iframe']";

    @Override
    public void validate(Throwable throwable, WebDriver driver) {
        if (!AdVisible())
            return;
        closeAd();
    }

    private boolean AdVisible() {
        return DriverManager.getDriverInstance().getCurrentUrl().contains("#google_vignette");
    }

    private static void closeAd() {
        WebDriver driver = DriverManager.getDriverInstance();

        try {
            closeIFrame(driver, getElement(AD_IFRAME1));
        } catch (NoSuchFrameException e) {
            System.out.println("AD_IFRAME1 no encontrado");
        }

        try {
            closeIFrame(driver, getElement(AD_IFRAME2));
        } catch (NoSuchFrameException e) {
            System.out.println("AD_IFRAME2 no encontrado");
        }
        driver.switchTo().defaultContent();
    }

    private static boolean closeIFrame(WebDriver driver, WebElement iframe) {
        try {
            if (!iframe.isDisplayed())
                return false;
            driver.switchTo().frame(iframe);
            WebElement closeButton = getElement(CLOSE_AD_BUTTON);

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(closeButton));
            closeButton.click();
        } catch (StaleElementReferenceException e) {
            return false;
        } finally {
            driver.switchTo().defaultContent();
        }
        return true;
    }
}