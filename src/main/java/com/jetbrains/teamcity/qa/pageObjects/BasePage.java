package com.jetbrains.teamcity.qa.pageObjects;

import com.codeborne.selenide.Condition;
import com.jetbrains.teamcity.qa.pageObjects.main.Administration;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class BasePage {

    @Step("goToAdministration")
    public Administration goToAdministration() {
        $(byAttribute("data-hint-container-id", "header-administration-link")).shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        return page(Administration.class);
    }
}
