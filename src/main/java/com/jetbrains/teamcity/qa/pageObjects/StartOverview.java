package com.jetbrains.teamcity.qa.pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class StartOverview {
    private SelenideElement createProject = $(byText("Create project"));
    private SelenideElement administrationHeaderTab = $(byAttribute("data-hint-container-id", "header-administration-link"));

    public void createProject() {
        createProject.click();
    }

    public Administration goToAdministration() {
        administrationHeaderTab.click();
        return page(Administration.class);

    }
}
