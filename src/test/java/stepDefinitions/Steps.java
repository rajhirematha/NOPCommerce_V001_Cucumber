package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;

import java.util.concurrent.TimeUnit;


public class Steps extends BaseClass {

    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--disable-popup-blocking");
//        co.addArguments("--incognito");
        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
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


    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        addCustomerPage = new AddCustomerPage(driver);
//        This will compare expected title with actual title[addCustomerPage.getPageTitle()]
        Assert.assertEquals("Dashboard / nopCommerce administration", addCustomerPage.getPageTitle());
    }

    @When("User click on customers Menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        Thread.sleep(3000);
        addCustomerPage.menuCustomer();
    }

    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() throws InterruptedException {
        Thread.sleep(2000);
        addCustomerPage.menuCustomerItem();
    }

    @When("click on Add new button")
    public void click_on_add_new_button() throws InterruptedException {
        Thread.sleep(2000);
        addCustomerPage.setLnk_Add();
    }

    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCustomerPage.getPageTitle());
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        String email = randomestring() + "@gmail.com";
        addCustomerPage.setTxt_Email(email);
        addCustomerPage.setTxt_pwd("test123");
        addCustomerPage.setCustomerRoles("Guests");
        addCustomerPage.setManagerOfVendor("Vendor 2");
        addCustomerPage.setTxt_Company("Raj's Company");
        addCustomerPage.setTxt_Comment("Its a new Company");
        addCustomerPage.setGender("Male");
        addCustomerPage.setTxt_Active();
        addCustomerPage.setTxt_FN("Raj");
        addCustomerPage.setTxt_LN("Swami");
        addCustomerPage.setTxt_DOB("30/01/1995");
    }

    @When("click on Save button")
    public void click_on_save_button() {
        addCustomerPage.setTxt_Save();
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String successMsg) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully"));
    }

    @And("close browser")
    public void close_browser() {

        driver.close();
    }

}
