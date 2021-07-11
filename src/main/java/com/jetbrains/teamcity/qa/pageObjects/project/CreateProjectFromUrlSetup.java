package com.jetbrains.teamcity.qa.pageObjects.project;

import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import com.jetbrains.teamcity.qa.pageObjects.build.AutoDetectedBuildSteps;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class CreateProjectFromUrlSetup extends BasePage {

    @Step
    public String setRandomProjectName() {
        var projectName = randomAlphabetic(10);
        $("#projectName").val(projectName);
        return projectName;
    }

    @Step
    public CreateProjectFromUrlSetup checkTheForm() {
        titleShouldContainText("Create Project From URL");
        $(".connectionSuccessful").shouldHave(text("The connection to the VCS repository has been verified"));
        return this;
    }

    @Step
    public CreateProjectFromUrlSetup checkProjectNameIsFilled(String projectName) {
        $("#projectName").shouldHave(value(projectName));
        return this;
    }

    @Step
    public CreateProjectFromUrlSetup checkBuildTypeNameIsFilled(String buildTypeName) {
        $("#buildTypeName").shouldHave(value(buildTypeName));
        return this;
    }

    @Step
    public CreateProjectFromUrlSetup checkBranchIsFilled(String branch) {
        $("#branch").shouldHave(value(branch));
        return this;
    }

    @Step
    public CreateProjectFromUrlSetup checkBranchSpecIsFilled(String branchSpec) {
        $("#teamcity\\:branchSpec").shouldHave(value(branchSpec));
        return this;
    }

    @Step
    public AutoDetectedBuildSteps submit() {
        $("[name='createProject']").click();
        return page(AutoDetectedBuildSteps.class);
    }
}
