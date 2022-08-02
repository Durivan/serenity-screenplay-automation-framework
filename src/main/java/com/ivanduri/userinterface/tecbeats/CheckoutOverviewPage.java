package com.ivanduri.userinterface.tecbeats;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutOverviewPage {

    public static final Target FINISH_BUTTON =
            Target.the("Finish button").located(By.id("finish"));

    private CheckoutOverviewPage() {
        super();
    }
}
