package com.pluribus.vcf.pagefactory;
import com.pluribus.vcf.helper.PageInfra;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

    

public class VCFIaIndexPage extends PageInfra {

	@FindBy(how = How.NAME, using = "username")
	WebElement userName;
	
	@FindBy(how = How.NAME, using = "password")
	WebElement password;

	@FindBy(how = How.NAME, using = "ok")
	WebElement okButton;
	
	@FindBy(how = How.CSS, using = "a.list-group-item.category.ia-dashboard-menu")
	WebElement dashboardIcon;
	
	@FindBy(how = How.CSS, using = "a.list-group-item.category.ia-tag-menu")
	WebElement tagIcon;
	
	@FindBy(how = How.CSS, using = "div.metric-value.ng-binding")
	WebElement countIcons;	
	 
	@FindBy(how= How.CSS, using = "a#taggingOptions.btn.btn-default.dropdown-toggle")
	WebElement tagOptions;

	//@FindBy(how = How.CSS, using = "input[type = 'text']")
	@FindBy(how= How.XPATH,using ="//a[@ng-click='$ctrl.uploadTaggingFile()' and contains (text(),'Upload Tags')]")
	WebElement uploadTags;
	
	//@FindBy(how = How.CSS, using = "input[type = 'text']")
	//WebElement searchBox;
	
	@FindBy(how= How.CSS, using = "button.btn.btn-primary")
	WebElement confirmOkButton;
		
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH,using ="//div[@id='file']/descendant::span[@ng-if='!$ctrl.createObj.file' and contains (text(),'Click to select or drop here...')]")
	public
	WebElement selectFileToUpload;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH,using ="//button[@class='btn btn-primary' and contains (text(),'Upload')]")
	WebElement upload;
	
	//HCS:Newly added WebElement
	@FindBy(how = How.XPATH, using = "//form[@class='fullWidth ng-pristine ng-valid']/descendant::input[@class='form-control ng-pristine ng-untouched ng-valid']")
	WebElement searchBox;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using = "//li[@class= 'label nav-sub-module-label active']|a[@ui-sref='ia-dashboard.connections']")
	WebElement Connections;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using = "//li[@class='label nav-sub-module-label']/descendant::a[@ui-sref='ia-dashboard.traffic' and contains (text(),'Traffic')]")
	WebElement Traffic;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using = "//li[@class='label nav-sub-module-label']/descendant::a[@ui-sref='ia-dashboard.vmconnections' and contains (text(),'VMware Connections')]")
	WebElement VMwareConnections;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using = "//li[@class='label nav-sub-module-label']/descendant::a[@ui-sref='ia-dashboard.vmtraffic' and contains (text(),'VMware Traffic')]")
	WebElement VMwareTraffic;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using = "//li[@class='label nav-sub-module-label']/descendant::a[@ui-sref='ia-dashboard.portstatus' and contains (text(),'Port Status')]")
	WebElement PortStatus;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using = "//span[@class='panel-title' and contains (text(),'Insight - Top L4 Services by Connections')]")
	WebElement InsightTopL4ServicesbyConnections;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using = "//span[@class='panel-title' and contains (text(),'Insight - Top Clients by Connections')]")
	WebElement InsightTopClientsbyConnections;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using = "//span[@class='panel-title' and contains (text(),'Insight - L4 Services Count')]")
	WebElement 	InsightL4ServicesCount;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using = "//div[contains(text(),'Unique counts of L4 Service')]")
	WebElement 	UniquecountsofL4Service;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//span[@class='panel-title' and contains (text(),'Insight - Connection Count')]")
	WebElement InsightConnectionCount;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//div[contains(text(),'Count')]")
	WebElement Count;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//span[@class='panel-title' and contains (text(),'Insight - Top Domains by Connections')]")
	WebElement InsightTopDomainsbyConnections;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//span[@class='panel-title' and contains (text(),'Insight - Top Servers by Connections')]")
	WebElement	InsightTopServersbyConnections;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//span[@class='panel-title' and contains (text(),'Top Servers by Total Unique Clients')]")
	WebElement 	TopServersbyTotalUniqueClients;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//span[@class='panel-title' and contains (text(),'Top L4 Services by Total Unique Clients')]")
	WebElement TopL4ServicesbyTotalUniqueClients;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//span[@class='panel-title' and contains (text(),'Insight - Connections by State')]")
	WebElement 	InsightConnectionsbyState;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//span[@class='panel-title' and contains (text(),'Top Switches by Total # of Connections')]")
	WebElement TopSwitchesbyTotalNoofConnections;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//a[@class='fa fa-cogs']")
	WebElement UNUMSetting;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using =".//a[contains(@href,'/vcf-center/collector')]")
	WebElement CollectorManagement;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//button[@class='btn btn-sm btn-primary' and contains (text(),'Add Netvisor Collector')]")
	WebElement	AddNetvisorCollector;	
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//a[@class='fa fa-home']")
	WebElement UNUMHome;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//button[@class='btn btn-warning cancel' and contains (text(),'Cancel')]")
	WebElement Cancel;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//a[@class='ng-binding']")
	WebElement searchString1;	

	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//a[@class='list-group-item category ia-report-menu']/descendant::span[@class='fa fa-file-text']")
	WebElement report;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//span[@class='fa fa-clock-o']/descendant::pretty-duration[@to='timefilter.time.to']")
	WebElement TimeSelector;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//li[@class='active']")
	WebElement TimeRangeQuick;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//div[@class='kbn-timepicker-section']/descendant::li[@ng-repeat='option in list']/a")
	WebElement QuickTimeRange;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//div[@class='search-filter']/descendant::span[@ng-show='timefilter.refreshInterval.value > 0' and contains (text(),'60 seconds')]")
	WebElement IntervalSelector;
	
	//HCS:Newly added WebElement
	@FindBy(how= How.XPATH, using ="//li[@ng-repeat='inter in list']/a")
	WebElement RefreshInterval;
	
	// Field names used for webdriver findElement
	String iframeTag = "iframe";
    String srchString = "a[title=";
    String inputTagName = "input";
    String uploadTagStr = "UploadTags";
    String clearTagStr = "Clear Tags";
    String fileUpload = "selectFileToUpload"; 
    String switchListName = "ul.dropdown-menu li";
	String insightCountWidget =  "div.metric-value.ng-binding";
	String countIconsId = "div.metric-value.ng-binding";
	String collectorListId = "div.panel-heading.mirror-head";
	
	public VCFIaIndexPage(WebDriver driver) {
		super(driver);
	}
	
	//HCS: Click on Connection, load the Connection Page content in VCFC Application
	public void Connections() {
		waitForElementVisibility(Connections,1000);
		Connections.click();
		}
	
	//HCS: Click on Traffic, load the Traffic Page content in VCFC Application
	public void Traffic(){
		waitForElementVisibility(Traffic,1000);
		Traffic.click();
		}
	
	//HCS: Click on VMwareConnections, load the VMwareConnections Page content in VCFC Application
	public void VMwareConnections() {
		waitForElementVisibility(VMwareConnections,1000);
		VMwareConnections.click();
		}
	
	//HCS: Click on VMwareTraffic, load the VMwareTraffic Page content in VCFC Application
	public void VMwareTraffic() {
		waitForElementVisibility(VMwareTraffic,1000);
		VMwareTraffic.click();
		}
	
	//HCS: Click on PortStatus, load the PortStatus Page content in VCFC Application
	public void PortStatus() {
		waitForElementVisibility(PortStatus,1000);
		PortStatus.click();
		}
	
	//HCS: Click on Report, load the Report Page content in VCFC Application
	public void report() {
		waitForElementVisibility(report,1000);
		report.click();
		}
	
	//HCS: Click on SearchString1, Submit the SearchString provided in Search Box
	public void searchString1() {
		waitForElementVisibility(searchString1,1000);
		searchString1.click();
		}
	
	
	public void InsightTopL4ServicesbyConnections() {
		driver.switchTo().frame("iframe");
		waitForElementVisibility(InsightTopL4ServicesbyConnections,30000);
		InsightTopL4ServicesbyConnections.isDisplayed();
		}
	
	public void InsightTopClientsbyConnections() {
		waitForElementVisibility(InsightTopClientsbyConnections,30000);
		InsightTopClientsbyConnections.click();
		}
	
	public void InsightL4ServicesCount() {
		waitForElementVisibility(InsightL4ServicesCount,30000);
		InsightL4ServicesCount.click();
		}
	
	public void UniquecountsofL4Service() {
		driver.switchTo().frame("iframe");
		waitForElementVisibility(UniquecountsofL4Service,30000);
		UniquecountsofL4Service.getText();
		String UniquecountL4Service = UniquecountsofL4Service.getText();
		System.out.println(UniquecountL4Service);
		}
	
	public void InsightConnectionCount() {
		waitForElementVisibility(InsightConnectionCount,30000);
		InsightConnectionCount.click();
		}
	
	public void InsightTopDomainsbyConnections() {
		waitForElementVisibility(InsightTopDomainsbyConnections,30000);
		InsightTopDomainsbyConnections.click();
		}
	
	public void InsightTopServersbyConnections() {
		waitForElementVisibility(InsightTopServersbyConnections,30000);
		InsightTopServersbyConnections.click();
		}
	
	public void TopServersbyTotalUniqueClients() {
		waitForElementVisibility(TopServersbyTotalUniqueClients,30000);
		TopServersbyTotalUniqueClients.click();
		}
	
	public void InsightConnectionsbyState() {
		waitForElementVisibility(InsightConnectionsbyState,30000);
		InsightConnectionsbyState.click();
		}
	
	public void TopSwitchesbyTotalNoofConnections() {
		waitForElementVisibility(TopSwitchesbyTotalNoofConnections,30000);
		TopSwitchesbyTotalNoofConnections.click();
		}
		
	//HCS:Changes the Frame to iframe and look for Search Box
	public void searchBox(){
		waitForElementVisibility(driver.findElement(By.tagName(iframeTag)),1000);
		driver.switchTo().frame(driver.findElement(By.tagName(iframeTag)));	
		waitForElementVisibility(searchBox,1000);
		searchBox.isDisplayed();
		}
	
	//HCS: Click on tagIcon, load the CustomTag Page content in VCFC Application
	public void tagIcon(){
		waitForElementVisibility(tagIcon,3000);
		tagIcon.click();
		}
	
	//HCS: Click on uploadTags , Opens the Pop-UP for Upload Project Tags File
	public void uploadTags() throws Exception{
		waitForElementVisibility(uploadTags,4000);
		uploadTags.click();
		}
	
	//HCS:Click on selectFileToUpload Opens the Pop-UP for File Path Selection
	public void selectFileToUpload(){
		waitForElementVisibility(selectFileToUpload,2000);
		selectFileToUpload.click();
		}
	
	public void Cancel(){
		waitForElementVisibility(Cancel,3000);
		Cancel.click();
		}
	
	//HCS: Click on UNUMHome, load the VCFC Home Page
	public void UNUMHome(){
		waitForElementVisibility(UNUMHome,3000);
		UNUMHome.click();
		}
	
	//HCS: Click on UNUMSeeting, opens the VCFC Setting Page
	public void UNUMSetting(){
		waitForElementVisibility(UNUMSetting,2000);
		UNUMSetting.click();
		}
	
	//HCS: Click on CollectorManagement, opens the CollectorSetting Page
	public void CollectorManagement(){
		waitForElementVisibility(CollectorManagement,2000);
		CollectorManagement.click();
		}
	
	//HCS: Click on AddNetvisorCollector, opens the Pop-UP for AddNetvisor Collector
	public void AddNetvisorCollector(){
		waitForElementVisibility(AddNetvisorCollector,1000);
		AddNetvisorCollector.click();
		}
	
	//HCS: CLick on TimeSelector opens the Pop-UP for Selection of Time Range
	public void TimeSelector(){
		waitForElementVisibility(driver.findElement(By.tagName(iframeTag)),1000);
		driver.switchTo().frame(driver.findElement(By.tagName(iframeTag)));	
		waitForElementVisibility(TimeSelector,1000);
		TimeSelector.click();
		}
	
	//HCS:To Find the TimeRange Quick
	public void TimeRangeQuick(){
		waitForElementVisibility(TimeRangeQuick,2000);
		TimeRangeQuick.isDisplayed();
		}
	
	public void quickTimeSearch() {
		TimeSelector();
		TimeRangeQuick();
		QuickTimeRange();
	}
	
	//HCS: Get All the Time Interval Available in QuickTime Range
	public void QuickTimeRange(){
		waitForElementVisibility(QuickTimeRange,1000);
		QuickTimeRange.isDisplayed();
		List <WebElement> allElement = driver.findElements(By.xpath("(//div[@class='kbn-timepicker-section']/descendant::li[@ng-repeat='option in list']/a)"));
		// allElement.forEach(element -> Reporter.log("=QuickTimeRange " + element.getText() + "=", true) );		
		for(WebElement element: allElement) {
			Reporter.log("QuickTimeRange " + element.getText(), true);
			}
	}
	
	//HCS:IntervalSelector Pop-Up gets Open
	public void IntervalSelector(){
		waitForElementVisibility(driver.findElement(By.tagName(iframeTag)),1000);
		driver.switchTo().frame(driver.findElement(By.tagName(iframeTag)));	
		waitForElementVisibility(IntervalSelector,1000);
		IntervalSelector.click();
	}
	
	//HCS: Get All Time Range Available for Refresh
	public void RefreshInterval() throws InterruptedException {
		waitForElementVisibility(RefreshInterval,1000);
		RefreshInterval.isDisplayed();
		List <WebElement> allElement = driver.findElements(By.xpath("(//li[@ng-repeat='inter in list']/a)"));
		// allElement.forEach(element -> Reporter.log("=RefreshInterval " + element.getText() + "=", true) );		
		for(WebElement element: allElement) {
			Reporter.log("RefreshInterval " + element.getText(), true);
			      Thread.sleep(1000);
			      	     driver.close();
			
			//String popupHandle = "Refresh Interval";
			//driver.switchTo().window(popupHandle);
            //System.out.println("Pop Up Title: "+ driver.switchTo().window(popupHandle).getTitle());
            //driver.close();
			
			//WebDriverWait wait = new WebDriverWait(driver, (30));
	        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='tab-pane active']"))); 
	        //driver.findElement(By.xpath("//div[@class='tab-pane active']")).quit();

			
			}
		}

	public void gotoIADashboard() {
		dashboardIcon.click();
		waitForElementVisibility(driver.findElement(By.tagName(iframeTag)),1000);
		}
	
	public List<WebElement> getInsightCount() {
		List<WebElement> rows = new ArrayList();
		dashboardIcon.click();
		waitForElementVisibility(driver.findElement(By.tagName(iframeTag)),1000);
		driver.switchTo().frame(driver.findElement(By.tagName(iframeTag)));	
		retryingFindClick(By.cssSelector(countIconsId));
		rows = driver.findElements(By.cssSelector(insightCountWidget));
		return rows;
		}
	
	public void applySearchFilter(String searchString) {
		waitForElementVisibility(searchBox,100);
		setValue(searchBox,searchString);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		boolean existsOn = false;
		existsOn = (driver.findElements(By.cssSelector(srchString+"'"+searchString+"'")).size() != 0);
		if(existsOn) {
			
		}
	}
	public int getConnectionCount() {
		int connCount = 0;
		List <WebElement> rows = getInsightCount();
			if(!rows.isEmpty()) {
				String connOutput = rows.get(0).getText();
				if(StringUtils.contains(connOutput, ',')) {
					connOutput = StringUtils.remove(connOutput, ',');
				}
				if(connOutput.equals("")) {
					connCount = 0;
				} else {
					connCount = Integer.parseInt(connOutput);
				}
			}
			driver.switchTo().defaultContent();
		return connCount;
	}
	
	public int getAppCount() {
		int connCount = 0;
		List <WebElement> rows = getInsightCount();
			if(!rows.isEmpty()) {
				String connOutput = rows.get(1).getText();	
				if(StringUtils.contains(connOutput, ',')) {
					connOutput = StringUtils.remove(connOutput, ',');
				} 
				if(connOutput.equals("")) {
					connCount = 0;
				} else {
					connCount = Integer.parseInt(connOutput);
				}
			}
			driver.switchTo().defaultContent();	
		return connCount;
	}
	
	
	
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}	     
			//String checkText = driver.findElement(By.id("message")).getText();
			//Assert.assertEquals("File uploaded successfully", checkText);	
		
		
		
		public WebElement findAnchorTags(String anchorText) {
			List <WebElement> anchorTags = driver.findElements(By.cssSelector("a"));
			WebElement returnRow = null;
			for (WebElement row:anchorTags) {
				if(row.getText().equalsIgnoreCase(anchorText)) {
					returnRow = row;
					break;
				}
			}
			return returnRow;
		}	
	}


		
		
	
	    
	

	

	

	
	

	

	
  
