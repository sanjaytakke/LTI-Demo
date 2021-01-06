Feature: Login page feature

@LoginTitle @ETE_1
Scenario: Login page title
Given User is on home page
When user gets the title of the page
Then page title should be "Login - My Store"

@ForgetPwdLink @ETE_1
Scenario: Forgot Password link
Given User is on home page
Then forgot your password link should be displayed

@CorrectLogin @ETE_1
Scenario Outline: Login with correct credentials
Given User is on home page
When user enters username "<EmailID>"
And user enters password "<Password>"
And user clicks on Login button
Then user gets the title of the page
And page title should be "<PageTitle>"

Examples:
|EmailID|Password|PageTitle|
|dec2020secondbatch@gmail.com|Selenium@12345|My account - My Store|
|dec2020secondbatch@gmail.com|Selenium@123456|My account - My Store|

 