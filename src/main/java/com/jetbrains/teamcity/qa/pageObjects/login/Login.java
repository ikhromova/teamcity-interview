package com.jetbrains.teamcity.qa.pageObjects.login;

import com.codeborne.selenide.SelenideElement;
import com.jetbrains.teamcity.qa.pageObjects.main.Projects;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byPartialLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Login {
    private SelenideElement loginAsSuperUser = $(byPartialLinkText("Super user"));

    @Step
    public Projects loginAs(String userName, String password) {
        $("#username").val(userName);
        $("#password").val(password);
        $("[name='submitLogin']").click();
        return page(Projects.class);
    }

    @Step("goToLoginAsSuperUser")
    public LoginSuperUser goToLoginAsSuperUser() {
        loginAsSuperUser.click();
        return page(LoginSuperUser.class);
    }

}
