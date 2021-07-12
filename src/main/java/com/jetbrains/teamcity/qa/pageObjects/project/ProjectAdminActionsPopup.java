package com.jetbrains.teamcity.qa.pageObjects.project;

import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProjectAdminActionsPopup {

    @Step("Delete project")
    public void deleteProject(String hostname) {
        $(".quickLinksMenuPopup [title='Delete project']").scrollTo().shouldBe(visible).click();
        $(".hostnameConfirmation").scrollTo().shouldBe(visible).val(hostname);
        $("[type=submit][value=Delete]").scrollTo().shouldBe(visible, enabled).click();
        $("#message_projectRemoved").scrollTo().shouldBe(visible, Duration.ofSeconds(15));
    }
}
