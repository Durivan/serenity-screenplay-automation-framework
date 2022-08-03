package com.ivanduri.tasks.api;

import com.ivanduri.models.GetUserByIdResponse;
import com.ivanduri.questions.api.commons.ResponseStatusCode;
import lombok.AllArgsConstructor;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.apache.http.HttpStatus;

import static com.ivanduri.utils.enums.EnumResources.GET_USER;
import static com.ivanduri.utils.enums.EnumVariablesSesion.CONSULT_USER_RESPONSE;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

@AllArgsConstructor
public class ConsultUser implements Task {

    private String id;

    public static ConsultUser byId(String id){
        return instrumented(ConsultUser.class, id);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(GET_USER.getResource())
                        .with(request -> request.header("Content-Type", "application/json")
                                .pathParam("userId", id))
                        .withRequest(request -> request.log().all())
        );

        actor.should(
                seeThatResponse(response -> response.log().all()),
                seeThatResponse(response -> response.assertThat().body(matchesJsonSchemaInClasspath("schemas/consultUserResponse.json"))),
                seeThat(ResponseStatusCode.obtainedInService(), equalTo(HttpStatus.SC_OK)));

        actor.remember(CONSULT_USER_RESPONSE.getVariableSesion(), SerenityRest.lastResponse().as(GetUserByIdResponse.class));
    }
}
