package com.jetbrains.teamcity.qa.pageObjects.build;

import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Build extends BasePage {

    @Step
    public Build openOverviewTab() {
        $("#buildTypeStatusDiv_Tab").click();
        return this;
    }

    @Step
    public void clickFirstRunBtn() {
        $(".runFirstBuild").click();
    }

    @Step
    public void clickRunBtn() {
        $(".quickLinks .btn-group_run .btn").click();
    }

    @Step
    public Build runningStatusShouldEqual(String runningStatus) {
        $(".runningStatus").should(visible, timeoutForRunBuild).shouldBe(text(runningStatus));
        return this;
    }

    @Step
    public BuildResults openFirstBuildResults() {
        $("[title='Build number: 1']").click();
        return page(BuildResults.class);
    }



}
