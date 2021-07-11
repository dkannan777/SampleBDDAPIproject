Feature: Pet store Post scenarios

Scenario: Place an order for a pet with valid input data
	Given placing a valid pet order
	When User calls PostPetOrderAPI with "/store/order" post HTTP method
	Then validate the API status code 200
	And validate the "POST" response message

Scenario: Place an order for a pet with invalid input data
	Given placing a invalid pet order
	When User calls PostPetOrderAPI with "/store/order" post HTTP method
	Then validate the API status code 400
	And validate the "POST" response message
