package com.ivanduri.stepdefinitions.api;

import com.ivanduri.models.User;
import com.ivanduri.questions.api.users.TheUser;
import com.ivanduri.tasks.api.ConsultUser;
import com.ivanduri.tasks.api.CreateUser;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import java.util.List;
import java.util.Map;

import static com.ivanduri.utils.enums.EnumVariablesSesion.*;
import static com.ivanduri.utils.methods.GenericMethods.getRandomNumber;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.lang.Boolean.TRUE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ApiStepDefinitions {

    private EnvironmentVariables environmentVariables;

    @Given("the {string} is pointing to reqres.in")
    public void theActorIsPointingToReqresIn(String actorName) {
        theActorCalled(actorName).whoCan(
                CallAnApi.at(EnvironmentSpecificConfiguration.from(environmentVariables)
                        .getProperty("api.base.url")));
    }

    @When("he/she/it creates an user")
    public void createAnUser(DataTable dataTable) {
        List<Map<String, String>> signUpForms = dataTable.asMaps(String.class, String.class);
        String firstName = signUpForms.get(0).get("FirstName");
        String job = signUpForms.get(0).get("Job");
        theActorInTheSpotlight().attemptsTo(
                CreateUser.called(firstName, job));
    }

    @Then("he should see the expected response of the create user service")
    public void heShouldSeeTheExpectedResponseOfCreateUserService() {
        User bodyRequest = theActorInTheSpotlight().recall(CREATE_USER_REQUEST_BODY.getVariableSesion());
        User createUserResponse = theActorInTheSpotlight().recall(CREATE_USER_RESPONSE.getVariableSesion());

        theActorInTheSpotlight().should(
                seeThat(TheUser.wasCreatedWithTheCorrectData(bodyRequest, createUserResponse), equalTo(TRUE)));
    }



    @When("he/she/it consult to the service for a random user")
    public void heConsultToTheServiceForARandomUser() {
        int randomId = getRandomNumber(10, 1);
        theActorInTheSpotlight().attemptsTo(
                ConsultUser.byId(String.valueOf(randomId)));
    }

    @Then("he should see that there aren't any empty field on the response")
    public void heShouldSeeThatThereArenTAnyEmptyFieldOnTheResponse() {

    }

    @And("the structure of the response is correct")
    public void theStructureOfTheResponseIsCorrect() {
        //Response response = (Response) SerenityRest.lastResponse().getBody();
        //assertThat(response.toString(), matchesJsonSchemaInClasspath("schemas/consultUserResponse.json"));
    }
}
