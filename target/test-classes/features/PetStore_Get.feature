Feature: Pet store Get scenarios

Scenario: Retrive pet order with valid input data
	Given get a pet order ID
	When User calls GetPetOrderAPI "/store/order/:" with valid order ID
	Then validate the API status code 200
	And validate the "GET" response message

Scenario: Retrive pet order with invalid input data
	Given get a pet order ID
	When User calls GetPetOrderAPI "/store/order/:" with invalid order ID
	Then validate the API status code 404
	And validate the "GET" response message