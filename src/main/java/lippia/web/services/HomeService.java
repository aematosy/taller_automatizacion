package lippia.web.services;

import com.crowdar.core.PropertyManager;
import com.crowdar.core.actions.ActionManager;
import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.HomeConstants;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.List;

import static com.crowdar.core.actions.WebActionManager.navigateTo;
import static lippia.web.utils.Utilities.*;

public class HomeService extends ActionManager {

    static String newArrivalValue;
    static String precioProductoValue;
    static String paymentMethodValue;

    public static void navegarWeb() {
        navigateTo(PropertyManager.getProperty("web.base.url"));
    }

    public static void clickIniciarSesionMenu() {
        WebActionManager.waitClickable(HomeConstants.LOGIN_OPTION);
        WebActionManager.click(HomeConstants.LOGIN_OPTION);
    }

    public static void clickStarWithEmailOption() {
        WebActionManager.waitClickable(HomeConstants.EMAIL_OPTION);
        WebActionManager.click(HomeConstants.EMAIL_OPTION);
    }

    public static void fillEmailPassword(String email, String password) {
        WebActionManager.waitVisibility(HomeConstants.EMAIL_INPUT);
        WebActionManager.setInput(HomeConstants.EMAIL_INPUT, email);
        WebActionManager.setInput(HomeConstants.PASSWORD_INPUT, password);
        WebActionManager.click(HomeConstants.LOGIN_BUTTON);
    }

    public static void verifyLogoutElements() {
        WebActionManager.waitVisibility(HomeConstants.LOGOUT_BUTTON);
    }

    public static void validateFailedLogin(String message) {
        String failedMessage = WebActionManager.getText(HomeConstants.FAILED_LOGIN_MESSAGE);
        Assert.assertEquals(message, failedMessage);
    }



    public static void testHomeSliders(String numberOfArrivals) {
        List<WebElement> sliders = WebActionManager.getElements(HomeConstants.HOME_SLIDER_LOCATOR);
        String cantSlider = "" + sliders.size();
        Assert.assertEquals(cantSlider, numberOfArrivals);
    }

    public static void clickImageArrival(String newArrival) {
        newArrivalValue = newArrival;
        scrollToAndClick(HomeConstants.NEW_ARRIVAL_TITLE_LOCATOR);
        clickByVariableXPath(HomeConstants.IMAGE_ARRIVAL, newArrival);
    }

    public static void verifyPage() {
        cerrarPopUp();
        String xpath = "//h1[contains(text(),'" + newArrivalValue + "')]";
        WebElement h1Element = waitElementVisible(xpath);
        try {
            Assert.assertNotNull(h1Element, "El elemento no se encontró.");
            Assert.assertTrue(h1Element.isDisplayed(), "El elemento no está visible.");
        } catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
            System.out.println("Excepción durante la espera o validación: " + e.getMessage());
            Assert.fail("La página no contiene el elemento con el texto esperado: " + newArrivalValue);
        } catch (Exception e) {
            System.out.println("Excepción general: " + e.getMessage());
            Assert.fail("La página no contiene el elemento con el texto esperado: " + newArrivalValue);
        }
    }

    public static void verifyArrivalElements() {
        cerrarPopUp();
        Assert.assertTrue(ActionManager.waitPresence(HomeConstants.ADD_BASKET_BUTTON_LOCATOR).isDisplayed());
    }

    public static void addArrivalElementBasket() {
        try {
            List<WebElement> precioElements = WebActionManager.getElements(HomeConstants.PRICE_LABEL_LOCATOR);
            WebElement precioHabilitado = (precioElements.size() > 1) ? precioElements.get(1) : (precioElements.size() == 1) ? precioElements.get(0) : null;

            if (precioHabilitado != null) {
                precioProductoValue = precioHabilitado.getText();
            } else {
                System.out.println("No se encontraron elementos de precio para el producto seleccionado.");
            }
            WebElement addButton = WebActionManager.getElement(HomeConstants.ADD_BASKET_BUTTON_LOCATOR);
            clickWithJS(addButton);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validateAmountBasket() {
        WebActionManager.waitVisibility(HomeConstants.AMOUNT_LABEL_LOCATOR);
        String amountProducto = WebActionManager.getText(HomeConstants.AMOUNT_LABEL_LOCATOR);
        Assert.assertEquals(amountProducto, precioProductoValue);
    }

    public static void clickItemLink() {
        WebActionManager.waitClickable(HomeConstants.AMOUNT_LABEL_LOCATOR);
        WebActionManager.click(HomeConstants.AMOUNT_LABEL_LOCATOR);
        cerrarPopUp();
    }

    public static void verifyElemntsCheckout() {
        WebActionManager.waitPresence(HomeConstants.SUBTOTAL_LABEL_LOCATOR);
        WebActionManager.waitPresence(HomeConstants.TOTAL_LABEL_LOCATOR);
    }

    public static void validateTotalAmount() {
        String precioSubTotal = WebActionManager.getText(HomeConstants.SUBTOTAL_LABEL_LOCATOR);
        String precioTotal = WebActionManager.getText(HomeConstants.TOTAL_LABEL_LOCATOR);

        double subtotal = extraerValorNumerico(precioSubTotal);
        double total = extraerValorNumerico(precioTotal);

        Assert.assertTrue(total > subtotal, "El valor del precio total no es mayor que el precio subtotal");
    }

    public static void clickOpcCheckout() {
        WebElement checkoutButton = DriverManager.getDriverInstance().findElement(By.xpath(HomeConstants.CHECKOUT_BUTTON_LOCATOR));
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriverInstance();
        executor.executeScript("arguments[0].click();", checkoutButton);
    }

    public static void verifyCheckoutPageElemnts() {
        WebActionManager.waitPresence(HomeConstants.BILLING_DETAIL_LABEL_LOCATOR);
        WebActionManager.waitPresence(HomeConstants.ADITIONAL_INFORMATION_LABEL_LOCATOR);
    }

    public static void fillBiilingDetails(String firtsName, String lastName, String company, String email, String phone, String country,
                                          String address, String city, String state, String zipcode) {
        WebActionManager.setInput(HomeConstants.FIRST_NAME_INPUT_LOCATOR, firtsName);
        WebActionManager.setInput(HomeConstants.LAST_NAME_INPUT_LOCATOR, lastName);
        WebActionManager.setInput(HomeConstants.COMPANY_INPUT_LOCATOR, company);
        WebActionManager.setInput(HomeConstants.EMAIL_INPUT_LOCATOR, email);
        WebActionManager.setInput(HomeConstants.PHONE_INPUT_LOCATOR, phone);
        WebActionManager.waitVisibility(HomeConstants.COUNTRY_SPAN_LOCATOR);
        WebActionManager.waitClickable(HomeConstants.COUNTRY_SPAN_LOCATOR);
        WebElement countryElement = WebActionManager.getElement(HomeConstants.COUNTRY_SPAN_LOCATOR);
        scrollTo(HomeConstants.EMAIL_INPUT_LOCATOR);

        try {
            countryElement.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("ElementClickInterceptedException: " + e.getMessage());
        }

        WebActionManager.waitVisibility(HomeConstants.COUNTRY_SEARCH_INPUT_LOCATOR);
        WebActionManager.setInput(HomeConstants.COUNTRY_SEARCH_INPUT_LOCATOR, country);
        WebActionManager.setInput(HomeConstants.COUNTRY_SEARCH_INPUT_LOCATOR, String.valueOf(Keys.ENTER));
        WebActionManager.setInput(HomeConstants.ADDRESS_INPUT_LOCATOR, address);
        WebActionManager.setInput(HomeConstants.CITY_INPUT_LOCATOR, city);
        WebElement stateElement = WebActionManager.getElement(HomeConstants.STATE_INPUT_LOCATOR);
        scrollTo(HomeConstants.ADDRESS_INPUT_LOCATOR);

        try {
            stateElement.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("ElementClickInterceptedException: " + e.getMessage());
        }
        WebActionManager.setInput(HomeConstants.STATE_SEARCH_INPUT_LOCATOR, state);
        WebActionManager.setInput(HomeConstants.STATE_SEARCH_INPUT_LOCATOR, String.valueOf(Keys.ENTER));
        WebActionManager.setInput(HomeConstants.ZIPCODE_INPUT_LOCATOR, zipcode);
        String productPrice = WebActionManager.getText(HomeConstants.PRODUCT_PRICE_LOCATOR);
        precioProductoValue = productPrice;
    }

    public static void choosePaymentMethod(String payment_method) {
        paymentMethodValue = payment_method;
        waitByVariableXPath(HomeConstants.PAYMENT_METHOD_XPATH, payment_method, 10);
        scrollTo(HomeConstants.TITLE_YOUR_ORDER_LOCATOR);
        sleep(500);
        clickByVariableXPath(HomeConstants.PAYMENT_METHOD_XPATH, payment_method);
    }

    public static void clickPlaceOrder() {
        WebActionManager.waitClickable(HomeConstants.PLACE_ORDER_BUTTON_LOCATOR);
        WebActionManager.click(HomeConstants.PLACE_ORDER_BUTTON_LOCATOR);
    }

    public static void goOrderConfirmation() {
        WebActionManager.waitPresence(HomeConstants.TITLE_ORDER_DETAILS_LOCATOR);
        String precioTotal = WebActionManager.getText(HomeConstants.PRICE_ORDER_DETAILS_LOCATOR);
        String paymentMethod = WebActionManager.getText(HomeConstants.PAYMENT_ORDER_DETAILS_LOCATOR);
        Assert.assertEquals(precioTotal, precioProductoValue);
        Assert.assertEquals(paymentMethod, paymentMethodValue);
    }
}
