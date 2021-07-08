package com.jetbrains.teamcity.qa.pageObjects.build;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class AutoDetectedBuildSteps extends EditBuildConfiguration<AutoDetectedBuildSteps> {
    private SelenideElement discoveredRunners = $("#discoveredRunners");

    public AutoDetectedBuildSteps buildStepNameShouldBe(String buildStepName) {
        $(".editBuildStepRow .stepName").shouldBe(text(buildStepName));
        return this;
    }

//    TODO Поменять страшный xpath
    @Step
    public AutoDetectedBuildSteps checkDiscoveredRunnersContain(String buildSteps) {
        $$(byXpath("//*[@id=\"discoveredRunners\"]/table/tbody/*/td[2]")).findBy(text(buildSteps)).should(exist);
        return this;
    }

    @Step
    public AutoDetectedBuildSteps selectAllSteps() {
        discoveredRunners.$$("[type=checkbox]").forEach(SelenideElement::click);
        return this;
    }

    @Step
    public BuildSteps useSelected() {
        discoveredRunners.$(".btn.btn_hint").click();
        return page(BuildSteps.class);
    }
}
