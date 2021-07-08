package com.jetbrains.teamcity.qa.pageObjects.project;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class EditProjectVcsRoots extends EditProject {

    @Step
    public EditProjectVcsRoots vcsRootShouldContain(String url) {
        $(".vcsRoot").shouldHave(text(url));
        return this;
    }

}
