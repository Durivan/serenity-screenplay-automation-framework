package com.ivanduri.userinterface.tecbeats;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutCompletePage {

    public static final Target PURCHASE_COMPLETE_TEXT_ALERT =
            Target.the("Purchase complete text alert").located(By.className("complete-text"));

    private CheckoutCompletePage() {
        super();
    }
}
