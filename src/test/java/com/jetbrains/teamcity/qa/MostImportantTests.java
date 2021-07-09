package com.jetbrains.teamcity.qa;

import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import com.jetbrains.teamcity.qa.pageObjects.build.BuildResults;
import com.jetbrains.teamcity.qa.pageObjects.login.Login;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class MostImportantTests extends BaseTest {

    @Test(description = "Create project")
    public void createProject() {
        open("/");
        var createProjectMenu = new Login().loginAs(null, "8656453123873842931")
                .goToAdministration()
                .createProject();

       var createProjectFromUrlSetup =  createProjectMenu
                .checkTheForm()
                .fillRepositoryUrl("https://github.com/ikhromova/gradle-project-test")
                .fillUserName("test-integration-adventure")
                .fillToken("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")
                .submit();

       var projectName = createProjectFromUrlSetup.getProjectName();
       var autoDetectedBuildSteps = createProjectFromUrlSetup
                .checkTheForm()
                .checkProjectNameIsFilled("Gradle Project Test")
                .checkBuildTypeNameIsFilled("Build")
                .checkBranchIsFilled("refs/heads/master")
                .checkBranchSpecIsFilled("refs/heads/*")
                .submit();

        var buildSteps = autoDetectedBuildSteps
                .subTitleShouldEqual("Auto-detected Build Steps")
                .checkDiscoveredRunnersContain("Gradle")
                .selectAllSteps()
                .useSelected();

        buildSteps
                .subTitleShouldEqual("Build Steps")
                .successMessageIsShown()
                .buildStepsCountShouldEqual(1)
                .buildStepsShouldContain("Gradle")
                .vcsRootsCounterShouldBe(1)
//                .buildStepTabShouldBe("Build Step: Gradle")
                .buildTriggersCounterShouldBe(1);

        var projectId = buildSteps.getProjectId();

        var editProjectSettings = new BasePage().openProjectsTab().openProject(projectId).editProjectSettings();
        editProjectSettings.projectNameShouldBeEqual(projectName)
                .projectIdShouldBeEqual(projectId)
                .buildConfigurationNameShouldEqual("Build")
                .buildConfigurationStepsShouldEqual("Gradle");
        editProjectSettings.openVcsRoots().vcsRootShouldContain("https://github.com/ikhromova/gradle-project-test");
        editProjectSettings.openAdminActions().deleteProject();
    }

    @Test(description = "Run build")
    public void runBuild() {
        open("/");
        var createProjectMenu = new Login().loginAs(null, "8656453123873842931")
                .goToAdministration()
                .createProject();

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
                .subTitleShouldEqual("Auto-detected Build Steps")
                .checkDiscoveredRunnersContain("Gradle")
                .selectAllSteps()
                .useSelected();
        var projectId = buildSteps.getProjectId();
        buildSteps
                .subTitleShouldEqual("Build Steps")
                .successMessageIsShown()
                .buildStepsCountShouldEqual(1)
                .buildStepsShouldContain("Gradle")
                .vcsRootsCounterShouldBe(1)
//                .buildStepTabShouldBe("Build Step: Gradle")
                .buildTriggersCounterShouldBe(1);

        buildSteps.clickRunBtn();
        var buildInfo = new BuildResults();
        buildInfo
                .titleShouldContainTexts("#1")
//                .openChangesTab()
//                .vcsRootShouldContain("https://github.com/ikhromova/gradle-project-test")
//                .revisionBranchShouldContain("refs/heads/master")
                .passedTestBlockShouldEqual("1 test passed");

        new BasePage().openProjectsTab().openProject(projectId).editProjectSettings().openAdminActions().deleteProject();
    }

//    @Test(description = "Run build from vcs trigger")
//    public void runBuildFromVcsTrigger() {
//        open("/");
//        var createProjectMenu = new Login().loginAs(null, "3825374187092561042")
//                .goToAdministration()
//                .createProject();
//
//        var createProjectFromUrlSetup =  createProjectMenu
//                .checkTheForm()
//                .fillRepositoryUrl("https://github.com/ikhromova/gradle-project-test")
//                .fillUserName("test-integration-adventure")
//                .fillToken("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")
//                .submit();
//
//        var autoDetectedBuildSteps = createProjectFromUrlSetup
//                .checkTheForm()
//                .submit();
//
//        var buildSteps = autoDetectedBuildSteps
//                .subTitleShouldEqual("Auto-detected Build Steps")
//                .checkDiscoveredRunnersContain("Gradle")
//                .selectAllSteps()
//                .useSelected();
//        var projectId = buildSteps.getProjectId();
//        buildSteps
//                .subTitleShouldEqual("Build Steps")
//                .successMessageIsShown()
//                .buildStepsCountShouldEqual(1)
//                .buildStepsShouldContain("Gradle")
//                .vcsRootsCounterShouldBe(1)
////                .buildStepTabShouldBe("Build Step: Gradle")
//                .buildTriggersCounterShouldBe(1);
//
//        buildSteps.clickRunBtn();
//        new BuildResults()
//                .runningStatusShouldEqual("1 build running")
//                .passedTestBlockShouldEqual("1 test passed");
//
//        new BasePage().openProjectsTab().openProject(projectId).editProjectSettings().openAdminActions().deleteProject();
//    }

//    @Test(description = "Create project")
//    public void newtest() throws IOException {
//
//        var url = VcsUtils.createPullRequest();
//        sleep(10000);
//        VcsUtils.closePullRequest(url);
//    }
}
