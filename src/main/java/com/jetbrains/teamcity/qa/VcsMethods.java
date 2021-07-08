package com.jetbrains.teamcity.qa;

import io.restassured.RestAssured;

public class VcsMethods {

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

    public static void closePullRequest(String url) {
        var response = RestAssured.given()
                .header("Accept", "application/vnd.github.v3+json")
                .header("Authorization", "Basic dGVzdC1pbnRlZ3JhdGlvbi1hZHZlbnR1cmU6Z2hwX1BoREhvMGhDNm5vNVk1VHROZGpGeE02SGszRlVnSDRUc0VYdg==")
                .header("Content-Type", "application/json")
                .body("{\"state\":\"closed\"}")
                .patch(url);
        response.then().statusCode(200);
    }
}
