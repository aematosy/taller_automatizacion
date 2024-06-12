package lippia.web.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lippia.web.services.SauceDemoService;

public class SauceDemoSteps extends PageSteps {

    @Given("the user is on the SauceDemo login page")
    public void the_user_is_on_home_page() {
        SauceDemoService.navegarWeb();
    }

    @And("the user enters the username {string} and the password {string}")
    public void the_user_enters_username_lastname(String username, String lastname){
        SauceDemoService.fillLoginsInputs(username,lastname);
    }

    @And("the user clicks the login button")
    public void the_user_clicks_login_button(){
        SauceDemoService.clickLoginButton();
    }

    @And("the user should be redirected to the inventory page")
    public void the_user_should_be_inventory_page(){
        SauceDemoService.validateInventoryPage();
    }


}
