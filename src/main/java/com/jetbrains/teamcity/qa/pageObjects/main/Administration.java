package com.jetbrains.teamcity.qa.pageObjects.main;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import com.jetbrains.teamcity.qa.pageObjects.project.CreateProjectMenu;
import com.jetbrains.teamcity.qa.pageObjects.project.EditProject;
import com.jetbrains.teamcity.qa.pageObjects.project.EditProjectGeneralSettings;
import com.jetbrains.teamcity.qa.pageObjects.project.ProjectAdminActionsPopup;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class Administration extends BasePage {
    private ElementsCollection projects = $$(".project_content_holder .project_name a");

//    TODO А если несколько проектов в похожими названиями
    public EditProjectGeneralSettings openProject(String projectId) {
        $(".project_name a[href*='" + projectId + "']").click();
        return page(EditProjectGeneralSettings.class);
    }

    @Step
    public CreateProjectMenu createProject() {
        $(".createProject > .btn").click();
        return page(CreateProjectMenu.class);
    }

    @Step
    public EditProject open(SelenideElement project) {
        project.click();
        return page(EditProject.class);
    }

    @Step
    public void deleteAllProjects() {
        this.openProject("=_Root");
        var count = $$("table #subprojects .edit .btn").size();
        for (int i = 0; i < count; i++) {
            $("table #subprojects .edit .btn").shouldBe(visible).click();
            new ProjectAdminActionsPopup().deleteProject();
        }
    }

}
