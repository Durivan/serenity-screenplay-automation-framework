package com.ivanduri.interactions.front.tecbeats;

import lombok.Data;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.ivanduri.userinterface.tecbeats.LoginPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

@Data
public class Signin implements Interaction {

    private final String username;
    private final String password;

    public static Signin withTheUser(String username, String password){
        return instrumented(Signin.class, username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(username).into(USERNAME_INPUT),
                Enter.theValue(password).into(PASSWORD_INPUT),
                Click.on(LOGIN_BUTTON));
    }
}
