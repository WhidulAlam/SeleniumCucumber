
@Login_test
Feature: Test login functionalities
  Background:
    Given a user is on the login page

  @positive_test
  Scenario: Check login is successful with valid credentials
    //Given a user is on the login page
    When user enters username "Riyad" and password "12345"
    And click on the login button
    Then user is navigated to home page


  @dataDriven_test
  Scenario Outline: Check login is successful with valid credentials for multiple users
    //Given a user is on the login page
    When user enters username "<username>" and password "<password>"
    And click on the login button
    Then user is navigated to home page

    Examples:
      | username | password |
      | Riyad    | 12345    |
      | Daniel   | 12345    |
      | Vettory  | 12345    |

  @dataTable_test
  Scenario: Check login is successful with valid credentials
    //Given a user is on the login page
    When user click on login button upon entering credentials
    |username|password|
    |Riyad   |12345   |
    Then user is navigated to home page

  @negative_Test
  Scenario: Check login is unsuccessful with invalid credentials
    //Given a user is on the login page
    When user enters username "Riyad" and password "12344"
    And click on the login button
    Then user is failed to login
