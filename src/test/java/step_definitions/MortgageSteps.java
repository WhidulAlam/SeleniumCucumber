package step_definitions;

import command_providers.ActOn;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page_object.Home;
import page_object.RealApr;
import utilities.ReadConfigFiles;

import java.util.List;
import java.util.Map;

public class MortgageSteps {
    private static Logger LOGGER = LogManager.getLogger(MortgageSteps.class);

    WebDriver driver = Hooks.driver;

    @Given("^a user is in mortgage calculator home page$")
    public void user_is_in_mortgage_calculator_home_page() {
        ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("MortgageAppUrl"));
        LOGGER.info("User is in the Mortgage Calculator Home page");
    }

    @And("^user navigate to Real Apr page$")
    public void user_navigate_to_real_apr_page() {
        new Home(driver)
                .mouseHoverToRates()
                .nagetiveToRealApr()
                .waitForPageToLoad();
        LOGGER.info("Navigate to Real Apr page");
    }

    @When("^user click on calculate button upon entering the data$")
    public void user_click_on_calculate_button_upon_entering_the_data(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        for (Map<String, String> cells: data) {

            new RealApr(driver)
                    .typeOfHomePrice(cells.get("HomePrice"))
                    .typeDownPayment(cells.get("DownPayment"))
                    .typeInterestRate(cells.get("InterestRate"))
                    .clickOnCalculateButton();
            LOGGER.info("Real APR rate is calculated upon entering the data");
        }


    }

    /*@And("^user entering the monthly payment data$")
    public void userEnteringTheMonthlyPaymentData(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        for (Map<String, String> cells: data) {
            new Home(driver)
                    .typeOfHomePrice(cells.get("HomePrice"))
                    .typeDownPayment(cells.get("DownPayment"))
                    .typeLoanAmount(cells.get("LoanAmount"))
                    .typeInterestRate(cells.get("InterestRate"))
                    .typeYear(cells.get("LoanTermYear"))
                    .typePropertyTax(cells.get("PropertyTax"))
                    .typePMI(cells.get("PMI"))
                    .typeHomeOwnerInsurance(cells.get("HOInsurance"))
                    .typeMonthlyHOA(cells.get("HOA"))
                    .selectLoanType(cells.get("LoanType"))
                    .clickOnCalculateButton();
            LOGGER.info("Total Monthly payment is calculated upon entering the data");
        }*/


        /*@When("^click on the calculate button$")
        public void clickOnTheCalculateButton(DataTable) {
            new Home(driver)

                    .clickOnCalculateButton()
            LOGGER.info("Monthly mortgage is calculated by clicking calculate button");
        }
    }*/

    @Then("^the real apr rate is \"(.+?)\"$")
    public void the_real_apr_rate_is(String realApr) {
        new RealApr(driver)
                .validateRealAprRate(realApr);

        LOGGER.info(String.format("Real APR rate: %s is validated", realApr));
    }

   /* @Then("^calculate total monthly payment is \"(.+?)\"$")
    public void calculateTotalMonthlyPaymentIs(String monthlyPayment) {
        new Home(driver)
                .validateTotalMonthlyPayment(monthlyPayment);
        LOGGER.info(String.format("Total Monthly payment Mortgage: %s is validated", monthlyPayment));
    }*/
}
