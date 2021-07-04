package com.jetbrains.teamcity.qa.pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Administration {
    private SelenideElement createProjectBtn = $(".createProject > .btn");
    private SelenideElement projects = $("#adminOverview_project_handle__RootContent .project_name a");

    public CreateProjectFromUrlSetup createProject() {
        createProjectBtn.click();
        return page(CreateProjectFromUrlSetup.class);
    }

}
