package com.jetbrains.teamcity.qa.pageObjects.build;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EditBuildConfiguration extends BasePage {
    private SelenideElement subTitle = $(byTagName("h2"));
    private SelenideElement useSelectedBtn = $("#discoveredRunners .btn.btn_hint");
    private ElementsCollection discoveredRunners = $$(byXpath("//*[@id=\"discoveredRunners\"]/table/tbody/*/td[2]"));
    private SelenideElement buildStep = $(".editBuildStepRow .stepName");
    private ElementsCollection discoveredRunnersCheckboxes = $$("#discoveredRunners [type=checkbox]");
    private SelenideElement generalSettingsTab = $("#general_Tab");
    private SelenideElement vcsRootsTab = $("#vcsRoots_Tab");
    private SelenideElement runTypeTab = $("#runType_Tab");
    private SelenideElement buildTriggersTab = $("#buildTriggers_Tab");
    private SelenideElement projects = $("#adminOverview_project_handle__RootContent .project_name a");

    @Step("getProjectId")
    public String getProjectId() {
        return $(".last").shouldBe(visible).attr("data-projectid");
    }

    public EditBuildConfiguration vcsRootsCounterShouldBe(Integer count) {
        $("#vcsRoots_Tab .tabCounter").shouldBe(text(count.toString()));
        return this;
    }

    public EditBuildConfiguration runTypeCounterShouldBe(Integer count) {
        $("#runType_Tab .tabCounter").shouldBe(text(count.toString()));
        return this;
    }

    public EditBuildConfiguration buildTriggersCounterShouldBe(Integer count) {
        $("#buildTriggers_Tab .tabCounter").shouldBe(text(count.toString()));
        return this;
    }

    public EditBuildConfiguration buildStepNameShouldBe(String buildStepName) {
        $(".editBuildStepRow .stepName").shouldBe(text(buildStepName));
        return this;
    }

    public void clickRunBtn() {
        $(".runFirstBuild").click();
    }

    public EditBuildConfiguration checkDiscoveredRunnersContain(String... steps) {
        discoveredRunners.shouldHave(CollectionCondition.texts(steps));
        return this;
    }

    public EditBuildConfiguration selectAllSteps() {
        discoveredRunnersCheckboxes.forEach(SelenideElement::click);
        return this;
    }

    public EditBuildConfiguration titleShouldEqual(String text) {
        subTitle.shouldBe(exactText(text));
        return this;
    }

    public void useSelected() {
        useSelectedBtn.click();
    }
}
