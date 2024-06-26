package lippia.web.services;

import com.crowdar.core.PropertyManager;
import com.crowdar.core.actions.ActionManager;
import com.crowdar.core.actions.WebActionManager;
import lippia.web.constants.HomeConstants;

import static com.crowdar.core.actions.WebActionManager.navigateTo;

public class HomeService extends ActionManager {

    public static void navegarWeb() {
        navigateTo(PropertyManager.getProperty("web.base.url"));
    }

    public static void clickRegisterOption() {
        WebActionManager.waitClickable(HomeConstants.REGISTER_OPTION);
        WebActionManager.click(HomeConstants.REGISTER_OPTION);
    }

}
