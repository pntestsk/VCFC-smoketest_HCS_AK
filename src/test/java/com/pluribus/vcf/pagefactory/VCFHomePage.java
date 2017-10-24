package com.pluribus.vcf.pagefactory;

import com.pluribus.vcf.helper.PageInfra;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VCFHomePage extends PageInfra{

	@FindBy(how = How.CSS, using = "a.fa.fa-cogs")
    WebElement vcfSettingsIcon;

    @FindBy(how = How.CSS, using = "a.fa.fa-home")
    WebElement vcfHomeIcon;
    
    @FindBy(how = How.CSS, using = "div.homelogo.vcf-ia span")
	WebElement vcfIAIcon;
	
	@FindBy(how = How.CSS, using = "span.bullet.vcf-pa span")
	WebElement vcfPAIcon;
	
	@FindBy(how = How.CSS, using = "span.bullet.vcf-mgr span")
	WebElement vcfMgrIcon;
	
	@FindBy(how = How.CSS, using = "a.inner-center div") 
	WebElement vcfCenterIcon;
	
	//@FindBy(how = How.XPATH, using = "//a[@class='list-group-item category admin-menu']")
	//WebElement vcfUsers;

	//HCS:WebElement was updated due some changed in UI
	@FindBy(how = How.XPATH, using ="//a[@class= 'fa fa-sign-out']")
	WebElement clickvcfLogOut;
	
	//@FindBy(CSS = "a.fa.fa-sign-out")
	//WebElement clickvcfLogOut;
	
	@FindBy(how = How.XPATH, using = "//span[@class='fa fa-users']")
	WebElement clickAdminButton;
	
	@FindBy(how = How.XPATH,using ="//span[@class='fa fa-rocket']")
	WebElement clickManageApps;

	
		
	public VCFHomePage(WebDriver driver) {
         	super(driver);
	}
	
	//public void vcfUsersTab(){
	//	vcfUsers.click();
	//}
	
	public void waitForHomeLogo() {
		waitForElementVisibility(vcfIAIcon, 100);
	}
	
	//HCS:Testing Home Page opening or not
	public void gotoIA() {
		waitForElementVisibility(vcfIAIcon,1000);
		vcfIAIcon.click();
	}	
	
	//HCS: Testing PA Page opening or not
	public void gotoPA() {
		waitForElementVisibility(vcfPAIcon,1000);
		vcfPAIcon.click();
	}
	
	//HCS: Testing Fabric manager page opening or not
	public void gotoVCFMgr() {	
		waitForElementVisibility(vcfMgrIcon,1000);
		vcfMgrIcon.click();
	}
	
	//HCS:Testing Manage apps page opening or not
	public void clickManageApps() {
		waitForElementVisibility(clickManageApps,1000);
		clickManageApps.click();
	}

	//HCS:Testing Manage User Page
	public void clickAdminButton() {
		waitForElementVisibility(clickAdminButton, 1000);
		clickAdminButton.click();
	}

	//HCS:Testing user logout from application
	public void clickvcfLogOut() {
		waitForElementVisibility(clickvcfLogOut,1000);
		clickvcfLogOut.click();
				
	}

		
}
