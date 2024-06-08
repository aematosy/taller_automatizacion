package lippia.web.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lippia.web.services.HomeService;

public class HomeSteps extends PageSteps {

    @Given("^The user is on home page$")
    public void home() {
        HomeService.navegarWeb();
    }

    @And("^I click on \"Register\" option$")
    public void clickRegister() throws InterruptedException {
        HomeService.clickRegisterOption();
    }
}
