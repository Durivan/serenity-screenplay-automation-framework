package com.ivanduri.userinterface.tecbeats;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutInformationPage {

    public static final Target FIRST_NAME_INPUT =
            Target.the("Firstname input field").located(By.id("first-name"));

    public static final Target LAST_NAME_INPUT =
            Target.the("Lastname input field").located(By.id("last-name"));

    public static final Target POSTAL_ZIP_CODE_INPUT =
            Target.the("Postal zip input field").located(By.id("postal-code"));

    public static final Target CONTINUE_BUTTON =
            Target.the("Continue button").located(By.id("continue"));

    private CheckoutInformationPage() {
        super();
    }

}
