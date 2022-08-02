package com.ivanduri.questions.api.users;

import com.ivanduri.models.User;
import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
@AllArgsConstructor
public class TheUser implements Question<Boolean> {

    private User userRequest;
    private User userResponse;

    public static TheUser wasCreatedWithTheCorrectData(User userRequest, User userResponse){
        return new TheUser(userRequest, userResponse);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return this.userRequest.equals(this.userResponse);
    }
}
