Feature: Validating Place API's

@AddPlace
Scenario Outline: verify if place is being succesdully added
	Given Add place payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "Post" http request
	Then the API call is got  success with status code 200
	And "status" in responce body is "OK"
	And "scope" in responce body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:
|	name	|		language		|		address			|
|	Ahouse	|		English			|	world class center	|
#  |	Bhouse	|		Spanish			|	Sea class center	|

@DeletePlace
Scenario: Verify if delete place functionality is working

	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "Post" http request
	Then the API call is got  success with status code 200
	And "status" in responce body is "OK"