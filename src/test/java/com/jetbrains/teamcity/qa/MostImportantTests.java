package com.jetbrains.teamcity.qa;

import com.jetbrains.teamcity.qa.pageObjects.BasePage;
import com.jetbrains.teamcity.qa.pageObjects.login.Login;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class MostImportantTests extends BaseTest {

    @Test(description = "Create project")
    public void createProject() {
        open("/");
        var createProjectMenu = new Login().loginAs(null, "8227625318384614588")
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

//    @Test(description = "Create project")
//    public void createProject() {
//        open("/");
//        new Login().goToLoginAsSuperUser().loginWithToken("8227625318384614588").goToAdministration();
//                .createProject();

//        CreateProject createProject = new CreateProject();
//        createProject.fillRepositoryUrl("https://github.com/marcobehlerjetbrains/teamcity-gradle.git");
//
//        CreateProjectFromUrlSetup projectSetup = createProject.submit();
//        var projectName = projectSetup.getProjectName();
//        projectSetup
//                .checkProjectNameIsFilled("Teamcity Gradle")
//                .checkBuildTypeNameIsFilled("Build")
//                .checkBranchIsFilled("refs/heads/master")
//                .checkBranchSpecIsFilled("refs/heads/*")
//                .submit();
//        EditBuildConfiguration editBuildConfiguration = new EditBuildConfiguration();
//        editBuildConfiguration
//                .titleShouldEqual("Auto-detected Build Steps")
//                .checkDiscoveredRunnersContain("Gradle")
//                .selectAllSteps()
//                .useSelected();
//        editBuildConfiguration
//                .titleShouldEqual("Build steps")
//                .buildStepNameShouldBe("Gradle")
//                .vcsRootsCounterShouldBe(1)
////                .runTypeCounterShouldBe(1)
//                .buildTriggersCounterShouldBe(1);
//        new StartOverview().goToAdministration().openProject("TeamcityGradle4").openAdminActions().deleteProject();
//        editBuildConfiguration.clickRunBtn();
//        new BuildResults().passedTestBlockShouldEqual("1 test passed");
//    }

//    @Test(description = "Create project")
//    public void newtest() throws IOException {
//
//        var url = VcsUtils.createPullRequest();
//        sleep(10000);
//        VcsUtils.closePullRequest(url);
//    }
}
