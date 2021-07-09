package com.jetbrains.teamcity.qa.pageObjects.build;

import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class BuildResults extends BasePage {

    @Step
    public BuildResults passedTestBlockShouldEqual(String passedTextBlock) {
        $(".passedTestsBlock .passCount").should(appear, timeoutForRunBuild).shouldBe(text(passedTextBlock));
        return this;
    }

    @Step
    public BuildResults runningStatusShouldEqual(String runningStatus) {
        $(".runningStatus").should(visible, longTimeout).shouldBe(text(runningStatus));
        return this;
    }

    @Step("Check page title equal to \"{titleText}\"")
    public BuildResults titleShouldContainTexts(String titleText) {
        $("#restPageTitle").shouldBe(visible, longTimeout).shouldHave(text(titleText));
        return this;
    }

    @Step
    public BuildResults openChangesTab() {
        $("#buildChangesDiv_Tab").click();
        return this;
    }

    @Step
    public BuildResults openResultsTab() {
        $("#buildResultsDiv_Tab").click();
        return this;
    }

    @Step
    public BuildResults vcsRootShouldContain(String text) {
        $("td.vcsRoot").shouldHave(text(text));
        return this;
    }

    @Step
    public BuildResults revisionBranchShouldContain(String text) {
        $(".revision.branch ").shouldHave(text(text));
        return this;
    }

    @Step
    public Build goToBuild() {
        $(".last.buildType a").click();
        return page(Build.class);
    }


}
