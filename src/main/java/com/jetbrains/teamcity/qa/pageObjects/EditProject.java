package com.jetbrains.teamcity.qa.pageObjects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class EditProject extends BasePage {


    public ProjectAdminActionsPopup openAdminActions() {
        $("[data-hint-container-id='project-admin-actions'] .btn").click();
        return page(ProjectAdminActionsPopup.class);
    }

}
