package com.jetbrains.teamcity.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import com.jetbrains.teamcity.qa.pageObjects.login.Login;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    @BeforeSuite(description = "SetUp")
    public void setUpConfiguration() {
        Configuration.baseUrl = "http://localhost:8111";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(true).includeSelenideSteps(true));
    }

    @BeforeMethod
    public void loginToTeamCity() {
        open("/");
        new Login().loginAs(null, "1689708622505232287");
    }

    @AfterSuite(description = "Teardown")
    public void tearDown() {
        new BasePage().goToAdministration().deleteAllProjects();
        SelenideLogger.removeListener("AllureSelenide");
    }

    public String createDefaultProject() {
        var createProjectMenu = new BasePage().goToAdministration().createProject();

        var createProjectFromUrlSetup =  createProjectMenu
                .checkTheForm()
                .fillRepositoryUrl("https://github.com/ikhromova/gradle-project-test")
                .fillUserName("test-integration-adventure")
                .fillToken("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")
                .submit();

        var autoDetectedBuildSteps = createProjectFromUrlSetup
                .checkTheForm()
                .submit();

        var buildSteps = autoDetectedBuildSteps
                .checkDiscoveredRunnersContain("Gradle")
                .selectAllSteps()
                .useSelected();
        buildSteps
                .subTitleShouldEqual("Build Steps")
                .successMessageIsShown();

        return buildSteps.getProjectId();
    }

//    public String domain() {
//        return Optional.ofNullable(System.getProperty("domain"))
//                .orElseThrow(() -> new AssertionError("Domain property doesn't set"));
//    }
//
//    public String token() {
//        return Optional.ofNullable(System.getProperty("token"))
//                .orElseThrow(() -> new AssertionError("Token property doesn't set"));
//    }
}
