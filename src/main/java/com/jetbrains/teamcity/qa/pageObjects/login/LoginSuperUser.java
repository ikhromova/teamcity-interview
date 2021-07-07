package com.jetbrains.teamcity.qa.pageObjects.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.jetbrains.teamcity.qa.pageObjects.main.StartOverview;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class LoginSuperUser {
    private SelenideElement passwordField = $(byId("password"));
    private SelenideElement submitBtn = $(byName("submitLogin"));

    @Step("Login with token)")
    public StartOverview loginWithToken(String token) {
        fillToken(token);
        $(byName("submitLogin")).shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        passwordField.should(Condition.disappear);
        return Selenide.page(StartOverview.class);
    }

    @Step
    private void fillToken(String token) {
        passwordField.val(token).pressEnter();
    }


}
