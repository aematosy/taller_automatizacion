package lippia.web.services;

import com.crowdar.core.actions.ActionManager;
import com.crowdar.core.actions.WebActionManager;
import lippia.web.constants.RegisterConstants;
import lippia.web.constants.LoginConstants;

public class LoginService extends ActionManager {

    public static void clickSignOnButton(){
        WebActionManager.waitClickable(RegisterConstants.SIGNIN_BUTTON);
        WebActionManager.click(RegisterConstants.SIGNIN_BUTTON);
    }

    public static void login(String username, String password){
        WebActionManager.waitClickable(LoginConstants.USERNAME_INPUT);
        WebActionManager.setInput(LoginConstants.USERNAME_INPUT, username);
        WebActionManager.setInput(LoginConstants.PASSWORD_INPUT, password);
        WebActionManager.click(LoginConstants.SUBIT_BUTTON);
    }

    public static boolean isLoginSuccessful(){
        try {
            WebActionManager.waitPresence(LoginConstants.SUCCESS_MESSAGE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
