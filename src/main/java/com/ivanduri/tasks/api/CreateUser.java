package com.ivanduri.tasks.api;

import com.ivanduri.models.User;
import com.ivanduri.questions.api.commons.ResponseStatusCode;
import com.ivanduri.repository.user.UserRepository;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static com.ivanduri.utils.enums.EnumResources.CREATE_USER;
import static com.ivanduri.utils.enums.EnumVariablesSesion.CREATE_USER_RESPONSE;
import static net.serenitybdd.core.environment.ConfiguredEnvironment.getEnvironmentVariables;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;


public class CreateUser implements Task {

    private final User user;
    private final Map<String, String> headers = new HashMap<>();

    public CreateUser(User user){
        this.user = user;
        headers.put("Authorization", EnvironmentSpecificConfiguration.from(
                getEnvironmentVariables()).getProperty("access.token"));
        headers.put("Content-Type", "application/json");
    }

    public static CreateUser called(Map<String, String> mapUserData){
        return instrumented(CreateUser.class, new UserRepository().generateRequestCreateUser(mapUserData));
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(CREATE_USER.getResource())
                        .with(request -> request.headers(headers).body(user))
                        .withRequest(request -> request.log().all()));

        actor.should(
                seeThatResponse(response -> response.log().all()),
                seeThat(ResponseStatusCode.obtainedInService(), equalTo(HttpStatus.SC_CREATED)));

        actor.remember(CREATE_USER_RESPONSE.getVariableSesion(), SerenityRest.lastResponse().as(User.class));
    }
}
