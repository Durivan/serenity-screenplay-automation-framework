package com.ivanduri.runners.api;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/examples/",
        glue = {"com/ivanduri/stepdefinitions"},
        tags = "@Smoke")
public class ApiRunner {
}
