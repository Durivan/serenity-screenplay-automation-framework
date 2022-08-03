package com.ivanduri.repository.user;

import com.ivanduri.models.User;
import com.ivanduri.repository.commons.Repository;

import java.util.Map;

import static com.ivanduri.utils.enums.EnumVariablesSesion.CREATE_USER_REQUEST_BODY;
import static com.ivanduri.utils.methods.GenericMethods.getRandomEmail;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class UserRepository extends Repository<User> {

    private static final String USER_EMAIL = "User email";
    private static final String RANDOM = "random";

    public User generateRequestCreateUser(Map<String, String> mapUserData) {
        User userToReturn = new UserRepository().mapperByKey(mapUserData);
        userToReturn.setEmail(
                mapUserData.get(USER_EMAIL).toLowerCase().contains(RANDOM)
                        ? getRandomEmail()
                        : mapUserData.get(USER_EMAIL));
        theActorInTheSpotlight()
                .remember(CREATE_USER_REQUEST_BODY.getVariableSesion(), userToReturn);
        return userToReturn;
    }

}
