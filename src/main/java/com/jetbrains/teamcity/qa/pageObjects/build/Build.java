package com.jetbrains.teamcity.qa.pageObjects.build;

import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Build extends BasePage {

    @Step
    public Build openOverviewTab() {
        $("#buildTypeStatusDiv_Tab").click();
        return this;
    }

    @Step
    public Build runningStatusShouldEqual(String runningStatus) {
        $(".runningStatus").should(visible, timeoutForRunBuild).shouldBe(text(runningStatus));
        return this;
    }

    @Step
    public void clickRunBtn() {
        $(".runFirstBuild").click();
    }



}
