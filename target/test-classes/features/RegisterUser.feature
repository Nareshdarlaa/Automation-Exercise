Feature: User Registration

  Scenario: Register with an existing email
    Given Launch browser
    When Navigate to url "https://automationexercise.com/"
    Then Verify that home page is visible successfully
    When Click on "Signup / Login" button
    Then Verify "New User Signup!" is visible
    When Enter name "Naresh" and email address "darlanaresh25@gmail.com"
    And Click "Signup" button
    Then Verify error message "Email Address already exist!" is displayed
    And Close the browser
