Feature: FirstSpecFlowTest

Scenario: SF_Login as a regular user
	Given as a user I'm on the Polteq WebShop home page
	When I log in as a user
	Then I should be greeted as a regular user

Scenario: SF_Login as a tester user
	Given as a user I'm on the Polteq WebShop home page
	When I log in as a "tester@test.com" user
	Then I should be greeted as a regular user
