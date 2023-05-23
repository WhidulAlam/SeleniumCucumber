Feature: Mortgage Calculator

  @CalculateApr
  Scenario: Calculator Real APR Rate
    Given a user is in mortgage calculator home page
    And user navigate to Real Apr page
    When user click on calculate button upon entering the data
    |HomePrice|DownPayment|InterestRate|
    |200000   |15000      |3           |
    Then the real apr rate is "3.139%"
#
#  @CalculateMortgageRate
#  Scenario: Calculate Monthly Mortgage
#    Given a user is in mortgage calculator home page
#    And user entering the monthly payment data
#    |HomePrice|DownPayment|LoanAmount|InterestRate|LoanTermYear|PropertyTax|PMI|HOInsurance|HOA|LoanType|
#    |300000   |60000      |240000    |3           |30          |5000       |0.5|1000       |100|FHA     |
#
#    Then calculate total monthly payment is "$1611.85"
