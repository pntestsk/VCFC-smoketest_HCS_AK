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

public class IATest extends TestSetup {
	//private static final WebElement Element = null;
	private VCFHomePage home1;
	private VCFIaIndexPage iaIndex;
	private VCFLoginPage login;
	//private String switchUserName = "network-admin";
	//private String switchPassword = "test123";
	//private VcfSettingsPage settings;      
	//private String pncPwd = "test123";
	//private String pncuName= "pn-vcf";
	//private String vcfUserName = "admin";
	//private SwitchMethods cli;
	//private final static String FILE_UPLOAD = "selectFileToUpload";
	    
	    
	@Parameters({"clientIp","serverIp","mgmtIp"})
	@BeforeClass(alwaysRun = true)
	public void init (String clientIp, String serverIp, String mgmtIp) {
		//cli = new SwitchMethods(mgmtIp);
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
	
	//HCS:Tag Test - working on it
	 /*
	@Test(groups={"smoke","regression"},dependsOnMethods={"logintoIA"},description="Tag test")
	public void tagTest() {
		try{
			iaIndex.tagIcon();
			iaIndex.uploadTags();
			iaIndex.selectFileToUpload();
			((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
	        Thread.sleep(2000);
	        String path = "C:\\Users"  + File.separator + "akumar09"  + File.separator + "Desktop"  + File.separator + "srcIp.csv";
	        File f=new File(path);
	        System.out.println(f.isFile() ? "yes" : "no" );
	        //WebElement upload = driver.findElement(By.id("myfile"));upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");driver.findElement(By.id("submit")).click(); 
	        driver.findElement(By.xpath("//div[@id='file']/descendant::span[@ng-if='!$ctrl.createObj.file' and contains (text(),'Click to select or drop here...')]"));
	        iaIndex.selectFileToUpload.sendKeys(f.toURI().toString());
	        //driver.findElement(By.xpath("upload")).click();
	        //Actions actions = new Actions(driver);
	        //actions.moveToElement(Element);
	        //actions.click();
	        //actions.sendKeys("file\\C:\\Users\\akumar09\\Desktop\\VCFC-smoketest_CreateAdmin_Manage\\src\\test\\resources\\srcIp.csv");
	        //actions.build().perform();
			} catch (Exception e) {
			System.out.println("error in file test");
			e.printStackTrace();
			}
		   }
	 */
	
	
	//HCS: Report Test --> need to improve
	@Parameters({"trafficDestIp","trafficNumSessions"}) 
	@Test(groups={"smoke","regression"}, dependsOnMethods = { "logintoIA" },description="Report Test")
	public void report(String trafficDestIp, int trafficNumSessions) throws Exception{
		System.out.println("Running Report Test");
				iaIndex.report();
				System.out.println("Report Button is Avaiable");
				Thread.sleep(8000);
				iaIndex.searchBox();
				System.out.println("Search Box Avaiable on Report");
				iaIndex.applySearchFilter("dstIp: "+trafficDestIp);
				iaIndex.searchString1();
				Thread.sleep(80000);
				boolean dstStatus = verifydstIpCount(trafficNumSessions);
				if(dstStatus == true) {
					printLogs("info","simpleTrafficTest","VCFC count verification after applying dstIp filter passed");
				} else {
					printLogs("error","simpleTrafficTest","VCFC count verification after applying dstIP filter failed");	
				}				
	}
	private boolean verifydstIpCount(int trafficNumSessions) {
		return false;
	}				
	
	//HCS:SearchTest For dstIP and srcIp
	@Parameters({"trafficDestIp", "trafficSrcIp", "trafficNumSessions", "trafficInterval"}) 
	@Test(groups={"smoke","regression"},dependsOnMethods={"logintoIA"},description="Search Test")
	public void search(String trafficDestIp, String trafficSrcIp, int trafficNumSessions, int trafficInterval) throws Exception{      
		        System.out.println("Running Search Test");			
				//Apply search filter for dstIp
		        iaIndex.searchBox();
				iaIndex.applySearchFilter("dstIp: "+trafficDestIp);
				iaIndex.searchString1();
				Thread.sleep(80000);
				boolean status = verifydstIpCount(trafficNumSessions);
				if(status == true) {
					printLogs("info","simpleTrafficTest","VCFC count verification after applying dstIp filter passed");
				} else {
					printLogs("error","simpleTrafficTest","VCFC count verification after applying dstIP filter failed");	
				}
				//Apply search filter for srcIp
				iaIndex.searchBox();
				iaIndex.applySearchFilter("srcIp: "+trafficSrcIp);
				iaIndex.searchString1();
				Thread.sleep(80000);
				boolean srcStatus = verifysrcIpcount(trafficNumSessions);
				if(srcStatus == true) {
					printLogs("info","simpleTrafficTest","VCFC count verification after applying srcIp filter passed");
				} else {
					printLogs("error","simpleTrafficTest","VCFC count verification after applying srcIP filter failed");			
				}
				if(srcStatus == false) {
					throw new Exception(" Simple traffic test failed");
				}
			   }   
	private boolean verifysrcIpcount(int trafficNumSessions) {
		return false;
	}
         
	//HCS:Collector Management Test
	@Test(groups={"smoke","regression"},dependsOnMethods={"logintoIA"},description="IACollector Configuration Test")
	public void iaCollectorConfigurationTest() {     
		try{
		System.out.println("Running IA Collector Test");
		iaIndex.UNUMSetting();
		Thread.sleep(10000);
		System.out.println("Configuration Page is Available");
		iaIndex.CollectorManagement();
		Thread.sleep(10000);
		System.out.println("Collector Management Page is Available");
		iaIndex.AddNetvisorCollector();
		Thread.sleep(1000);
		System.out.println("Netvisor Configuration Page is Available");
		iaIndex.Cancel();
		iaIndex.UNUMHome();
		Thread.sleep(10000);
		home1.gotoIA();
		} catch (Exception e) {
			System.out.println("Not Able to Access IACollector");
			e.printStackTrace();
		 }
	    }
	
	//HCS: IAFlow Page Test
	@Test(groups={"smoke","regression"},dependsOnMethods={"logintoIA"},description="InsightAnalyticsFlows Page Test")
	public void InsightAnalyticsFlows(){
		try{
		System.out.println("Running IA Flows Tab Test");
		iaIndex.Connections();
		Thread.sleep(40000);
		System.out.println("Connections Page is Available");
		iaIndex.Traffic();
		Thread.sleep(40000);
		System.out.println("Traffic Page is Available");
		iaIndex.VMwareConnections();
		Thread.sleep(40000);
		System.out.println("VMwareConnections Page is Available");
		iaIndex.VMwareTraffic();
		Thread.sleep(40000);
		System.out.println("VMwareTraffic Page is Available");
		iaIndex.PortStatus();
		Thread.sleep(40000);
		System.out.println("PortStatus Page is Available");
		} catch (Exception e) {
			System.out.println("Not Able to Access IAFlow Page");
			e.printStackTrace();
		 }
	    }
	
	@AfterClass(alwaysRun = true)
	public void logout() {
		home1.clickvcfLogOut();
	}		
}

	
	
	



	
