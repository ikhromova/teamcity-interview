package com.jetbrains.teamcity.qa.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byPartialLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Login {
    private SelenideElement usernameField = $(byId("username"));
    private SelenideElement passwordField = $(byId("password"));
    private SelenideElement submitBtn = $(byName("submitLogin"));
    private SelenideElement loginAsSuperUser = $(byPartialLinkText("Super user"));

    @Step
    public void loginAs(String username, String password) {
        fillUsername(username);
        fillPassword(password);
        submit();
    }

    @Step
    private void fillUsername(String userName) {
        usernameField.val(userName).pressEnter();
    }

    @Step
    private void fillPassword(String password) {
        passwordField.val(password).pressEnter();
    }

    @Step
    private void submit() {
        submitBtn.click();
    }

    @Step("goToLoginAsSuperUser")
    public LoginSuperUser goToLoginAsSuperUser() {
        loginAsSuperUser.click();
        usernameField.should(Condition.disappear);
        return page(LoginSuperUser.class);
    }

}
