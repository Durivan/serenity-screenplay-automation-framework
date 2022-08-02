package com.ivanduri.interactions.commons;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import java.util.List;
import lombok.AllArgsConstructor;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

@AllArgsConstructor
public class PrintInvalidListStringsInSerenityReport implements Interaction {

  private final String type;
  private final List<String> invalidValues;

  public static PrintInvalidListStringsInSerenityReport accordingToTypeAndList(
      String type, List<String> invalidValues) {
    return instrumented(PrintInvalidListStringsInSerenityReport.class, type, invalidValues);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    for (String invalidValue : invalidValues) {
      Serenity.recordReportData().withTitle(type).andContents(invalidValue);
    }
  }
}
