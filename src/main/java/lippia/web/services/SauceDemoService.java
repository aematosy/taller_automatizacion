package lippia.web.services;

import com.crowdar.core.PropertyManager;
import com.crowdar.core.actions.ActionManager;
import com.crowdar.core.actions.WebActionManager;
import lippia.web.constants.SauceDemoConstants;
import lippia.web.constants.InventoryConstants;

import static com.crowdar.core.actions.WebActionManager.navigateTo;

public class SauceDemoService extends ActionManager {

    public static void navegarWeb() {
        navigateTo(PropertyManager.getProperty("web.base.url"));
    }

    public static void fillLoginsInputs(String username,String password){
        WebActionManager.waitPresence(SauceDemoConstants.USERNAME_INPUT);
        WebActionManager.setInput(SauceDemoConstants.USERNAME_INPUT,username);
        WebActionManager.setInput(SauceDemoConstants.PASSWORD_INPUT,password);
    }

    public static void clickLoginButton(){
        WebActionManager.waitClickable(SauceDemoConstants.LOGIN_BUTTON);
        WebActionManager.click(SauceDemoConstants.LOGIN_BUTTON);
    }

    public static void validateInventoryPage(){
        WebActionManager.waitPresence(InventoryConstants.PRODUCT_LABEL);
    }
}
