package stepDefinitions;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.WebDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;

import java.util.UUID;

public class BaseClass {
    public WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage addCustomerPage;

//    METHOD TO CREATE RANDOM STRING for unique Email ids
    public static String randomestring() {
        String uuid =  UUID.randomUUID().toString().replace("-", "").substring(0, 5);
        return uuid;
    }

}
