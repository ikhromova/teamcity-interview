package com.jetbrains.teamcity.qa.pageObjects.project;

import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import com.jetbrains.teamcity.qa.pageObjects.main.Administration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProjectAdminActionsPopup {

    public Administration deleteProject() {
        $(".quickLinksMenuPopup [title='Delete project']").click();
        $(".hostnameConfirmation").val("localhost:8111");
        $("[type=submit][value=Delete]").shouldBe(enabled).click();
        $("#message_projectRemoved").shouldBe(visible);
        return new BasePage().goToAdministration();
    }
}
