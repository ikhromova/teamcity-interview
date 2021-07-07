package com.jetbrains.teamcity.qa.pageObjects.build;

import com.codeborne.selenide.Condition;
import com.jetbrains.teamcity.qa.pageObjects.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class BuildResults extends BasePage {

    public BuildResults passedTestBlockShouldEqual(String passedTextBlock) {
        $(".passedTestsBlock .passCount").should(Condition.appear, Duration.ofSeconds(100)).shouldBe(text(passedTextBlock));
        return this;
    }


}
