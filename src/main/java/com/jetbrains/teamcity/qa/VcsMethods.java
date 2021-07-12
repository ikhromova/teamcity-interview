package com.jetbrains.teamcity.qa;

import io.qameta.allure.Step;
import io.restassured.RestAssured;

public class VcsMethods {

    @Step("Get pull requests from Github")
    public static String getPullRequest() {
        var response = RestAssured.given()
                .header("Accept", "application/vnd.github.v3+json")
                .header("Authorization", "Basic dGVzdC1pbnRlZ3JhdGlvbi1hZHZlbnR1cmU6Z2hwX1BoREhvMGhDNm5vNVk1VHROZGpGeE02SGszRlVnSDRUc0VYdg==")
                .get("https://api.github.com/repos/ikhromova/gradle-project-test/pulls");
        response.then().statusCode(200);
        return response.then().extract().path("[0].url");
    }

    @Step("Create pull request to Github")
    public static String createPullRequest() {
        var response = RestAssured.given()
                .header("Accept", "application/vnd.github.v3+json")
                .header("Authorization", "Basic dGVzdC1pbnRlZ3JhdGlvbi1hZHZlbnR1cmU6Z2hwX1BoREhvMGhDNm5vNVk1VHROZGpGeE02SGszRlVnSDRUc0VYdg==")
                .header("Content-Type", "application/json")
                .body("{\"head\":\"test-branch\",\"base\":\"master\", \"title\":\"Create pull request\"}")
                .post("https://api.github.com/repos/ikhromova/gradle-project-test/pulls");
        response.then().statusCode(201);
        return response.then().extract().path("url");
    }

    @Step("Close pull request")
    public static void closePullRequest(String url) {
        var response = RestAssured.given()
                .header("Accept", "application/vnd.github.v3+json")
                .header("Authorization", "Basic dGVzdC1pbnRlZ3JhdGlvbi1hZHZlbnR1cmU6Z2hwX1BoREhvMGhDNm5vNVk1VHROZGpGeE02SGszRlVnSDRUc0VYdg==")
                .header("Content-Type", "application/json")
                .body("{\"state\":\"closed\"}")
                .patch(url);
        response.then().statusCode(200);
    }

    @Step("Close previous pull requests from Github")
    public static void closePreviousPullRequest() {
        var pr = getPullRequest();
        if (pr != null) {
            closePullRequest(pr);
        }
    }
}
