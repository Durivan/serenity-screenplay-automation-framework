package com.ivanduri.stepdefinitions.api;

import com.ivanduri.models.User;
import com.ivanduri.questions.api.users.TheUser;
import com.ivanduri.tasks.api.ConsultUser;
import com.ivanduri.tasks.api.CreateUser;
import com.ivanduri.tasks.api.UpdateUser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.Map;

import static com.ivanduri.utils.enums.EnumConstants.API_BASE_URL;
import static com.ivanduri.utils.enums.EnumVariablesSesion.*;
import static java.lang.Boolean.TRUE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class ApiStepDefinitions {

    private EnvironmentVariables environmentVariables;

    @Given("the {string} is pointing to gorest.co.in")
    public void theActorIsPointingToTheApi(String actorName) {
        theActorCalled(actorName).whoCan(
                CallAnApi.at(EnvironmentSpecificConfiguration.from(environmentVariables)
                        .getProperty(API_BASE_URL.getConstantValue())));
    }

    @When("he/she/it creates an user")
    @And("he/she/it has created an user")
    public void createAnUser(Map<String, String> mapUserData) {
        theActorInTheSpotlight().attemptsTo(
                CreateUser.called(mapUserData));
    }

    @Then("he/she/it should see that the service has returned the same data that was sent on user creation")
    public void heShouldSeeTheExpectedResponseOfCreateUserService() {
        User bodyRequest = theActorInTheSpotlight().recall(CREATE_USER_REQUEST_BODY.getVariableSesion());
        User createUserResponse = theActorInTheSpotlight().recall(CREATE_USER_RESPONSE.getVariableSesion());
        theActorInTheSpotlight().should(
                seeThat(TheUser.wasCreatedWithTheCorrectData(bodyRequest, createUserResponse), equalTo(TRUE)));
        }

    @When("he updates the user data")
    public void heUpdatesTheUserData(Map<String, String> mapUserData) {
        int userIdCreated =theActorInTheSpotlight().recall(USER_ID.getVariableSesion());
        theActorInTheSpotlight().attemptsTo(
                UpdateUser.withTheData(mapUserData).byId(userIdCreated));
    }

    @And("he consults for the user edited")
    public void heConsultsForTheUserEdited() {
        int userIdUpdated = theActorInTheSpotlight().recall(USER_ID.getVariableSesion());
        theActorInTheSpotlight().attemptsTo(
                ConsultUser.byId(userIdUpdated));
    }

    @Then("he should see that the user was updated correctly")
    public void heShouldSeeThatTheUserWasUpdatedCorrectly() {
        User bodyRequestUserUpdated = theActorInTheSpotlight().recall(UPDATE_USER_REQUEST_BODY.getVariableSesion());
        User consultUserResponse = theActorInTheSpotlight().recall(CONSULT_USER_RESPONSE.getVariableSesion());
        theActorInTheSpotlight().should(
                seeThat(TheUser.wasCreatedWithTheCorrectData(bodyRequestUserUpdated, consultUserResponse), equalTo(TRUE)));
    }
}
