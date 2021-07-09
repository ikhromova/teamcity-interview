package com.jetbrains.teamcity.qa.pageObjects.project;

import com.codeborne.selenide.SelenideElement;
import com.jetbrains.teamcity.qa.pageObjects.build.EditBuildConfiguration;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

// TODO Может нужно использовать интерфейсы вместо наследования?
public class EditProjectGeneralSettings extends EditProject {
    private SelenideElement buildConfigurations = $("#configurations");

    @Step
    public EditProjectGeneralSettings projectNameShouldBeEqual(String projectName) {
        $("#name").shouldHave(value(projectName));
        return this;
    }

    @Step
    public EditProjectGeneralSettings projectIdShouldBeEqual(String projectId) {
        $("#externalId").shouldHave(value(projectId));
        return this;
    }

    @Step
    public EditProjectGeneralSettings buildConfigurationNameShouldEqual(String buildConfigurationName) {
        buildConfigurations.$(".name.highlight").shouldHave(exactText(buildConfigurationName));
        return this;
    }

    @Step
    public EditProjectGeneralSettings buildConfigurationStepsShouldEqual(String buildConfigurationStep) {
        buildConfigurations.$(".runner.highlight").shouldHave(text(buildConfigurationStep));
        return this;
    }

    @Step
    public EditBuildConfiguration editBuildConfiguration() {
        $("#configurations .edit a").click();
        return page(EditBuildConfiguration.class);
    }
}
