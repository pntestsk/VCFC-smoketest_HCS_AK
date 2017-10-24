package com.pluribus.vcf.test;
import com.pluribus.vcf.helper.TestSetup;
import com.pluribus.vcf.helper.PageInfra;
import com.pluribus.vcf.pagefactory.VCFLoginPage;
import com.pluribus.vcf.pagefactory.VCFHomePage;
import com.pluribus.vcf.pagefactory.VCFIaIndexPage;
import com.pluribus.vcf.pagefactory.VCFManageApps;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

//HCS:This Test is for Verifying the Software Details Page
public class ManageApps<WebElement> extends TestSetup{
	private VCFHomePage home1;
	private VCFLoginPage login;
	private VCFManageApps manageapps;
	//private VCFIaIndexPage iaIndex;
	
	@Parameters({"clientIp","serverIp","mgmtIp"})
	@BeforeClass(alwaysRun = true)
	public void init(String clientIp, String serverIp, String mgmtIp) {
		login = new VCFLoginPage(getDriver());
		home1 = new VCFHomePage(getDriver());
		manageapps = new VCFManageApps(getDriver());
		
	}
	//HCS: Verify SoftwareDetail Page
	@Parameters({"password"}) 
	@Test(alwaysRun = true)
	public void loginAsAdmin(@Optional("Compaq8**7") String password) throws Exception{
		System.out.println("Running Software Details Page Test");
		login.login("admin", password);
		Thread.sleep(1000);
		home1.clickManageApps();
		manageapps.UNUM();
		manageapps.UNUMVersion();
		manageapps.UNUMBuildTime();
		manageapps.InsightAnalyicsFlow();
		manageapps.InsightAnalyicsFlowBuildTime();
		manageapps.InsightAnalyicsFlowVersion();
		manageapps.FabricManager();
		manageapps.FabricManagerBuildTime();
		manageapps.FabricManagerVersion();
		manageapps.InsightAnalyticsPacket();
		manageapps.InsightAnalyticsPacketBuildTime();
		manageapps.InsightAnalyticsPacketVersion();
		
		login.logout();
	    Thread.sleep(10000);
	}
	
	
	
}