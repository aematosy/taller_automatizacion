#Author: Adrian Matos
@POC
Feature: LoginPage of TV Azteca digital

  Background: Open the url in the browser
    Given The user is on home page

  @login @login_success
  Scenario Outline: Login success on TV Azteca digital
    When I click on "Iniciar sesion" option
    Then I click on "Iniciar con correo electronico" option
    And I log in with email "<email>" and password "<password>"
    Then I validate that I have logged in correctly

    Examples:
      | email                | password  |
      | mat.210920@gmail.com | test123A@ |
      | fear@gmail.com       | test      |

  @login @login_failed
  Scenario Outline: Login failed on TV Azteca digital
    When I click on "Iniciar sesion" option
    Then I click on "Iniciar con correo electronico" option
    And I log in with email "<email>" and password "<password>"
    Then I validate the error message "<message>"

    Examples:
      | email            | password  | message                                    |
      | failed@gmail.com | test123A@ | El usuario o la contraseña son incorrectos |

