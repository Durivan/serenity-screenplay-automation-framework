package com.ivanduri.tasks.api;


import com.ivanduri.models.User;
import com.ivanduri.questions.api.commons.ResponseStatusCode;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static com.ivanduri.utils.enums.EnumResources.GET_USER;
import static com.ivanduri.utils.enums.EnumVariablesSesion.CONSULT_USER_RESPONSE;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.core.environment.ConfiguredEnvironment.getEnvironmentVariables;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

public class ConsultUser implements Task {

    private Integer userId;
    private final Map<String, String> headers = new HashMap<>();

    public ConsultUser(Integer userId){
        this.userId = userId;
        headers.put("Authorization", EnvironmentSpecificConfiguration.from(
                getEnvironmentVariables()).getProperty("access.token"));
        headers.put("Content-Type", "application/json");
    }

    public static ConsultUser byId(int id){
        return instrumented(ConsultUser.class, id);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(GET_USER.getResource())
                        .with(request -> request.headers(headers)
                                .pathParam("userId", userId))
                        .withRequest(request -> request.log().all())
        );

        actor.should(
                seeThatResponse(response -> response.log().all()),
                seeThatResponse(response -> response.assertThat().body(matchesJsonSchemaInClasspath("schemas/consultUserResponse.json"))),
                seeThat(ResponseStatusCode.obtainedInService(), equalTo(HttpStatus.SC_OK)));

        actor.remember(CONSULT_USER_RESPONSE.getVariableSesion(), SerenityRest.lastResponse().as(User.class));
    }
}
