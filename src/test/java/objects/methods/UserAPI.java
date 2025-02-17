package objects.methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import objects.http.UserHTTP;
import objects.resource.ServerApi;
import objects.methods.UserRequest;
import objects.methods.UserResponse;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class UserAPI extends UserHTTP {

    //    --- создание нового тестового пользователя ---
    @Step("Creating a test user")
    public void createUser(String name, String email, String password) {
        Response response = given(this.baseRequest("application/json"))
                .body(new UserRequest(email, password, name))
                .when()
                .post(ServerApi.API_REGISTER_USER);

        response.getStatusCode();
    }

    //    --- создание тестового пользователя ---
    @Step("Deleting a user")
    public void deleteUser(String email, String password) {
        Response response = loginUser(email, password);

        if(response.getStatusCode() != SC_OK) return;

        String token = response.body().as(UserResponse.class).getAccessToken().split(" ")[1];
        deleteUser(token);
    }

}
