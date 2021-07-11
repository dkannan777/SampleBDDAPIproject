package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoClass.petStore.Pojo_PetStore_InvalidResponse;
import pojoClass.petStore.Pojo_PetStore_Valid_Response;
import pojoClass.petStore.Pojo_PostReqBody;
import utilities.BaseClass;
import static io.restassured.RestAssured.*;

public class PetStore_Post_steps extends BaseClass {

	RequestSpecification postRequest;
	Response response;
	Pojo_PostReqBody postReqBody = new Pojo_PostReqBody();
	Pojo_PetStore_Valid_Response validResponseBody;
	Pojo_PetStore_InvalidResponse invalidResponseBody;
	int actualStatusCode;

	@Given("placing a valid pet order")
	public void postValidPetOrder() throws IOException {

		postRequest = given().spec(requestSpecification()).body(postReqBody.postValidRequest());

	}

	@When("User calls PostPetOrderAPI with {string} post HTTP method")
	public void receivePetOrderResponse(String postResources) {

		response = postRequest.when().post(postResources).then().spec(responseSpecification()).extract().response();

	}

	@Then("validate the API status code {int}")
	public void validatePetOrderHttpStatusCode(int expectedStatusCode) {

		actualStatusCode = response.getStatusCode();
		assertEquals(expectedStatusCode, actualStatusCode);
		System.out.println("Statuscode received as 200");
	}

	@And("validate the {string} response message")
	public void validatePetOrderPostResponse(String httpdMethod) {

		validResponseBody = response.as(Pojo_PetStore_Valid_Response.class);

		if (httpdMethod.equalsIgnoreCase("POST") || httpdMethod.equalsIgnoreCase("GET")) {
			if (actualStatusCode == 200) {
				assertEquals(postReqBody.postValidRequest().getId(), validResponseBody.getId());
				assertEquals(postReqBody.postValidRequest().getPetId(), validResponseBody.getPetId());
				assertEquals(postReqBody.postValidRequest().getQuantity(), validResponseBody.getQuantity());
				assertEquals(postReqBody.postValidRequest().getStatus(), validResponseBody.getStatus());
				assertEquals(postReqBody.postValidRequest().isComplete(), validResponseBody.isComplete());

				System.out.println("Pet Store Post HTTP request is successful");
				
			} else {

				invalidResponseBody = response.as(Pojo_PetStore_InvalidResponse.class);

				assertEquals(400, invalidResponseBody.getCode());
				assertEquals("unknown", invalidResponseBody.getType());
				assertEquals("bad input", invalidResponseBody.getMessage());

				System.out.println("Pet Store " + httpdMethod + "request is unsuccessful");

			}

		}
	}

	@Given("placing a invalid pet order")
	public void postInvalidPetOrder() throws IOException {

		postReqBody = new Pojo_PostReqBody();

		postRequest = given().spec(requestSpecification())
				.body("{\r\n" + "    \"id\": 34A33,\r\n" + "    \"petId\": 408584,\r\n" + "    \"quantity\": 47754,\r\n"
						+ "    \"shipDate\": \"2000-08-20T11:56:30.604Z\",\r\n" + "    \"status\": \"placed\",\r\n"
						+ "    \"complete\": true\r\n" + "}");

	}
	
	

}
