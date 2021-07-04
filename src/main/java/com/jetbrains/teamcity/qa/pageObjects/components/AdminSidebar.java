package com.jetbrains.teamcity.qa.pageObjects.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AdminSidebar {
    private SelenideElement generalSettingsTab = $("#general_Tab");
    private SelenideElement vcsRootsTab = $("#vcsRoots_Tab");
    private SelenideElement runTypeTab = $("#runType_Tab");
    private SelenideElement buildTriggersTab = $("#buildTriggers_Tab");
    private SelenideElement projects = $("#adminOverview_project_handle__RootContent .project_name a");

    public AdminSidebar vcsRootsCounterShouldBe(Integer count) {
        $("#vcsRoots_Tab .tabCounter").shouldBe(Condition.exactValue(count.toString()));
        return this;
    }

    public AdminSidebar runTypeCounterShouldBe(Integer count) {
        $("#runType_Tab .tabCounter").shouldBe(Condition.exactValue(count.toString()));
        return this;
    }

    public AdminSidebar buildTriggersCounterShouldBe(Integer count) {
        $("#buildTriggers_Tab .tabCounter").shouldBe(Condition.exactValue(count.toString()));
        return this;
    }

}
