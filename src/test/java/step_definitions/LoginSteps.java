package step_definations;

import command_providers.ActOn;
import command_providers.AssertThat;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.ReadConfigFiles;

public class LoginSteps {
    private static final By FullName = By.id("name");
    private static final By Password = By.id("password");
    private static final By Login = By.id("login");
    private static final By Logout = By.id("logout");

    public static Logger LOGGER= LogManager.getLogger(LoginSteps.class);
    WebDriver driver;

    @Given("^a user is on the login page$")
    public void navigateToLoginPage(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("----remote-allow-origin=*");
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("https://example.testproject.io/web/"));
        LOGGER.info("user is on the login page");
    }
//    Previous-one
//    @When("^user enters username and password$")
//    public void enterUserCredentials(){
//        ActOn.element(driver, FullName).setValue("Riyad");
//        ActOn.element(driver, Password).setValue("12345");
//        LOGGER.info("User has entered credentials");
//    }

    //Update-one
    @When("^user enters username \"(.+?)\" and password \"(.+?)\"$")
    public void enterUserCredentials(String userName, String password){
        ActOn.element(driver, FullName).setValue(userName);
        ActOn.element(driver, Password).setValue(password);
        LOGGER.info("User has entered credentials");
    }

    @When("^user click on login button upon entering credentials$")
    public void ClickOnLoginUponEnteringCredential(DataTable table){

    }

    @And("^click on the login button$")
    public void clickOnLogin(){
        ActOn.element(driver, Login).click();
        LOGGER.info("User click on the login button");
    }

    @Then("^user is navigated to home page$")
    public void validateUserIsLoggedSuccessfully(){
        AssertThat.elementAssertions(driver, Logout).elementIsDisplayed();
        LOGGER.info("User is in Home Page");
        ActOn.browser(driver).closeBrowser();
    }
}
