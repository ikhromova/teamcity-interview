package com.jetbrains.teamcity.qa.pageObjects;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProjectAdminActionsPopup {

    public void deleteProject() {
        $(".quickLinksMenuPopup [title='Delete project']").click();
        $(".hostnameConfirmation").val("localhost:8111");
        $("[type=submit][value=Delete]").shouldBe(enabled).click();
        $("#message_projectRemoved").shouldBe(visible);
    }
}
