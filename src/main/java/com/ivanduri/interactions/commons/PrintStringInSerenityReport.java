package com.ivanduri.interactions.commons;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import lombok.AllArgsConstructor;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

@AllArgsConstructor
public class PrintStringInSerenityReport implements Interaction {
  private final String title;
  private final String value;

  @Override
  public <T extends Actor> void performAs(T actor) {
    Serenity.recordReportData().withTitle(title).andContents(value);
  }

  public static PrintStringInSerenityReport withTitleAndValue(String title, String value) {
    return instrumented(PrintStringInSerenityReport.class, title, value);
  }
}
