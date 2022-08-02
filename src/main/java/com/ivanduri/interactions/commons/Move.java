package com.ivanduri.interactions.commons;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.interactions.Actions;

public class Move implements Interaction {
  private WebElementFacade webElementFacade;

  public Move(WebElementFacade webElementFacade) {
    this.webElementFacade = webElementFacade;
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    Actions action = new Actions(BrowseTheWeb.as(actor).getDriver());
    action.moveToElement(webElementFacade).click().perform();
  }

  public static Move toTheElementAndClick(WebElementFacade webElementFacade) {
    return instrumented(Move.class, webElementFacade);
  }
}
