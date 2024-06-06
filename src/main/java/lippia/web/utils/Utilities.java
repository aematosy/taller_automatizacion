package lippia.web.utils;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.listeners.validator.AdValidator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities extends WebActionManager{

	static WebDriver driver = DriverManager.getDriverInstance();

	/*
	 * Método constructor que inicializa el driver
	 */
	public Utilities(WebDriver driver) {
		Utilities.driver = driver;
	}

	public static void cerrarPopUp() {
		WebDriver driver = DriverManager.getDriverInstance();

		try {
			if (driver.getCurrentUrl().contains("#google_vignette")) {
				WebDriverWait wait = new WebDriverWait(driver, 5);
				WebElement IframeExterno = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/ins/*/*[contains(@id, 'aswift_') and not(contains(@id, 'host'))]")));
				driver.switchTo().frame(IframeExterno);
				WebElement IframeInterno = driver.findElement(By.id("ad_iframe"));
				driver.switchTo().frame(IframeInterno);
				try {
					WebElement cerrarPublicidad = driver.findElement(By.id("dismiss-button"));
					cerrarPublicidad.click();
				} catch (NoSuchElementException e) {
					System.out.println("El botón 'dismiss-button' no está presente: " + e.getMessage());
				}
				driver.switchTo().defaultContent();
				AdValidator adValidator = new AdValidator();
				adValidator.validate(null, driver);
			} else {
				System.out.println("La URL actual no contiene la palabra '#google_vignette'.");
			}
		} catch (TimeoutException e) {
			System.out.println("El iframe no está presente: " + e.getMessage());
		} catch (Exception ex) {
			System.out.println("" + ex.getMessage());
		}
		sleep(1000);
	}

	public static void scrollTo2(String xpath) {
		JavascriptExecutor javascriptExecutor = DriverManager.getDriverInstance();
		WebElement element = WebActionManager.getElement(xpath);
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void sleep (long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException var4) {
			System.out.println("Error in sleep: ".concat(var4.getMessage()));
			var4.printStackTrace();
		}
	}

	public static void verifyElementPresence(String locator) {
		Assert.assertTrue(WebActionManager.isPresent(locator));
	}

	public static boolean isElementPresent(By by) {
		try {
			DriverManager.getDriverInstance().findElement(by);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public static void verifyCurrentUrl(String expectedUrl) {
		String currentUrl = DriverManager.getDriverInstance().getCurrentUrl();
		Assert.assertEquals(currentUrl, expectedUrl);
	}

	public static void clickear(String locatorElement, String... locatorReplacementValue) {
		click(locatorElement, false, locatorReplacementValue);
	}

	public static void doClick(String locatorElement, String... locatorReplacementValue) {
		WebElement element;
		if (locatorElement.startsWith("id:")) {
			String idValue = locatorElement.substring(3);
			element = DriverManager.getDriverInstance().findElement(By.id(idValue));
		} else if (locatorElement.startsWith("xpath:")) {
			String xpathValue = locatorElement.substring(6);
			element = DriverManager.getDriverInstance().findElement(By.xpath(xpathValue));
		} else {
			throw new IllegalArgumentException("Locator type not supported.");
		}

		if (DriverManager.getDriverInstance() instanceof JavascriptExecutor) {
			((JavascriptExecutor) DriverManager.getDriverInstance()).executeScript(
					"arguments[0].scrollIntoView(true);", element);
		}

		if (DriverManager.getDriverInstance() instanceof JavascriptExecutor) {
			((JavascriptExecutor) DriverManager.getDriverInstance()).executeScript(
					"arguments[0].style.border='3px solid red'", element);
		}
		click(locatorElement, false, locatorReplacementValue);
	}

	public static void clickByVariableXPath(String xpathTemplate, String dynamicText) {
		String finalXPath = String.format(xpathTemplate, dynamicText);
		WebElement element = DriverManager.getDriverInstance().findElement(By.xpath(finalXPath));
		element.click();
	}

	public static void scrollToAndClick(String locatorWithPrefix) {
		String locator = extractLocatorFromPrefixed(locatorWithPrefix);
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriverInstance(), 5);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

		((JavascriptExecutor) DriverManager.getDriverInstance()).executeScript(
				"window.scrollTo(0, arguments[0]); arguments[1].click();", element.getLocation().getY() - 50, element);
	}

	private static String extractLocatorFromPrefixed(String locatorWithPrefix) {
		if (locatorWithPrefix.startsWith("xpath:")) {
			return locatorWithPrefix.substring(6);
		} else {
			throw new IllegalArgumentException("Locator type not supported.");
		}
	}


	public static void clickWithJS(WebElement element) {
		Wait<EventFiringWebDriver> wait = new FluentWait<>(DriverManager.getDriverInstance())
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(org.openqa.selenium.NoSuchElementException.class);

		wait.until(driver -> {
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].style.border='3px solid red'", element);
			}

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			return true;
		});
	}

	public static void scrollTo(String locator) {
		By by = getByFromLocator(locator);
		WebElement element = DriverManager.getDriverInstance().findElement(by);
		((JavascriptExecutor) DriverManager.getDriverInstance()).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	private static By getByFromLocator(String locator) {
		String[] parts = locator.split(":", 2);
		String type = parts[0].toLowerCase();
		String value = parts[1].trim();

		switch (type) {
			case "id":
				return By.id(value);
			case "name":
				return By.name(value);
			case "xpath":
				return By.xpath(value);
			default:
				throw new IllegalArgumentException("Locator type not supported: " + type);
		}
	}

	public static double extraerValorNumerico(String precio) {
		Pattern pattern = Pattern.compile("[^0-9.]");
		Matcher matcher = pattern.matcher(precio);
		String numericValue = matcher.replaceAll("");
		return Double.parseDouble(numericValue);
	}

	public static WebElement waitByVariableXPath(String xpathVariable, String dynamicText, int time) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriverInstance(), time);
		String dynamicXPath = String.format(xpathVariable, dynamicText);
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dynamicXPath)));
	}

	public static void scrollToAndClickJS(String xpathTemplate, String dynamicText) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriverInstance(), 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(xpathTemplate, dynamicText))));
		((JavascriptExecutor) DriverManager.getDriverInstance()).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", element);
		wait.until(ExpectedConditions.visibilityOf(element));

		if (DriverManager.getDriverInstance() instanceof JavascriptExecutor) {
			((JavascriptExecutor) DriverManager.getDriverInstance()).executeScript(
					"arguments[0].style.border='3px solid red'", element);
			((JavascriptExecutor) DriverManager.getDriverInstance()).executeScript("arguments[0].click();", element);
		}
	}

	public static WebElement waitForElementWithXPath(String xpath) {
		FluentWait<EventFiringWebDriver> fluentWait = new FluentWait<>(DriverManager.getDriverInstance())
				.withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

		return fluentWait.until(driver ->
				driver.findElement(By.xpath(xpath)));
	}

	public static WebElement waitElementVisible(String xpath) {
		FluentWait<EventFiringWebDriver> wait = new FluentWait<>(DriverManager.getDriverInstance())
				.withTimeout(Duration.ofSeconds(25))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.ignoring(UnknownError.class)
				.ignoring(WebDriverException.class);

		try {
			return wait.until(driver -> {
				WebElement element = driver.findElement(By.xpath(xpath));
				return ExpectedConditions.visibilityOf(element).apply(driver);
			});
		} catch (Exception e) {
			System.out.println("Element not found: " + e.getMessage());
			return null;
		}
	}
}
