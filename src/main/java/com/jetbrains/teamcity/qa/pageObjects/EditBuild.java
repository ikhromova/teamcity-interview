package com.jetbrains.teamcity.qa.pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EditBuild {
    private SelenideElement subTitle = $(byTagName("h2"));
    private SelenideElement useSelectedBtn = $("#discoveredRunners .btn.btn_hint");
    private ElementsCollection discoveredRunners = $$(byXpath("//*[@id=\"discoveredRunners\"]/table/tbody/*/td[2]"));
    private ElementsCollection discoveredRunnersCheckboxes = $$("#discoveredRunners [type=checkbox]");

    public EditBuild checkDiscoveredRunnersContain(String... steps) {
        discoveredRunners.shouldHave(CollectionCondition.texts(steps));
        return this;
    }

    public EditBuild selectAllSteps() {
        discoveredRunnersCheckboxes.forEach(SelenideElement::click);
        return this;
    }


    public EditBuild checkTitle() {
        subTitle.shouldBe(exactText("Auto-detected Build Steps"));
        return this;
    }

    public void useSelected() {
        useSelectedBtn.click();
    }
}
