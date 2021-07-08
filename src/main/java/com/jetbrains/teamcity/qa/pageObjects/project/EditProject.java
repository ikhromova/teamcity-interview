package com.jetbrains.teamcity.qa.pageObjects.project;

import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class EditProject extends BasePage {

    @Step
    public ProjectAdminActionsPopup openAdminActions() {
        $("[data-hint-container-id='project-admin-actions'] .btn").click();
        return page(ProjectAdminActionsPopup.class);
    }

    @Step
    public EditProjectGeneralSettings openGeneralSettings() {
        $("#projectGeneralTab_Tab").click();
        return page(EditProjectGeneralSettings.class);
    }

    @Step
    public EditProjectVcsRoots openVcsRoots() {
        $("#projectVcsRoots_Tab").click();
        return page(EditProjectVcsRoots.class);
    }

}
