package com.ivanduri.interactions.front.tecbeats;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.conditions.Check;

import java.util.Random;

import static com.ivanduri.userinterface.tecbeats.CartPage.CHECKOUT_BUTTON;
import static com.ivanduri.userinterface.tecbeats.ProductPage.ADD_TO_CART_BUTTON;
import static com.ivanduri.userinterface.tecbeats.ProductPage.SHOPPING_CART;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddToCart implements Interaction {

    public static AddToCart aRandomItem(){
        return instrumented(AddToCart.class);
    }

    public AddToCart andGoToCheckout(){
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Random r = new Random();
        actor.attemptsTo(
                Click.on(ADD_TO_CART_BUTTON.resolveAllFor(actor)
                        .stream()
                        .skip(r.nextInt(ADD_TO_CART_BUTTON.resolveAllFor(actor).toArray().length))
                        .findFirst()
                        .get()),
                Click.on(SHOPPING_CART));
        actor.attemptsTo(
                Check.whether(CHECKOUT_BUTTON.resolveFor(actor).isCurrentlyVisible())
                        .andIfSo(Click.on(CHECKOUT_BUTTON)));
    }
}
