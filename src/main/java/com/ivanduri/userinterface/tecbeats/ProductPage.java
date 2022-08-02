package com.ivanduri.userinterface.tecbeats;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductPage {

    public static final Target ADD_TO_CART_BUTTON =
            Target.the("Add to cart - button").located(By.xpath("//button[contains(@class, 'btn_inventory')]"));

    public static final Target SHOPPING_CART =
            Target.the("Shopping cart - link").located(By.className("shopping_cart_link"));

    private ProductPage() {
        super();
    }
}
