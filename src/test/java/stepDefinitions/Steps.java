package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.LoginPage;

import java.net.URL;

public class Steps {
    public WebDriver driver;
    public LoginPage lp;

    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--incognito");
        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        lp = new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        driver.get(url);
    }

    @And("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        lp.setUserName(email);
        lp.setPassword(password);
    }

    @And("Click on Login")
    public void click_on_login() {
        lp.clickLogin();
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) {
        if (driver.getPageSource().contains("Login was unsuccessful.")) {
            driver.close();
            Assert.assertTrue(false);
        } else {
            Assert.assertEquals(title, driver.getTitle());
        }
    }

//    @Then("The Page Title should be {string}")
//    public void the_page_title_should_be(String title) {
//        if (driver.getPageSource().contains("Login was unsuccessful.")) {
//            driver.close();
////            This will make test method fail if page source contains("Login was unsuccessful.") message
//            Assert.assertTrue(false);
////          Else get the title of the page
//        } else {
//            Assert.assertEquals(title, driver.getTitle());
//        }
//    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() throws InterruptedException {
        lp.clickLogout();
        Thread.sleep(3000);
    }

    @And("close browser")
    public void close_browser() {

        driver.close();
    }

}
