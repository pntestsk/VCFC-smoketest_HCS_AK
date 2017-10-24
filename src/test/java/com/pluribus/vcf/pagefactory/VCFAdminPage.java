package com.pluribus.vcf.pagefactory;

import com.pluribus.vcf.helper.PageInfra;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//HCS:Testing Admin management page,Test adding new user 
public class VCFAdminPage extends PageInfra{
   
	@FindBy(how = How.XPATH, using="//button[contains(text(),'Add Admin')]")
	WebElement addAdminButton;
	
	@FindBy(id="username")
	WebElement addusername;
	
	@FindBy(id="password")
	WebElement addpassword;
	
	@FindBy(name="role")
	WebElement addRole;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Admin')]")
	WebElement roleAdmin;
	
	@FindBy(how = How.XPATH, using="//button[contains(text(),'Create')]")
	WebElement createAdminUser;
	
	public VCFAdminPage(WebDriver driver){
		 super(driver);
	}
	
	//HCS:Testing admin page opening or not
	public void roleAdmin() {
		roleAdmin.click();
	}
	
	//HCS:Adding new user
	public void addAdminUser() {
		waitForElementVisibility(addAdminButton,1000);
		addAdminButton.click();
	}
	
	//HCS:Adding user name in text box
	public void addUserName(String user) {
		waitForElementVisibility(addusername,1000);
		addusername.sendKeys(user);
	}
	
	//HCS:Adding password in text box
	public void addPassword(String pass) {
		waitForElementVisibility(addpassword,1000);
		addpassword.sendKeys(pass);
	}
	
	//HCS: Selecting role as a admin
	public void selectRole() {
		waitForElementVisibility(addRole, 1000);
		addRole.click();
	}
	
	//HCS: Creating admin user
		public void createAdminUser(){
			waitForElementVisibility(createAdminUser,1000);
			createAdminUser.click();
		}
}
