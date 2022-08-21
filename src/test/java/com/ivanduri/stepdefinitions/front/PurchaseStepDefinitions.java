package com.ivanduri.stepdefinitions.front;
import com.ivanduri.interactions.front.tecbeats.AddToCart;
import com.ivanduri.interactions.front.tecbeats.CompleteCheckoutInformation;
import com.ivanduri.interactions.front.tecbeats.Signin;
import com.ivanduri.questions.tecbeats.Purchase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.Map;

import static com.ivanduri.utils.enums.EnumConstants.BASE_URL;
import static org.hamcrest.Matchers.containsString;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PurchaseStepDefinitions {

    private static final String USER = "The user";
    private static final String FIRSTNAME = "Firstname";
    private static final String LASTNAME = "Lastname";
    private static final String ZIPCODE = "ZipCode";
    private EnvironmentVariables environmentVariables;

    @Given("the user is on the login page")
    public void userOnLoginPage() {
        theActorCalled(USER).wasAbleTo(Open.url(
                EnvironmentSpecificConfiguration.from(environmentVariables)
                        .getProperty(BASE_URL.getConstantValue())
        ));
    }

    @And("he/she/it signs in with the following credentials: {string}, {string}")
    public void heSignsInWithTheFollowingCredentials(String username, String password) {
        theActorInTheSpotlight().attemptsTo(
                Signin.withTheUser(username, password));
    }

    @When("he adds to the cart a random item from the product page")
    public void heAddsToTheCartARandomItemFromTheProductPage() {
        theActorInTheSpotlight().attemptsTo(
                AddToCart.aRandomItem().andGoToCheckout());
    }

    @And("completes the checkout form with the data")
    public void completesTheCheckoutFormWithTheData(DataTable dataTable) {
        Map<String, String> formData = dataTable.asMap(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(
                CompleteCheckoutInformation
                        .withTheData(formData.get(FIRSTNAME), formData.get(LASTNAME), formData.get(ZIPCODE))
                        .andFinishThePurchase());
    }

    @Then("he/she/it should see on the purchase congrats page the following text: {string}")
    public void heShouldSeeOnThePurchaseCongratsPageTheFollowingText(String expectedText) {
        theActorInTheSpotlight().should(
                seeThat(Purchase.finishAlertText(), containsString(expectedText)));
    }

}
