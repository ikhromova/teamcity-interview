package com.jetbrains.teamcity.qa.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginSuperUser {
    private SelenideElement passwordField = $(byId("password"));
    private SelenideElement submitBtn = $(byName("submitLogin"));

    @Step
    public StartOverview loginWithToken(String token) {
        fillToken(token);
        submit();
        passwordField.should(Condition.disappear);
        return page(StartOverview.class);
    }

    @Step
    private void fillToken(String token) {
        passwordField.val(token).pressEnter();
    }

    @Step
    private void submit() {
        submitBtn.shouldBe(Condition.visible).click();
    }
}
