package objects.http;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.methods.UserRequest;
import objects.resource.ServerApi;

import static io.restassured.RestAssured.given;

public class UserHTTP {

    private RequestSpecification baseRequest() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setRelaxedHTTPSValidation()
                .build();
    }

    protected RequestSpecification baseRequest(String contentType) {
        return new RequestSpecBuilder()
                .addHeader("Content-type", contentType)
                .addFilter(new AllureRestAssured())
                .setRelaxedHTTPSValidation()
                .build();
    }

    protected Response loginUser(String email, String password) {
        return given(this.baseRequest("application/json"))
                .body(new UserRequest(email, password))
                .when()
                .post(ServerApi.API_LOGIN);
    }

    protected Response deleteUser(String token) {
        return given(this.baseRequest())
                .auth().oauth2(token)
                .delete(ServerApi.API_DELETE_USER);
    }

}
