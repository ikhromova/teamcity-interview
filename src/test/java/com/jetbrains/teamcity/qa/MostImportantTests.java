package com.jetbrains.teamcity.qa;

import com.jetbrains.teamcity.qa.pageObjects.CreateProject;
import com.jetbrains.teamcity.qa.pageObjects.CreateProjectFromUrlSetup;
import com.jetbrains.teamcity.qa.pageObjects.EditBuild;
import com.jetbrains.teamcity.qa.pageObjects.Login;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class MostImportantTests extends BaseTest {

    @Test(description = "Create project")
    public void createProject() {
        open("/");
        new Login().goToLoginAsSuperUser().loginWithToken("8600017554972913985").goToAdministration().createProject();

        CreateProject createProject = new CreateProject();
        createProject.fillRepositoryUrl("https://github.com/marcobehlerjetbrains/teamcity-gradle.git");

        CreateProjectFromUrlSetup projectSetup = createProject.submit();
        projectSetup
                .checkProjectNameIsFilled("Teamcity Gradle")
                .checkBuildTypeNameIsFilled("Build")
                .checkBranchIsFilled("refs/heads/master")
                .checkBranchSpecIsFilled("refs/heads/*")
                .submit();
        EditBuild editBuild = new EditBuild();
        editBuild
                .checkTitle()
                .checkDiscoveredRunnersContain("Gradle", "Command Line")
                .selectAllSteps()
                .useSelected();
    }
}
