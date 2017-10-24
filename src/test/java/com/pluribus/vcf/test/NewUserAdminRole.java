package com.pluribus.vcf.test;
import com.pluribus.vcf.helper.TestSetup;
import com.pluribus.vcf.pagefactory.VCFLoginPage;
import com.pluribus.vcf.pagefactory.VCFAdminPage;
import com.pluribus.vcf.pagefactory.VCFHomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

//HCS:This test creates a New user with AdminRole,Changes the default password,Logout and Login with Changed Password
public class NewUserAdminRole extends TestSetup{
	private VCFHomePage home1;
	private VCFLoginPage login;
	private VCFAdminPage adminPage;
	private String newAdminUser = "abcd1";
	private String OldPassword = "test123";
	//private String NewPassword = "test@123";
	//private String confirmNewPassword = "test@123";
	
	@Parameters({"clientIp","serverIp","mgmtIp"})
	@BeforeClass(alwaysRun = true)
	public void init(String clientIp, String serverIp, String mgmtIp) {
		login = new VCFLoginPage(getDriver());
		home1 = new VCFHomePage(getDriver());
		adminPage = new VCFAdminPage(getDriver());
		}
	
	//HCS:New Admin User Creation
	@Parameters({"password"}) 
	@Test(alwaysRun = true)
	public void CreateNewAdminUser(@Optional("Compaq8**7") String password) throws Exception{
		System.out.println("Running NewAdminUser Creation Test");
		login.login("admin", password);
		home1.clickAdminButton();
		adminPage.addAdminUser();
		adminPage.addUserName(newAdminUser);
		adminPage.addPassword(OldPassword);
		adminPage.selectRole();
		adminPage.roleAdmin();
		adminPage.createAdminUser();
		Thread.sleep(1000);
		System.out.println("Admin User Created");
		home1.clickvcfLogOut();
		}
	
	//HCS:PasswordCahnge for NewUser
	@Test(alwaysRun = true)
	public void login() {
		System.out.println("Running PasswordChange Test for NewAdminUser");
		login.login("abcd1","test123");
		login.newAdminUserLogin("test123","test@123","test@123");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		home1.clickvcfLogOut();
		}
	
	@Test(alwaysRun = true)
	public void login1() {
		login.login("abcd1", "test@123");
		home1.clickvcfLogOut();
		}
	
}