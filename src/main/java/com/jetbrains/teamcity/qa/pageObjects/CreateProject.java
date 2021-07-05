package com.jetbrains.teamcity.qa.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CreateProject {
    private SelenideElement repositoryUrl = $(byId("url"));
    private SelenideElement proceedBtn = $(byAttribute("type", "submit"));

    @Step("fillRepositoryUrl")
    public void fillRepositoryUrl(String url) {
        repositoryUrl.shouldBe(Condition.visible, Duration.ofSeconds(10));
        repositoryUrl.val(url).pressEnter();
    }

    @Step("submit")
    public CreateProjectFromUrlSetup submit() {
        proceedBtn.click();
        return page(CreateProjectFromUrlSetup.class);
    }
}
