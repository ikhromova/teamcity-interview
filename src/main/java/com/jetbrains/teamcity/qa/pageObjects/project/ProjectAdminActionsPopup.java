package com.jetbrains.teamcity.qa.pageObjects.project;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProjectAdminActionsPopup {

    public void deleteProject(String hostname) {
        $(".quickLinksMenuPopup [title='Delete project']").scrollTo().shouldBe(visible).click();
        $(".hostnameConfirmation").val(hostname);
        $("[type=submit][value=Delete]").shouldBe(enabled).click();
        $("#message_projectRemoved").scrollTo().shouldBe(visible);
    }
}
