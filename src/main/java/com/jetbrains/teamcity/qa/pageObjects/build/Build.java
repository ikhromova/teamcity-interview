package com.jetbrains.teamcity.qa.pageObjects.build;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Build extends EditBuildConfiguration<Build> {
    private ElementsCollection stepNames = $$(".stepName .stepName");

    @Step
    public Build buildStepsShouldContain(String... buildStepName) {
        stepNames.should(containExactTextsCaseSensitive(buildStepName));
        return this;
    }

    @Step
    public Build buildStepsCountShouldEqual(int count) {
       stepNames.shouldHave(sizeGreaterThanOrEqual(count));
       return this;
    }

    @Step
    public Build successMessageIsShown() {
//       $("#unprocessed_buildRunnerSettingsUpdated").shouldHave(text("New build step added."));
       $("#unprocessed_buildRunnerSettingsUpdated").should(exist);
       return this;
    }





}
