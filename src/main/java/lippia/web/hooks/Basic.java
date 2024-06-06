package lippia.web.hooks;

import com.crowdar.driver.DriverManager;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import lippia.web.listeners.DriverValidatorListener;
import io.cucumber.java.BeforeStep;

public class Basic {

    @Before( order = 0 )
    public void beforeScenario( Scenario scenario ) {
       DriverManager.getDriverInstance().register( new DriverValidatorListener() );
    }

    @BeforeStep( order = 1 )
    public void BeforeStep() {
    }
}
