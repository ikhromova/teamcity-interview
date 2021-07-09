package com.jetbrains.teamcity.qa.pageObjects.project;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class EditVcsRoots extends EditProjectVcsRoots {
    @Step
    public EditVcsRoots setCustomPollingInterval(Integer seconds) {
        $(".advancedSettingsToggle a").scrollTo().click();
        $("#mod-check-interval-specified").scrollTo().selectRadio("SPECIFIED");
        $("#modificationCheckInterval").val(seconds.toString());
        return this;
    }

    @Step
    public EditVcsRoots editBranchSpec(String value) {
        $("#teamcity\\:branchSpec").val(value);
        return this;
    }

    @Step
    public EditProjectVcsRoots save() {
        $(".saveButtonsBlock .submitButton").click();
        return page(EditProjectVcsRoots.class);
    }

}
