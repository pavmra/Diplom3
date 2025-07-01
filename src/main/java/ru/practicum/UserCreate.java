package ru.practicum;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserCreate {
    private static final String MAIN = "https://stellarburgers.nomoreparties.site";
    private static final String LOGIN = "/api/auth/login";
    private static final String REGISTER = "/api/auth/register";
    private static final String USER = "/api/auth/user";

    public Response register(UserModel user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(MAIN)
                .body(user)
                .when()
                .post(REGISTER);
    }

    public Response login(UserModel user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(MAIN)
                .body(user)
                .when()
                .post(LOGIN);
    }

    public Response delete(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .baseUri(MAIN)
                .when()
                .delete(USER);
    }

    public String getAccessToken(UserModel user) {
        return login(user)
                .then()
                .extract()
                .path("accessToken");
    }
}