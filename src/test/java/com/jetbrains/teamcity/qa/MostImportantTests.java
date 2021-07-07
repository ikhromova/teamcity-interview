package com.jetbrains.teamcity.qa;

import com.jetbrains.teamcity.qa.pageObjects.login.Login;
import com.jetbrains.teamcity.qa.pageObjects.main.StartOverview;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class MostImportantTests extends BaseTest {

    @Test(description = "Create project")
    public void createProject() {
        open("/");
        new Login().goToLoginAsSuperUser().loginWithToken("8600017554972913985").goToAdministration();
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
        new StartOverview().goToAdministration().openProject("TeamcityGradle4").openAdminActions().deleteProject();
//        editBuildConfiguration.clickRunBtn();
//        new BuildResults().passedTestBlockShouldEqual("1 test passed");
    }

//    @Test(description = "Create project")
//    public void newtest() throws IOException {
//
//        var url = VcsUtils.createPullRequest();
//        sleep(10000);
//        VcsUtils.closePullRequest(url);
//    }
}
