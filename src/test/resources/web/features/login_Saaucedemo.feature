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

  @login_fail
  Scenario Outline: Successful login with credentials
    When the user enters the username "<username>" and the password "<password>"
    And the user clicks the login button
    Then an error message should be displayed saying "<message_error>"

    Examples:
      | username      | password     | message_error                                               |
      | standard_user | secret_sauce | Username and password do not match any user in this service |
      | standard_user |              | Password is required                                        |
      |               | test         | Username is required                                        |
