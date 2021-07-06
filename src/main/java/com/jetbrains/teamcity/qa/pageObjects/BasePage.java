package com.jetbrains.teamcity.qa.pageObjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class BasePage {

    @Step("goToAdministration")
    public Administration goToAdministration() {
        $(byAttribute("data-hint-container-id", "header-administration-link")).click();
        return page(Administration.class);
    }
}
