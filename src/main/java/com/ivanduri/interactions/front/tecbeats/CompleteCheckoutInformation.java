package com.ivanduri.interactions.front.tecbeats;

import lombok.Data;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;

import static com.ivanduri.userinterface.tecbeats.CheckoutInformationPage.*;
import static com.ivanduri.userinterface.tecbeats.CheckoutOverviewPage.FINISH_BUTTON;
import static net.serenitybdd.screenplay.Tasks.instrumented;

@Data
public class CompleteCheckoutInformation implements Interaction {

    private final String name;
    private final String lastName;
    private final String postalZipCode;

    public static CompleteCheckoutInformation withTheData(String name, String lastName, String postalZipCode){
        return instrumented(CompleteCheckoutInformation.class, name, lastName, postalZipCode);
    }

    public CompleteCheckoutInformation andFinishThePurchase(){
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(name).into(FIRST_NAME_INPUT),
                Enter.theValue(lastName).into(LAST_NAME_INPUT),
                Enter.theValue(postalZipCode).into(POSTAL_ZIP_CODE_INPUT),
                Click.on(CONTINUE_BUTTON));

        actor.attemptsTo(
                Check.whether(FINISH_BUTTON.resolveFor(actor).isCurrentlyVisible())
                        .andIfSo(Click.on(FINISH_BUTTON)));
    }
}
