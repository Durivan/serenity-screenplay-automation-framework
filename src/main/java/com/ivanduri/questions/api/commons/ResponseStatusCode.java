package com.ivanduri.questions.api.commons;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseStatusCode implements Question<Integer> {

  public static ResponseStatusCode obtainedInService() {
    return new ResponseStatusCode();
  }

  @Override
  public Integer answeredBy(Actor actor) {
    return SerenityRest.lastResponse().statusCode();
  }
}
