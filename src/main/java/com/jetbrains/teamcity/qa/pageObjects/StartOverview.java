package com.jetbrains.teamcity.qa.pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StartOverview extends BasePage {
    private SelenideElement createProject = $(byText("Create project"));

    public void createProject() {
        createProject.click();
    }
}
