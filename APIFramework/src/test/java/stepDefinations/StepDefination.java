package stepDefinations;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	private RequestSpecification res;
	public ResponseSpecification respec;
	private Response responce;
	public static String place_id;
	TestDataBuild  data = new TestDataBuild();
	 
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete action
		
		 res = given().spec(requestSpecification()).body(TestDataBuild.addPlacePayload(name, language, address));
	    
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_posr_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		ApiResources resourceApi = ApiResources.valueOf(resource);
		 respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 if(method.equalsIgnoreCase("POST")) {
			 responce = res.when().post(resourceApi.getResource());
		 }
		 else if(method.equalsIgnoreCase("GET")) {
			 
			 responce = res.when().get(resourceApi.getResource());
		 }
		 else if(method.equalsIgnoreCase("DELETE")) {
			 
			 responce = res.when().delete(resourceApi.getResource());
		 }
		 System.out.println(resourceApi.getResource());
					 
	}
	@Then("the API call is got  success with status code {int}")
	public void the_api_call_is_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
				assertEquals(responce.getStatusCode(), 200);
				//System.out.println(responce);
	  
	}
	@Then("{string} in responce body is {string}")
	public void in_responce_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
	   assertEquals(getJsonpath(responce, keyValue), expectedValue);
	}

	//request spec
		 @Then("verify place_Id created maps to {string} using {string}")
		    public void verifyPlaceIdMapsToNameUsingGetPlaceAPI(String expectedName, String resource ) throws IOException {

			 place_id= getJsonpath(responce, "place_id");
				res=given().spec(requestSpecification()).queryParam("place_id",place_id);
				user_calls_with_posr_http_request(resource,"GET");
				String actualName =getJsonpath(responce, "name");
				assertEquals(actualName, expectedName);
		 }
		 

		 @Given("DeletePlace Payload")
		 public void delete_place_payload() throws IOException {
		    res= given().spec(requestSpecification()).body(data.getPlaceData(place_id));
		    System.out.println();
		 }


}
