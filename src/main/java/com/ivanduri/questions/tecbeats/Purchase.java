package com.ivanduri.questions.tecbeats;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static com.ivanduri.userinterface.tecbeats.CheckoutCompletePage.PURCHASE_COMPLETE_TEXT_ALERT;

public class Purchase implements Question<String> {

    public static Purchase finishAlertText(){
        return new Purchase();
    }

    @Override
    public String answeredBy(Actor actor) {
        return PURCHASE_COMPLETE_TEXT_ALERT.resolveFor(actor).waitUntilPresent().getText();
    }
}
