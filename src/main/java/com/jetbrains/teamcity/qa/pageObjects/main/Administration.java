package com.jetbrains.teamcity.qa.pageObjects.main;

import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import com.jetbrains.teamcity.qa.pageObjects.project.CreateProjectMenu;
import com.jetbrains.teamcity.qa.pageObjects.project.EditProjectGeneralSettings;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Administration extends BasePage {

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

}
