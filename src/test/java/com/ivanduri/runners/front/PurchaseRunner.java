package com.ivanduri.runners.front;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/challengeTecbeats/purchase.feature",
        glue = {"com/ivanduri/stepdefinitions"})
public class PurchaseRunner {
}
