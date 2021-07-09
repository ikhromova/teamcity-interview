package com.jetbrains.teamcity.qa;

import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import com.jetbrains.teamcity.qa.pageObjects.build.BuildResults;
import org.testng.annotations.Test;

public class MostImportantTests extends BaseTest {

    @Test(description = "Create project")
    public void createProject() {
        var createProjectMenu = new BasePage().goToAdministration().createProject();

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
       var projectId = createDefaultProject();

        new BasePage().openProjectsTab().openProject(projectId).openBuild().clickRunBtn();
        var buildInfo = new BuildResults();
        buildInfo
                .titleShouldContainTexts("#1")
//                .openChangesTab()
//                .vcsRootShouldContain("https://github.com/ikhromova/gradle-project-test")
//                .revisionBranchShouldContain("refs/heads/master")
                .passedTestBlockShouldEqual("1 test passed");

//        new BasePage().openProjectsTab().openProject(projectId).editProjectSettings().openAdminActions().deleteProject();
    }

    @Test(description = "Run build from vcs trigger")
    public void runBuildFromVcsTrigger() {
        var projectId = createDefaultProject();
        var generalSettings = new BasePage()
                .openProjectsTab()
                .openProject(projectId)
                .editProjectSettings().openVcsRoots().editVcsRoot()
                .editBranchSpec("+:refs/pull/*")
                .setCustomPollingInterval(2)
                .save().openGeneralSettings();
        generalSettings
                .editBuildConfiguration()
                .openBuildTriggersTab()
                .addVcsTrigger("+:*/merge");

        var url = VcsMethods.createPullRequest();

        new BasePage().openProjectsTab().openProject("GradleProjectTest").openBuild()
                .openOverviewTab().runningStatusShouldEqual("1 build running");

        VcsMethods.closePullRequest(url);
    }
}
