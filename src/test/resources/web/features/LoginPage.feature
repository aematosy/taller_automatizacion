#Author: Adrian Matos
@Taller
Feature: LoginPage of Demosite

  Background: Open the url in the browser
    Given The user is on home page

  @register_login
  Scenario Outline: Login success on DemoSite Guru with a new account
    When I click on "Register" option
    And I fill in the Contact Information with firstname "<firstname>", lastname "<lastname>", phone "<phone>", and email "<email>"
    And I fill in the Mailing Information with address "<address>", city "<city>", state "<state>", postal code "<postal_code>", and country "<country>"
    And I fill in the User Information with username "<username>" and password "<password>"
    Then I should be on the successful registration page
    When I click on "Sign On" button
    And I log in with username "<username>" and password "<password>"
    Then I should see the message "Login Successfully"


    Examples:
      | firstname | lastname | phone     | email          | address                | city | state | postal_code | country | username | password |
      | Adrian    | Matos    | 999665447 | test@gmail.com | calle siempre viva 123 | Lima | Lima  | 15005       | PERU    | test     | test123  |
