package com.jetbrains.teamcity.qa.pageObjects.build;

import com.codeborne.selenide.SelenideElement;
import com.jetbrains.teamcity.qa.pageObjects.build.components.Triggers;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class EditBuildConfiguration<T extends EditBuildConfiguration<T>> extends Build {
    private SelenideElement generalTab = $("#general_Tab");
    private SelenideElement vcsRootsTab = $("#vcsRoots_Tab");
    private SelenideElement runTypeTab = $("#runType_Tab");
    private SelenideElement triggersTab = $("#buildTriggers_Tab");
    private SelenideElement counter = $(".tabCounter");


    @Step
    public String getProjectId() {
        return $(".last").shouldBe(visible).attr("data-projectid");
    }

    @Step
    public EditBuildConfiguration vcsRootsCounterShouldBe(Integer count) {
        $("#vcsRoots_Tab .tabCounter").shouldBe(text(count.toString()));
        return this;
    }

    @Step
    public EditBuildConfiguration buildStepsCounterShouldBe(Integer count) {
        $("#runType_Tab .tabCounter").shouldBe(text(count.toString()));
        return this;
    }

    @Step
    public EditBuildConfiguration buildStepTabShouldBe(String stepName) {
        $("#runType_Tab a").shouldHave(text(stepName));
        return this;
    }

    @Step
    public EditBuildConfiguration buildTriggersCounterShouldBe(Integer count) {
        $("#buildTriggers_Tab .tabCounter").shouldBe(text(count.toString()));
        return this;
    }

    @Step
    public Triggers openBuildTriggersTab() {
        triggersTab.click();
        return page(Triggers.class);
    }

    @Step
    public T subTitleShouldEqual(String text) {
        $(byTagName("h2")).shouldBe(exactText(text));
        return (T) this;
    }
}
