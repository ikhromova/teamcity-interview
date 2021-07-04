package com.jetbrains.teamcity.qa.pageObjects;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class CreateProjectFromUrlSetup {
    private SelenideElement projectNameField = $(byId("projectName"));
    private SelenideElement buildTypeNameField = $(byId("buildTypeName"));
    private SelenideElement branchField = $(byId("branch"));
    private SelenideElement branchSpecField = $(byId("teamcity:branchSpec"));
    private SelenideElement submitBtn = $(byName("createProject"));

    public CreateProjectFromUrlSetup checkProjectNameIsFilled(String projectName) {
        projectNameField.shouldHave(value(projectName), Duration.ofSeconds(10));
        return this;
    }

    public CreateProjectFromUrlSetup checkBuildTypeNameIsFilled(String buildTypeName) {
        buildTypeNameField.shouldHave(value(buildTypeName));
        return this;
    }

    public CreateProjectFromUrlSetup checkBranchIsFilled(String branch) {
        branchField.shouldHave(value(branch));
        return this;
    }

    public CreateProjectFromUrlSetup checkBranchSpecIsFilled(String branchSpec) {
        branchSpecField.shouldHave(value(branchSpec));
        return this;
    }

    public void submit() {
        submitBtn.click();
    }
}
