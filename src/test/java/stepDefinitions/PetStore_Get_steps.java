package stepDefinitions;

import static io.restassured.RestAssured.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoClass.petStore.Pojo_PetStore_Post_Request;
import pojoClass.petStore.Pojo_PetStore_Valid_Response;
import utilities.BaseClass;
import java.io.IOException;

public class PetStore_Get_steps extends BaseClass {

	RequestSpecification getRequest;
	Response response;
	Pojo_PetStore_Post_Request postRequest;
	Pojo_PetStore_Valid_Response validResponseBody;

	@Given("get a pet order ID")
	public void get_a_pet_order_id() throws IOException {
		// RequestSpecification request = new
		// RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();
		getRequest = given().spec(requestSpecification());
	}

	@When("User calls GetPetOrderAPI {string} with valid order ID")
	public void user_calls_get_pet_order_api_with_valid_order_ID(String resources) {

		validResponseBody = new Pojo_PetStore_Valid_Response();
		int orderId = validResponseBody.getId();

		// getResponse = new
		// ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		response = getRequest.when().get(resources + orderId).then().spec(responseSpecification()).extract().response();
	}

	@When("User calls GetPetOrderAPI {string} with invalid order ID")
	public void user_calls_get_pet_order_api_with_invalid_order_ID(String resources) {

		Pojo_PetStore_Valid_Response postResponse = new Pojo_PetStore_Valid_Response();
		int orderId = postResponse.getId();

		// getResponse = new
		// ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(404).build();
		response = getRequest.when().get(resources + orderId + "9").then().spec(responseSpecification()).extract()
				.response();
	}

}
