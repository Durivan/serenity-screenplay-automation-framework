package com.ivanduri.userinterface.tecbeats;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {

    public static final Target CHECKOUT_BUTTON =
            Target.the("Go to checkout - button").located(By.id("checkout"));

    private CartPage() {
        super();
    }

}
