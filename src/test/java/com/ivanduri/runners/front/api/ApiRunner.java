package com.ivanduri.runners.front.api;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/challengeTecbeats/",
        glue = {"com/ivanduri/stepdefinitions"},
        tags = "@run")
public class ApiRunner {
}
