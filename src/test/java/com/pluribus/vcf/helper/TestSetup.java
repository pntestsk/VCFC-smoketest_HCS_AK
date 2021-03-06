/**
* COPYRIGHT 2012-2017 Pluribus Networks Inc.
*
* All rights reserved. This copyright notice is Copyright Management
* Information under 17 USC 1202 and is included to protect this work and
* deter copyright infringement.  Removal or alteration of this Copyright
* Management Information without the express written permission from
* Pluribus Networks Inc is prohibited, and any such unauthorized removal
* or alteration will be a violation of federal law.
*/
package com.pluribus.vcf.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;
import static org.testng.Assert.assertTrue;
import com.browserstack.local.Local;
import com.jcabi.ssh.Shell;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.jcabi.ssh.SSHByPassword;

/**
 *
 * @author Haritha, Poornima
 */
public class TestSetup {
	protected RemoteWebDriver driver;
	private ResourceBundle bundle;
	Local bsLocal = new Local();
	private String localId;
	private String imageName;
	
    //HCS: Commented Some of the Code to avoid Install and Upgrade for Testing purpose
	
	/*
	 * This method will be invoked if VCFC upgrade is desired before test run.
	 */
	/*
	@Parameters({ "vcfIp", "upgrade", "jenkins_build_number", "vcfc_version", "buildNum", "folder_name" })
	@BeforeSuite(alwaysRun = true)
	public boolean upgradeVCFC(String vcfIp, @Optional("0") String upgrade, @Optional("") String jenkins_build_number,
			@Optional("") String vcfc_version, @Optional("") String build_number, @Optional("") String folder_name)
			throws Exception {
		boolean upgradeStatus = true;
		if (Integer.parseInt(upgrade) == 1) {
			Shell sh1 = new Shell.Verbose(new SSHByPassword(vcfIp, 22, "vcf", "changeme"));
			String out1;
			imageName = "vcf-" + vcfc_version + "-" + jenkins_build_number + ".tgz";
			String imageUrl = "http://sandy:8081/artifactory/Maestro/" + folder_name + "/" + build_number + "/"
					+ imageName;
			String wgetOutput= new Shell.Plain(sh1).exec("cd /srv/docker/offline_images;wget " + imageUrl);
			if(wgetOutput.contains("ERROR 404: Not Found")) {
				printLogs("error","upgradeVCFC","Image upgrade failed");
				upgradeStatus = false;
				throw new Exception ("Image upgrade failed");
			}
			if(upgradeStatus == true) {
				out1 = new Shell.Plain(sh1).exec("/home/vcf/VCFC_setup.sh upgrade " + imageName);
				Thread.sleep(30000);
			}
		}
		return upgradeStatus;
	}

	/*
	 * This method is usually invoked unless user overrides it with clean=0 in
	 * arguments with mvn clean test It deletes config files such as pcap-agent,
	 * pcap-engine, vcf-center properties, seed switch, username, license etc.
	 * This is run so that the user starts with a clean VM. If this isn't
	 * desired, then test class should be modified to use admin/new Password for
	 * login instead of admin/admin.
	 */
	/*
	@Parameters({ "vcfIp", "clean", "imageType" })
	@BeforeClass(alwaysRun = true)
	public void cleanLogs(String vcfIp, @Optional("1") String clean, @Optional("test") String imageType)
			throws IOException, InterruptedException {
		if (Integer.parseInt(clean) == 1) {
			Shell sh1 = getVcfShell(vcfIp);
			String out1;
			String path = "rm /home/vcf/srv/vcf/config/";
			String[] restartCommands = new String[2];
			if (imageType.equalsIgnoreCase("test")) {
				restartCommands[0] = "/home/vcf/srv/vcf/bin/stop-vcfc.sh";
				restartCommands[1] = "/home/vcf/srv/vcf/bin/start-vcfc.sh";
			}
			if (imageType.equalsIgnoreCase("dev")) {
				path = "docker exec -i vcf-center rm /srv/vcf/config/";
				restartCommands[0] = "docker stop vcf-center vcf-collector vcf-mgr vcf-elastic skedler";
				restartCommands[1] = "docker start vcf-center vcf-collector vcf-mgr vcf-elastic skedler";
			}
			out1 = new Shell.Plain(sh1).exec(path + "flowfilters.json");
			out1 = new Shell.Plain(sh1).exec(path + "pcap-agent.properties");
			out1 = new Shell.Plain(sh1).exec(path + "pcap-engine.json");
			out1 = new Shell.Plain(sh1).exec(path + "vcf-center.properties");
			out1 = new Shell.Plain(sh1).exec(path + "pcap_agents.properties");
			out1 = new Shell.Plain(sh1).exec(path + "switch-details.json");
			out1 = new Shell.Plain(sh1).exec(path + "vcf-license.json");
			out1 = new Shell.Plain(sh1).exec(path + "vcf-user.properties");
			out1 = new Shell.Plain(sh1).exec(path + "vcf-maestro.properties");
			out1 = new Shell.Plain(sh1).exec(path + "es_nodes.properties");
			out1 = new Shell.Plain(sh1).exec(path + "es_node.json");
			out1 = new Shell.Plain(sh1).exec(path + "vcf-collector.json");

			// Restart VCFC
			for (String s : restartCommands) {
				out1 = new Shell.Plain(sh1).exec(s);
			}
			Thread.sleep(180000);// Sleeping after clean logs
		}
	}

	public Shell getVcfShell(String vcfIp) {
		Shell sh1 = null;
		try {
			sh1 = new Shell.Verbose(new SSHByPassword(vcfIp, 22, "vcf", "changeme"));
		} catch (Exception e) {
			printLogs("error", "getVcfShell", e.toString());
		}
		return sh1;
	}
    */
    
	/*
	 * Routine for logging. So any changes to this routine will be a single
	 * point of edit for all log messages All logging should use printLogs
	 * method Mandatory arguments: Level, method which is logging the message
	 * and actual message
	 */
	public void printLogs(String level, String methodName, String msg) {
		if (level.equalsIgnoreCase("error")) {
			com.jcabi.log.Logger.error(methodName, msg);
		}
		if (level.equalsIgnoreCase("info")) {
			com.jcabi.log.Logger.info(methodName, msg);
		}
		System.out.println(level + ": " + methodName + " " + msg);
	}

	/*
	 * This is the method that starts the WebDriver either with browserstack or
	 * local based on the "local" argument value Mandatory argument: vcfIp,
	 * browser (Name of browser: "chrome", "firefox") etc. For local runs only
	 * chrome is supported now. Optional arguments: local, bsUserId, bsKey,
	 * jenkins Note: if jenkins plugin is used, handling is a little different
	 * from the other runs. jenkins = 1 is set by all jobs that run scripts from
	 * jenkins. End of Note.
	 */
	@Parameters({ "vcfIp", "browser", "local", "bsUserId", "bsKey", "jenkins" })
	@BeforeClass(alwaysRun=true)
	public void initDriver(String vcfIp, String browser, @Optional("0") String local,
			@Optional("pratikdam1") String bsUserId, @Optional("uZCXEzKXwgzgzMr3G7R6") String bsKey,
			@Optional("0") String jenkins) throws Exception {
		if (Integer.parseInt(local) == 1) {
			startDriver(vcfIp, browser);
		} else {
			startDriver(vcfIp, browser, bsUserId, bsKey, Integer.parseInt(jenkins)); 
		}
	}

	public void startDriver(String vcfIp, String browserName) {
		String platform = null;
		String os = System.getProperty("os.name").toLowerCase();
		String chrDriver = null;

		if (os.contains("win")) {
			chrDriver = "src/test/resources/chromedriver_win";
		} else if (os.contains("mac")) {
			chrDriver = "src/test/resources/chromedriver_mac";
		} else if (os.contains("linux")) {
			chrDriver = "src/test/resources/chromedriver_linux64";
		}

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", chrDriver);
			DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
			chromeCaps.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
			chromeCaps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(chromeCaps);
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("https:" + vcfIp);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
			DesiredCapabilities caps = DesiredCapabilities.firefox();
			caps.setCapability("marionette", true);
			caps.setCapability("acceptInsecureCerts", true);
			// var capabilities = new FirefoxOptions().addTo(caps);
			System.out.println("Capabilities:" + caps.toString());
			driver = new FirefoxDriver(caps);
			driver.get("https:" + vcfIp);
			System.out.println("title" + driver.getTitle());
		}
		// TODO: ADD Firefox, IE AND SAFARI TO THE LIST
	}

	public void startDriver(String vcfIp, String browser, String bsUserId, String bsKey, int jenkins) throws Exception {
		String sessionId = null;
		String command = null;
		HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
		String dateAsString = simpleDateFormat.format(new Date());
		String localId = "convergenceTest" + dateAsString;
		if (jenkins == 0) {
			bsLocalArgs.put("localIdentifier", localId); // environment variable
			bsLocalArgs.put("key", bsKey); // BrowserStack Key
			bsLocalArgs.put("v", "true");
			bsLocal.start(bsLocalArgs);
		}

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", browser);
		caps.setCapability("build", "VCFC SmokeTest Cases");
		caps.setCapability("acceptSslCerts", "true");
		caps.setCapability("acceptInsecureCerts", true);
		caps.setCapability("browserstack.debug", "true");
		caps.setCapability("browserstack.idleTimeout", "150");
		caps.setCapability("platform", "ANY");

		caps.setCapability("browserstack.console", "verbose");
		if (browser.equalsIgnoreCase("firefox")) {
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("security.tls.insecure_fallback_hosts", false);
			firefoxProfile.setAcceptUntrustedCertificates(true);
			caps.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
			caps.setCapability("browser_version", "54");
		}
		if (browser.equalsIgnoreCase("chrome")) {
			caps.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
		}
		if (jenkins == 0) {
			caps.setCapability("browserstack.local", "true");
			caps.setCapability("browserstack.localIdentifier", localId);
		} else {
			String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
			String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
			caps.setCapability("browserstack.local", browserstackLocal);
			caps.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);
		}
		driver = new RemoteWebDriver(
				new URL("https://" + bsUserId + ":" + bsKey + "@hub-cloud.browserstack.com/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		// Get a handle to the driver. This will throw an exception if a
		try {
			driver.get("https://" + vcfIp);
		} catch (Exception e) {
			printLogs("error", "getDriver", "Unable to open connection to " + vcfIp);
		}
	}

	/*
	 * This method is invoked when a browserstack session is created to retrieve
	 * and print out link to the logs in the console output.
	 * 
	 */
	public String getBSLogs(String bsUserId, String bsKey) {
		String sessId = driver.getSessionId().toString();
		String url = "https://browserstack.com/automate/sessions/" + sessId + ".json";
		String authUser = bsUserId + ":" + bsKey;
		String encoding = Base64.encodeBase64String(authUser.getBytes());
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json").header("Authorization", "Basic " + encoding)
				.get(ClientResponse.class);
		if (resp.getStatus() != 200) {
			printLogs("error", "getBSLogs", "Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		JSONObject obj = new JSONObject(output);
		JSONObject bsLogs = (JSONObject) obj.get("automation_session");
		String publicUrl = bsLogs.get("public_url").toString();
		return publicUrl;
	}

	/*
	@Parameters({ "jenkins", "upgrade", "vcfIp" })
	@AfterClass(alwaysRun = true)
	public void setupAfterSuite(@Optional("0") String jenkins, @Optional("0") String upgrade, String vcfIp) {
		try {
			if (Integer.parseInt(upgrade) == 1) {
				Shell sh1 = getVcfShell(vcfIp);
				String out1 = new Shell.Plain(sh1).exec("cd /srv/docker/offline_images;rm " + imageName);
			}
			driver.quit();
			if (Integer.parseInt(jenkins) == 0) {
				bsLocal.stop();
			}
		} catch (Exception e) {
			printLogs("info", "setupAfterSuite", "driver already closed");
		}
	}
    */
	public boolean isContainsText(String text) {
		return driver.getPageSource().contains(text);
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void pageRefresh() {
		driver.navigate().refresh();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String isRequired(String field) {
		String isrequired = getBundle().getString("errors.required").replace("{0}", field);
		return isrequired;
	}

	public String matchPattern(String field) {
		String isrequired = getBundle().getString("errors.required").replace("{0}", field);
		return isrequired;
	}
}
