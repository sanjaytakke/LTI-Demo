Feature: Login Test

@ETE1
Scenario: Verifying Last Update 
	Given User is on Home Page
	When User Navigate to SW Testing Page
	Then Verify Last Updated Date
	
	
@ETE1
Scenario: Verifying Test Cases
	Given User is on Home Page
	When User Navigate to Test Cases Page
	Then Verify Test Cases Page