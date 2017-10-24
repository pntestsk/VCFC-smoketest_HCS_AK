package com.pluribus.vcf.pagefactory;


import com.pluribus.vcf.helper.PageInfra;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/* @Author: Anant
 * HCS: Verifying app info page and all apps list with build version, timestamp
 * 
 * */

public class VCFManageApps extends PageInfra{

		
	@FindBy(how = How.XPATH, using="//span[@class= 'fa fa-rocket']")
	WebElement ManageApps;
	
	@FindBy(how = How.XPATH, using ="//span[@class= 'vcf-center-color' and contains(text(),'UNUM')]")
	WebElement UNUM;
	
	@FindBy(how = How.XPATH, using ="//span[@class= 'vcf-ia-color' and contains(text(),'Insight Analytics Flow')]")
	WebElement InsightAnalyticsFlow;
	
	@FindBy(how = How.XPATH, using ="//span[@class= 'vcf-mgr-color' and contains(text(),'Fabric Manager')]")
	WebElement FabricManager;
	
	@FindBy(how = How.XPATH, using = "//span[@class= 'vcf-pa-color' and contains(text(),'Insight Analytics Packet')]")
	WebElement InsightAnalyticsPacket;
	
	@FindBy(how = How.XPATH, using = "//div[@id='tr_apps_0']/a/span/span[1]")
	WebElement UNUMBuildTime;
	
	@FindBy(how = How.XPATH, using = "//div[@id='tr_apps_0']/a/span/span[2]")
	WebElement UNUMVersion;
	
	@FindBy(how = How.XPATH, using = "//div[@id='tr_apps_1']/a/span/span[1]")
	WebElement InsightAnalyicsFlowBuildTime;
	
	@FindBy(how = How.XPATH, using = "//div[@id='tr_apps_1']/a/span/span[2]")
	WebElement InsightAnalyicsFlowVersion;
		
	@FindBy(how = How.XPATH, using = "//div[@id='tr_apps_2']/a/span/span[1]")
	WebElement FabricManagerBuildTime;
	
	@FindBy(how = How.XPATH, using = "//div[@id='tr_apps_2']/a/span/span[2]")
	WebElement FabricManagerVersion;
	
	@FindBy(how = How.XPATH, using = "//div[@id='tr_apps_3']/a/span/span[1]")
	WebElement InsightAnalyticsPacketBuildTime;
	
	@FindBy(how = How.XPATH, using = "//div[@id='tr_apps_3']/a/span/span[2]")
	WebElement InsightAnalyticsPacketVersion;
	
	public VCFManageApps(WebDriver driver){
		 super(driver);
	}
	
	//HCS: Print the UNUM Version
	public void UNUMVersion(){
		waitForElementVisibility(UNUMVersion,1000);
		UNUMVersion.getText();
		String unumversion = UNUMVersion.getText();
		System.out.println("UNUM" + unumversion );
		}
	
	//HCS: Print the UNUM BuildTime
	public void UNUMBuildTime(){
		waitForElementVisibility(UNUMBuildTime,1000);
		UNUMBuildTime.getText();
		String unumbuildtimestamp = UNUMBuildTime.getText();
		System.out.println("UNUM" + unumbuildtimestamp );
		}
	
	//HCS: Print IA Version
	public void InsightAnalyicsFlowVersion(){
		waitForElementVisibility(InsightAnalyicsFlowVersion,1000);
		InsightAnalyicsFlowVersion.getText();
		String insightanalyicsflowversion = InsightAnalyicsFlowVersion.getText();
		System.out.println("InsightAnalyicsFlowVersion" + insightanalyicsflowversion );
		}
	
	//HCS: Print IA BuildTime
	public void InsightAnalyicsFlowBuildTime(){
		waitForElementVisibility(InsightAnalyicsFlowBuildTime,1000);
		InsightAnalyicsFlowBuildTime.getText();
		String insightanalyicsflowbuildtime = InsightAnalyicsFlowBuildTime.getText();
		System.out.println("UNUM" + insightanalyicsflowbuildtime );
		}
	
	//HCS: Print FabricManager Version
	public void FabricManagerVersion(){
		waitForElementVisibility(FabricManagerVersion,1000);
		FabricManagerVersion.getText();
		String fabricmanagerversion = FabricManagerVersion.getText();
		System.out.println("FabricManagerVersion" + fabricmanagerversion );
		}
	
    //HCS: Print FabricManager BuildTime
	public void FabricManagerBuildTime(){
		waitForElementVisibility(FabricManagerBuildTime,1000);
		FabricManagerBuildTime.getText();
		String fabricmanagerbuildtime = FabricManagerBuildTime.getText();
		System.out.println("FabricManagerBuildTime" + fabricmanagerbuildtime );
		}
	
	//HCS : Print IA Packet Version	
	public void InsightAnalyticsPacketVersion(){
		waitForElementVisibility(InsightAnalyticsPacketVersion,1000);
		InsightAnalyticsPacketVersion.getText();
		String insightanalyticspacketversion = InsightAnalyticsPacketVersion.getText();
		System.out.println("InsightAnalyticsPacketVersion" + insightanalyticspacketversion );
		}
	
	//HCS : Print IA Packet BuildTime
	public void InsightAnalyticsPacketBuildTime(){
		waitForElementVisibility(InsightAnalyticsPacketBuildTime,1000);
		InsightAnalyticsPacketBuildTime.getText();
		String insightanalyticspacketbuildtime = InsightAnalyticsPacketBuildTime.getText();
		System.out.println("InsightAnalyticsPacketTime" + insightanalyticspacketbuildtime );
		}
	
	//HCS: Opening the AppInfo page
	public void ManageApps() {
		waitForElementVisibility(ManageApps,1000);
		ManageApps.click();
		}
	
	//HCS: Check the availability of UNUM in Software Detail Page
	public void UNUM(){
		waitForElementVisibility(UNUM,1000);
		UNUM.isDisplayed();
		}
	
	//HCS: Check the availability of IA in Software Detail Page
	public void InsightAnalyicsFlow() {
		waitForElementVisibility(InsightAnalyticsFlow,1000);
		InsightAnalyticsFlow.isDisplayed();
		}
	
	//HCS: Check the availability of FabricManger in Software Detail Page
	public void FabricManager() {
		waitForElementVisibility(FabricManager,1000);
		FabricManager.isDisplayed();
		}
	
	//HCS: Check the availability of IA Packet in Software Detail Page
	public void InsightAnalyticsPacket() {
		waitForElementVisibility(InsightAnalyticsPacket,1000);
		InsightAnalyticsPacket.isDisplayed();
		}
}
