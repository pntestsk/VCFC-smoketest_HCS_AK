package com.pluribus.vcf.test;
import com.pluribus.vcf.helper.TestSetup;
import com.pluribus.vcf.pagefactory.VCFLoginPage;
import com.pluribus.vcf.pagefactory.VCFHomePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class IAPAlinkTest extends TestSetup {
	
	private VCFHomePage home;
	private VCFLoginPage login;	    
	    
	@Parameters({"clientIp","serverIp","mgmtIp"})
	@BeforeClass(alwaysRun = true)
	public void init (String clientIp, String serverIp, String mgmtIp) {
		login = new VCFLoginPage(getDriver());
		home = new VCFHomePage(getDriver());
		
	}
	
	//HCS:To Verify On Home Page IA and PA links are present
	@Parameters({"LogintoIA as Admin"}) 
	@Test(alwaysRun = true)
	public void logintoIA(@Optional("Compaq8**7") String password){
		login.login("admin", password);
		List <WebElement> allElement = driver.findElements(By.xpath("(//div[@class='homelogo vcf-ia'])"));
		for(WebElement element: allElement) {
		Reporter.log("InsightAnalytics " + element.getText(), true);
	    List <WebElement> allElement2 = driver.findElements(By.xpath("(//div[@class='homelogo vcf-pa'])"));
		for(WebElement element2: allElement2) {
		Reporter.log("PacketAnalytics " + element2.getText(), true);
				}
			}
	}
	
	@AfterClass(alwaysRun = true)
	public void logout() {
		home.clickvcfLogOut();
	}
		
}

	
	
	



	
