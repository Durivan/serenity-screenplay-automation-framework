package com.ivanduri.userinterface.tecbeats;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    public static final Target USERNAME_INPUT =
            Target.the("Username input field").located(By.id("user-name"));

    public static final Target PASSWORD_INPUT =
            Target.the("Password input field").located(By.id("password"));

    public static final Target LOGIN_BUTTON =
            Target.the("Login button").located(By.id("login-button"));

    private LoginPage() {
        super();
    }
}
