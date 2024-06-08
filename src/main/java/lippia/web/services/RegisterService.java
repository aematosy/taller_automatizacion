package lippia.web.services;

import com.crowdar.core.actions.ActionManager;
import com.crowdar.core.actions.WebActionManager;
import lippia.web.constants.RegisterConstants;
import org.testng.Assert;

public class RegisterService extends ActionManager {

    static String fullName;

    public static void fillContactInformation(String firstname, String lastname, String phone, String email) {
        fullName = firstname + " " + lastname;
        WebActionManager.waitClickable(RegisterConstants.FIRSTNAME_INPUT);
        WebActionManager.setInput(RegisterConstants.FIRSTNAME_INPUT, firstname);
        WebActionManager.setInput(RegisterConstants.LASTNAME_INPUT, lastname);
        WebActionManager.setInput(RegisterConstants.PHONE_INPUT, phone);
        WebActionManager.setInput(RegisterConstants.EMAIL_INPUT, email);
    }

    public static void fillMailingInformation(String address, String city, String state, String postalCode, String country) {
        WebActionManager.waitClickable(RegisterConstants.ADDRESS_INPUT);
        WebActionManager.setInput(RegisterConstants.ADDRESS_INPUT, address);
        WebActionManager.setInput(RegisterConstants.CITY_INPUT, city);
        WebActionManager.setInput(RegisterConstants.STATE_INPUT, state);
        WebActionManager.setInput(RegisterConstants.POSTALCODE_INPUT, postalCode);
        WebActionManager.setDropdownByValue(RegisterConstants.COUNTRY_SELECT, country);
    }

    public static void fillUserInformation(String username, String password) {
        WebActionManager.waitClickable(RegisterConstants.USERNAME_INPUT);
        WebActionManager.setInput(RegisterConstants.USERNAME_INPUT, username);
        WebActionManager.setInput(RegisterConstants.PASSWORD_INPUT, password);
        WebActionManager.setInput(RegisterConstants.CONFIRM_PASSWORD_INPUT, password);
        WebActionManager.waitClickable(RegisterConstants.ENVIAR_BUTTON);
        WebActionManager.click(RegisterConstants.ENVIAR_BUTTON);
    }

    public static void validateSuccessfulRegistration(){
        try {
        String expectedMessage = "Dear " + fullName + ",";
        String actualMessage = WebActionManager.getText(RegisterConstants.SUCCESS_MESSAGE);
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "El mensaje de registro exitoso no es el esperado.");
        } catch (Exception e) {
            Assert.fail("El mensaje de registro exitoso no es el esperado.");
        }
    }
}
