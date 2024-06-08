package lippia.web.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lippia.web.services.LoginService;

import static org.testng.Assert.assertTrue;

public class LoginSteps extends PageSteps {

    @When("I click on {string} button")
    public void iClickOnButton(String button) {
        LoginService.clickSignOnButton();
    }

    @And("I log in with username {string} and password {string}")
    public void iLogInWithUsernameAndPassword(String username, String password) {
        LoginService.login(username, password);
    }

    @Then("I should see the message {string}")
    public void iShouldSeeTheMessage(String message) {
        assertTrue(LoginService.isLoginSuccessful());
    }

}
