package stepDefinations;

import java.io.IOException;


import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeSenario() throws IOException {
		StepDefination mDefination = new StepDefination();
		if(StepDefination.place_id==null) {
			mDefination.add_place_payload_with("ahome", "Eng", "200 w washington st.");
			mDefination.user_calls_with_posr_http_request("addPlaceAPI", "Post");
			mDefination.verifyPlaceIdMapsToNameUsingGetPlaceAPI("ahome", "getPlaceAPI");
		}
	}
}
