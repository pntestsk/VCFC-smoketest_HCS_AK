package com.pluribus.vcf.test;
import com.pluribus.vcf.helper.TestSetup;
import com.pluribus.vcf.pagefactory.VCFLoginPage;
import com.pluribus.vcf.pagefactory.VCFHomePage;
import com.pluribus.vcf.pagefactory.VCFIaIndexPage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

    public class SearchRefreshFilter extends TestSetup {
	//private static final WebElement Element = null;
	private VCFHomePage home1;
	private VCFIaIndexPage iaIndex;
	private VCFLoginPage login;
	    
	    
	@Parameters({"clientIp","serverIp","mgmtIp"})
	@BeforeClass(alwaysRun = true)
	public void init (String clientIp, String serverIp, String mgmtIp) {
		login = new VCFLoginPage(getDriver());
		home1 = new VCFHomePage(getDriver());
		iaIndex = new VCFIaIndexPage(getDriver());
		
	}
	
	@Parameters({"LogintoIA as Admin"}) 
	@Test(alwaysRun = true)
	public void logintoIA(@Optional("Compaq8**7") String password){
		login.login("admin", password);
		home1.gotoIA();
		}
	         
	//HCS:QuickTimeRange Test for Each Tab in IA page
	@Test(groups={"smoke","regression"},dependsOnMethods={"logintoIA"},description="QuickTimeRange Test for Each Tab in IA page")
	public void QuickTimeRange() {
		try{
		System.out.println("Running QuickTimeRange Test");
		iaIndex.Connections();
		Thread.sleep(50000);
		iaIndex.quickTimeSearch();
		//iaIndex.IntervalSelector();
		//iaIndex.RefreshInterval();
		Thread.sleep(4000);
		System.out.println("QuickTimeRange Avaiable in Connection Tab");
		iaIndex.Traffic();
		iaIndex.quickTimeSearch();
		Thread.sleep(40000);
		System.out.println("QuickTimeRange Avaiable in Traffic Page");
		iaIndex.VMwareConnections();
		iaIndex.quickTimeSearch();
		Thread.sleep(40000);
		System.out.println("QuickTimeRange Avaiable in VMwareConnections Page");
		iaIndex.VMwareTraffic();
		iaIndex.quickTimeSearch();
		Thread.sleep(40000);
		System.out.println("QuickTimeRange Avaiable in VMwareTraffic Page");
		iaIndex.PortStatus();
		iaIndex.quickTimeSearch();
		Thread.sleep(40000);
		System.out.println("QuickTimeRange Avaiable in PortStatus Page");
		} catch (Exception e) {
			System.out.println("Not Able to Access QuickTimeRange Page");
			e.printStackTrace();
		 }
     }
	
	@AfterClass(alwaysRun = true)
	public void logout() {
		home1.clickvcfLogOut();
	}		
}

	
	
	



	
