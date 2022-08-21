package com.ivanduri.tasks.api;

import com.ivanduri.models.User;
import com.ivanduri.questions.api.commons.ResponseStatusCode;
import com.ivanduri.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import org.apache.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

import static com.ivanduri.utils.enums.EnumConstants.*;
import static com.ivanduri.utils.enums.EnumConstants.APPLICATION_JSON;
import static com.ivanduri.utils.enums.EnumResources.UPDATE_USER;
import static com.ivanduri.utils.enums.EnumVariablesSesion.*;
import static net.serenitybdd.core.environment.ConfiguredEnvironment.getEnvironmentVariables;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

@AllArgsConstructor
public class UpdateUser implements Task {

    private Integer userId;
    private final Map<String, String> headers = new HashMap<>();
    private Map<String, String> userNewData;

    public UpdateUser(Map<String, String> userNewData){
        this.userNewData = userNewData;
        headers.put(AUTHORIZATION.getConstantValue(), EnvironmentSpecificConfiguration.from(
                getEnvironmentVariables()).getProperty(ACCESS_TOKEN.getConstantValue()));
        headers.put(CONTENT_TYPE.getConstantValue(), APPLICATION_JSON.getConstantValue());
    }

    public UpdateUser byId(int userId){
        this.userId = userId;
        return this;
    }

    public static UpdateUser withTheData(Map<String, String> userNewData){
        return instrumented(UpdateUser.class, userNewData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        User userCreated = actor.recall(CREATE_USER_REQUEST_BODY.getVariableSesion());
        User newUser = new UserRepository().updateUserByParameters(userNewData, userCreated);
        actor.attemptsTo(
                Put.to(UPDATE_USER.getResource())
                        .with(request -> request.headers(headers)
                                .body(newUser)
                                .pathParam(USERID.getConstantValue(), userId))
                        .withRequest(request -> request.log().all()));
        actor.should(
                seeThatResponse(response -> response.log().all()),
                seeThat(ResponseStatusCode.obtainedInService(), equalTo(HttpStatus.SC_OK)));
        actor.remember(UPDATE_USER_RESPONSE.getVariableSesion(), SerenityRest.lastResponse().as(User.class));
    }
}
