Feature: Make My Trip Home Page

@Train
Scenario: Verifying Train Availability
	Given User is on home page
	When User navigate to train info page
	Then Verify default stations name
	
	
@Hotels @Train1
Scenario: Verifying Hotels Availability
	Given User is on home page
	When User Navigate to hotels page
	Then Verify default selected city