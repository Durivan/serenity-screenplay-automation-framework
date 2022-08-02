package com.ivanduri.interactions.commons;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;

@AllArgsConstructor
public class ScrollUntil implements Interaction {

  private Target element;

  public static ScrollUntil clickable(Target element) {
    return instrumented(ScrollUntil.class, element);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    do {
      BrowseTheWeb.as(actor).evaluateJavascript("window.scrollBy(0,100)");
    } while (!element.resolveFor(actor).isClickable());
  }
}
