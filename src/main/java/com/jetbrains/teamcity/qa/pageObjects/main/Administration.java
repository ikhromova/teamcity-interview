package com.jetbrains.teamcity.qa.pageObjects.main;

import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import com.jetbrains.teamcity.qa.pageObjects.project.CreateProjectMenu;
import com.jetbrains.teamcity.qa.pageObjects.project.EditProjectGeneralSettings;
import com.jetbrains.teamcity.qa.pageObjects.project.ProjectAdminActionsPopup;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class Administration extends BasePage {

    @Step("Open project = {projectId}")
    public EditProjectGeneralSettings openProject(String projectId) {
        $(".project_name a[href*='" + projectId + "']").click();
        return page(EditProjectGeneralSettings.class);
    }

    @Step("Create project")
    public CreateProjectMenu createProject() {
        $(".createProject > .btn").click();
        return page(CreateProjectMenu.class);
    }

    @Step("Delete all existing project")
    public void deleteAllProjects(String hostname) {
        this.openProject("=_Root");
        var count = $$("table #subprojects .edit .btn").size();
        for (int i = 0; i < count; i++) {
            $("table #subprojects .edit .btn").scrollTo().shouldBe(visible, longTimeout).click();
            new ProjectAdminActionsPopup().deleteProject(hostname);
        }
    }

}
