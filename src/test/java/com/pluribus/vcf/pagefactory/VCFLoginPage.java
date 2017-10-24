package com.pluribus.vcf.pagefactory;

import com.pluribus.vcf.helper.PageInfra;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VCFLoginPage extends PageInfra{

	/**
	 * All WebElements are identified by @FindBy annotation
	 */

	@FindBy(name="username")
	WebElement userNameVCF;
	
	@FindBy(name="password")
	WebElement passwordVCF;
	
	@FindBy(name="newPassword")
	WebElement newPassword;
	
	@FindBy(id="oldPassword")
	WebElement oldPassword;
	
	@FindBy(id="confirmNewPassword")
	WebElement confirmNewPassword;
	
	@FindBy(css="button.btn.btn-primary")
	WebElement loginBtn;
	
	//HCS:ClickSubmit ,Applies submit after changing the default password
	@FindBy(how = How.XPATH, using="//button[contains(text(),'Submit')]")
	WebElement clickSubmit;
	
	//HCS:Change the PATH from CSS to XPATH
	//@FindBy(css = "a.fa.fa-sign-out")
	//WebElement vcfLogout;
	
	@FindBy(how = How.XPATH, using="//a[@class= 'fa fa-sign-out']")
	WebElement vcfLogout;
    
	
	
	@FindBy(how = How.CSS, using = "a.fa.fa-cogs")
	WebElement vcfSettingsIcon;
	
	@FindBy(how = How.CSS, using = "a.fa.fa-home")
	WebElement vcfHomeIcon;
	
	public VCFLoginPage(WebDriver driver){
		 super(driver);
	}
	
	public void setOldUserPass(String oldpass){
		waitForElementVisibility(oldPassword,1000);
		oldPassword.sendKeys(oldpass);
	}
	
	public void setNewPasswordForNewUser(String newpass){
		waitForElementVisibility(newPassword,1000);
		newPassword.sendKeys(newpass);
	}
	
	public void setNewConfirmPasswordForNewUser(String newpass){
		confirmNewPassword.sendKeys(newpass);
	}
	
	public void setUserName(String strUserName){
		setValue(userNameVCF,strUserName);
	}
	public void logout(){
		waitForElementVisibility(vcfLogout,100);
		if(vcfLogout.isEnabled()) {
			vcfLogout.click();
		} 	
	}
	public void gotoHome() {
		vcfHomeIcon.click();
	}
	public void waitForLogoutButton() {
		waitForElementVisibility(vcfLogout,100);
	}
	
	public void setPassword(String strPassword){
		setValue(passwordVCF,strPassword);
	}
	
	public void setOldPassword(String strPassword){	
		setValue(oldPassword,strPassword);
	}
	
	public void setNewPassword(String strPassword){
		setValue(newPassword,strPassword);
	}
	
	public void setConfirmPassword(String strPassword){
		setValue(confirmNewPassword,strPassword);
	}
	
	//HCS:Changing the Default password of NewAdmin User
	public void newAdminUserLogin(String oldPassword, String NewPassword,String confirmNewPassword) {
		setOldPassword(oldPassword);
		setNewPassword(NewPassword);
		setConfirmPassword(confirmNewPassword);
		clickSubmit.click();
		}
	
	//HCS:Click on login button
	public void clickLogin(){
			loginBtn.click();
		}
	
	public void firstlogin(String strUserName,String newPassword){
		    login(strUserName,strUserName);
			this.setNewPassword(newPassword);
			this.setConfirmPassword(newPassword);
			this.clickLogin();			
		}
	
	public void login(String strUserName,String strPasword ){
		this.setUserName(strUserName);
		this.setPassword(strPasword);
		this.clickLogin();	
		}
	}
