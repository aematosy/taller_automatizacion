package lippia.web.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lippia.web.constants.HomeConstants;
import lippia.web.services.HomeService;

import static lippia.web.utils.Utilities.verifyElementPresence;

public class HomeSteps extends PageSteps {

    @Given("^The user is on home page$")
    public void home() {
        HomeService.navegarWeb();
    }

    @And("^I click on \"Iniciar sesion\" option$")
    public void clickIniciarSesion() throws InterruptedException {
        HomeService.clickIniciarSesionMenu();
    }

    @And("^I click on \"Iniciar con correo electronico\" option$")
    public void clickEmailOption(){
        HomeService.clickStarWithEmailOption();
    }

    @And("I log in with email \"([^\"]*)\" and password \"([^\"]*)\"")
    public void fillEmailPassword(String email, String password){
        HomeService.fillEmailPassword(email, password);
    }

    @Then("^I validate that I have logged in correctly$")
    public void verifyLogoutElements(){
        HomeService.verifyLogoutElements();
    }

    @Then("I validate the error message \"([^\"]*)\"")
    public void validateFailedLogin(String message){
        HomeService.validateFailedLogin(message);
    }






    @Then("^The Home page must contain only \"([^\"]*)\" Arrivals$")
    public void validateHomePageContainsArrivals(String numberOfArrivals){
        HomeService.testHomeSliders(numberOfArrivals);
    }

    @Then("^Click the image in the Arrival: \"([^\"]*)\"$")
    public void clickArrivals(String newArrival) throws InterruptedException {
        HomeService.clickImageArrival(newArrival);
    }

    @And("^Test whether it is navigating to the next page where the user can add that book to his basket$")
    public void validateStayNextPage(){
        HomeService.verifyPage();
    }

    @And("^User validate can add that book to his basket$")
    public void validateArrivalElements(){
        HomeService.verifyArrivalElements();
    }

    @And("^Click on the Add To Basket button which adds that book to your basket$")
    public void addArrivalElementBasket(){
        HomeService.addArrivalElementBasket();
    }

    @And("^User can view that Book in the Menu item with price$")
    public void viewAmount(){
        HomeService.validateAmountBasket();
    }

    @When("^Click on Item link which navigates to proceed to checkout page$")
    public void clickItemLink(){
        HomeService.clickItemLink();
    }

    @Then("^Now user can find total and subtotal values just above the Proceed to Checkout button$")
    public void validatePageCheckout(){
        HomeService.verifyElemntsCheckout();
    }

    @And("^The total is less than the subtotal because taxes are added in the subtotal$")
    @Then("^The total always < subtotal because taxes are added in the subtotal$")
    public void validateTotalAmount(){
        HomeService.validateTotalAmount();
    }

    @When("^Click on Proceed to Checkout button which navigates to payment gateway page$")
    public void clickOpcCheckout(){
        HomeService.clickOpcCheckout();
    }

    @When("^User can view Billing Details, Order Details, Additional details, and Payment gateway details$")
    public void validateElemntsCheckout(){
        HomeService.verifyCheckoutPageElemnts();
    }

    @When("User fill his details in billing details from: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"")
    public void fillBillingDetail(String firstName, String lastName, String Company, String Email, String phone, String country, String Address, String city, String state, String zipcode){
        HomeService.fillBiilingDetails(firstName, lastName, Company, Email, phone, country, Address, city, state, zipcode);
    }

    @And("^User select payment like \"([^\"]*)\"$")
    public void selectPayment(String payment_method){
        HomeService.choosePaymentMethod(payment_method);
    }

    @Then("^User can see order details in Your Order section and add coupons if any$")
    public void userIsOnOrderDetailsPage(){
        verifyElementPresence(HomeConstants.ADD_COUPON_LOCATOR);
        verifyElementPresence(HomeConstants.YOUR_ORDER_TABLE_LOCATOR);
    }


    @And("^Click on Place Order button to complete process$")
    public void clickPlaceOrderButton(){
        HomeService.clickPlaceOrder();
    }

    @Then("^User navigate to Order confirmation page with order details, bank details, customer details, and billing details$")
    public void navigateOrderConfirmation(){
        HomeService.goOrderConfirmation();
    }
}
