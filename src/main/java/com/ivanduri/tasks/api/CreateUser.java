package com.ivanduri.tasks.api;

import com.ivanduri.models.User;
import com.ivanduri.questions.api.commons.ResponseStatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.apache.http.HttpStatus;

import static com.ivanduri.utils.enums.EnumResources.USERS;
import static com.ivanduri.utils.enums.EnumVariablesSesion.CREATE_USER_REQUEST_BODY;
import static com.ivanduri.utils.enums.EnumVariablesSesion.CREATE_USER_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

@Data
@AllArgsConstructor
public class CreateUser implements Task {

    private String userName;
    private String job;

    public static CreateUser called(String userName, String job){
        return instrumented(CreateUser.class, userName, job);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        User body = new User(userName, job, null, null);
        actor.remember(CREATE_USER_REQUEST_BODY.getVariableSesion(), body);

        actor.attemptsTo(
                Post.to(USERS.getResource())
                        .with(request -> request.header("Content-Type", "application/json")
                                .body(body))
                        .withRequest(request -> request.log().all()));

        actor.should(
                seeThatResponse(response -> response.log().all()),
                seeThat(ResponseStatusCode.obtainedInService(), equalTo(HttpStatus.SC_CREATED)));

        actor.remember(CREATE_USER_RESPONSE.getVariableSesion(), SerenityRest.lastResponse().as(User.class));
    }
}
