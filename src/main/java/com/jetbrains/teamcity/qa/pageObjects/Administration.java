package com.jetbrains.teamcity.qa.pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Administration extends BasePage {
    private SelenideElement createProjectBtn = $(".createProject > .btn");
    private SelenideElement projects = $("#adminOverview_project_handle__RootContent .project_name a");

//    TODO А если несколько проектов в похожими названиями
    public EditProject openProject(String projectId) {
        $(".project_name a[href*='" + projectId + "']").click();
        return page(EditProject.class);
    }

    public CreateProjectFromUrlSetup createProject() {
        createProjectBtn.click();
        return page(CreateProjectFromUrlSetup.class);
    }

}
