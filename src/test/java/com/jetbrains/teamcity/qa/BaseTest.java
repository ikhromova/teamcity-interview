package com.jetbrains.teamcity.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite(description = "Set up configuration")
    public void setUpConfiguration() {
        Configuration.baseUrl = "http://localhost:8111";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(true));
    }

    @AfterSuite(description = "Teardown")
    public void tearDown() {
        SelenideLogger.removeListener("AllureSelenide");
    }
}
