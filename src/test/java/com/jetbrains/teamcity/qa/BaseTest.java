package com.jetbrains.teamcity.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import com.jetbrains.teamcity.qa.pageObjects.login.Login;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Optional;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    public String repositoryUrl = "https://github.com/ikhromova/gradle-project-test";
    public String githubUser = "test-integration-adventure";
    public String githubPassword = "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";
    public String buildTypeName = "Build";
    public String runner = "Gradle";

    @BeforeSuite(description = "SetUp")
    public void setUpConfiguration() {
        Configuration.baseUrl = "http://" + domain();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(true).includeSelenideSteps(true));
    }

    @BeforeMethod
    public void loginToTeamCity() {
        open("/");
        new Login().loginAs(null, token());
    }

    @AfterSuite(description = "Teardown")
    public void tearDown() {
        loginToTeamCity();
        new BasePage().goToAdministration().deleteAllProjects(domain());
        SelenideLogger.removeListener("AllureSelenide");
    }

    @Step
    public String createDefaultProject() {
        var createProjectFromUrlSetup = new BasePage().goToAdministration().createProject()
                .fillFormAndSubmit(repositoryUrl, githubUser, githubPassword);
        createProjectFromUrlSetup.setRandomProjectName();
        var autoDetectedBuildSteps = createProjectFromUrlSetup.checkTheForm().submit();
        var buildSteps = autoDetectedBuildSteps.selectAllSteps().useSelected().successMessageIsShown();
        return buildSteps.getProjectId();
    }

    public String domain() {
        return Optional.ofNullable(System.getProperty("domain"))
                .orElseThrow(() -> new AssertionError("Domain property doesn't set"));
    }

    public String token() {
        return Optional.ofNullable(System.getProperty("token"))
                .orElseThrow(() -> new AssertionError("Token property doesn't set"));
    }
}
