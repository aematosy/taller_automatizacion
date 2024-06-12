@taller_demo
Feature: SauceDemo Login

  Background:
    Given the user is on the SauceDemo login page

  @login_successful
  Scenario Outline: Successful login with credentials
    When the user enters the username "<username>" and the password "<password>"
    And the user clicks the login button
    Then the user should be redirected to the inventory page

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |
