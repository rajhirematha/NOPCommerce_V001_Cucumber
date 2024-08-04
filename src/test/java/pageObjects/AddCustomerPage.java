package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
    public WebDriver ldriver;

    //  Constructor
    public AddCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(ldriver, this);
    }

    By lnkCustomer_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
    By getLnkCustomer_menuItem = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
    By lnk_Add = By.xpath("//a[normalize-space()='Add new']");
    By txt_Email = By.xpath("//input[@id='Email']");
    By txt_pwd = By.xpath("//input[@id='Password']");
    By txt_FN = By.xpath("//input[@id='FirstName']");
    By txt_LN = By.xpath("//input[@id='LastName']");
    By txt_Company = By.xpath("//input[@id='Company']");
    By txt_Comment = By.xpath("//textarea[@id='AdminComment']");

    By txt_CustomerRoles = By.cssSelector("//span[@aria-expanded='true']//ul[@class='select2-selection__rendered']");
    By txt_Admin = By.cssSelector("//li[@id='select2-SelectedCustomerRoleIds-result-tgbg-1']");
//    By txt_Forum = By.cssSelector("#select2-SelectedCustomerRoleIds-result-lags-2");
    By txt_Reg = By.cssSelector("#select2-SelectedCustomerRoleIds-result-wqre-3");
    By txt_Guest = By.cssSelector("#select2-SelectedCustomerRoleIds-result-xmds-4");
    By txt_Vend = By.cssSelector("#select2-SelectedCustomerRoleIds-result-vza0-5");
    By txt_MgrVend = By.cssSelector("#VendorId");
    By txt_Active = By.cssSelector("#Active");

    By txt_Male = By.xpath("//input[@id='Gender_Male']");
    By txt_Female = By.xpath("//input[@id='Gender_Female']");
    By txt_DOB = By.xpath("//input[@id='DateOfBirth']");


    By txt_Save = By.xpath("//button[name='save']");

//  =============================================Action Methods====================================================

    public void menuCustomer() {
        ldriver.findElement(lnkCustomer_menu).click();
    }

    public void menuCustomerItem() {
        ldriver.findElement(getLnkCustomer_menuItem).click();
    }

    public void setLnk_Add() {
        ldriver.findElement(lnk_Add).click();
    }

    public void setTxt_Email(String email) {
        ldriver.findElement(txt_Email).sendKeys(email);
    }

    public void setTxt_pwd(String password) {
        ldriver.findElement(txt_pwd).sendKeys(password);
    }

    public void setTxt_FN(String fname) {
        ldriver.findElement(txt_FN).sendKeys(fname);
    }

    public void setTxt_LN(String lname) {
        ldriver.findElement(txt_LN).sendKeys(lname);
    }

    public void setTxt_Company(String compName) {
        ldriver.findElement(txt_Company).sendKeys(compName);
    }

    public void setTxt_Comment(String comment) {
        ldriver.findElement(txt_Comment).sendKeys(comment);
    }

    public void setTxt_MgrVend(String comment) {
        ldriver.findElement(txt_Comment).sendKeys(comment);
    }

    public void setManagerOfVendor(String value) {
        Select drp = new Select(ldriver.findElement(txt_MgrVend));
        drp.selectByVisibleText(value);
    }

    public void setTxt_Active() {
        ldriver.findElement(txt_Active).click();
    }

    public void setGender(String gender) {
        if (gender.equals("Male")) {
            ldriver.findElement(txt_Male).click();
        } else if (gender.equals("Female")) {
            ldriver.findElement(txt_Female).click();
        } else {
            ldriver.findElement(txt_Male).click();//Default
        }
    }

    public void setTxt_DOB(String dob) {
        ldriver.findElement(txt_DOB).sendKeys(dob);
    }

    public void setTxt_Save() {
        ldriver.findElement(txt_Save).click();
    }


    public void setCustomerRoles(String role) throws InterruptedException
    {
        if(!role.equals("Vendors")) //If role is vendors should not delete Register as per req.
        {
            ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
        }

        ldriver.findElement(txt_CustomerRoles).click();

        WebElement listitem;

        Thread.sleep(3000);

        if(role.equals("Administrators"))
        {
            listitem=ldriver.findElement(txt_Admin);
        }
        else if(role.equals("Guests"))
        {
            listitem=ldriver.findElement(txt_Guest);
        }
        else if(role.equals("Registered"))
        {
            listitem=ldriver.findElement(txt_Reg);
        }
        else if(role.equals("Vendors"))
        {
            listitem=ldriver.findElement(txt_Vend);
        }
        else
        {
            listitem=ldriver.findElement(txt_Guest);
        }

        //listitem.click();
        //Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor)ldriver;
        js.executeScript("arguments[0].click();", listitem);

    }


}