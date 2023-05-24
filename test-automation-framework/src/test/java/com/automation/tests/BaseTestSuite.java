package com.automation.tests;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.automation.core.ConfigReader;
import com.automation.core.ReadEmails;
import com.automation.ui.selenium.DriverActions;
import com.automation.ui.selenium.DriverFactory;
import com.automation.ui.selenium.SeleniumConfigs;
import com.automation.web.components.pages.LoginPage;
import com.automation.web.components.pages.ProjectAndBoardDashboard;
import com.automation.web.json.JsonParser;

import junit.framework.Assert;

/**
 * This function defines the core selenium actions, 
 * 		@BeforeClass - Before every class, creating a new webDriver instance
 * 		@AfterClass -  Quit the WebDriver when the test is completed. 
 * 
 * All the respective webPage related classes (which contain the functions specific to that particular webPage) are also initiated here, in the setUp function
 * 
 * This class needs to be extended, for every new automation testClass
 *
 */
public class BaseTestSuite{




	protected WebDriver driver;
	protected Logger logger = LogManager.getLogger(BaseTestSuite.class.getName());
	protected static final String THREADCONTEXT_ROUTINGKEY = "RoutingKey";
	protected ReadEmails readEmail = new ReadEmails();

	protected DriverActions das ;

	public JSONObject loginAndSignUp;
	public JSONObject createIssue;
	public JSONObject CreateProjectAndBoard;


	@BeforeSuite
	public void createObject() {
		//das=new DriverActions(driver);
	}

	/**
	 * Create an instance of webdriver and initiate all page
	 * 
	 * @param browser
	 * @param locale
	 * @throws Exception
	 */
	@Parameters({"browser"})
	@BeforeClass (alwaysRun = true)
	public void setUp(@Optional("Chrome") String browser) throws Exception{
		/**
		 * this sets ThreadContext map with key THREADCONTEXT_ROUTINGKEY and value from browser param.
	    this is needed despite setting ThreadContext in beforeTest and beforeMethod. When classes are run 
	    parallel, thread pool executing classes in parallel does not inherit ThreadContext set in beforeTest 
	    at test initialization. Log messages coming from other beforeClass methods such as login and 
	    feature flip needs to separated based on browser as well. 
		 */
		ThreadContext.put(THREADCONTEXT_ROUTINGKEY, browser.toLowerCase());
		String remoteSession = ConfigReader.getConfiguration(SeleniumConfigs.WEBDRIVER_REMOTE_PROPERTY, SeleniumConfigs.WEBDRIVER_REMOTE_DEFAULT_VALUE);
		if (remoteSession.equalsIgnoreCase("true")) {
			driver = DriverFactory.getRemoteInstance(browser);
			logger.info("WEBDRIVER IS STARTING ON REMOTE SESSION");
		} else {
			driver      = DriverFactory.getLocalInstance(browser);
			logger.info("WEBDRIVER IS STARTING ON LOCAL SESSION");


			das=new DriverActions(driver);
			das.createReport();

			createIssue = JsonParser.parse("issue.json");
			CreateProjectAndBoard=JsonParser.parse("projectAndBoard.json");
			loginAndSignUp = JsonParser.parse("loginAndSignUp.json");

		}
	}

	/**
	 * Closes the current Session
	 */
	protected void quitSession() {
		driver.close();
	}

	/**
	 * Quit the driver, once the tests are completed
	 */

	//	@AfterClass	(alwaysRun = true)
	public void tearDown() {
		try {
			if (driver != null) 
				driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ThreadContext.clearMap();
		}

	}

	/**
	 * This is run before any xml test is run and set a thread context with key THREADCONTEXT_ROUTINGKEY
	 * and value is from browser param. This is needed despite setting ThreadContext in beforeClass 
	 * and beforeMethod to separate logs based on browser happening before test class initialization 
	 * For e.g. some logs in TestListener and ConfigReader are run before test class initialized but we know
	 * already that test class will be initialized for execution in specific browser depending on params in 
	 * xml file or jvm args
	 * 
	 * @param browser
	 */
	@BeforeTest(alwaysRun = true)
	@Parameters({ "browser" })
	public void beforeTestThreadContextSetup(@Optional("Chrome") String browser) {
		//ThreadContext.put(THREADCONTEXT_ROUTINGKEY, browser.toLowerCase());
	}

	/**
	 * This clears the ThreadContextMap
	 */
	@AfterTest(alwaysRun = true)
	public void afterTestThreadContextCleanup() {
		ThreadContext.clearMap();
	}

	/**
	 * This is run before any test method is run and set a thread context with key THREADCONTEXT_ROUTINGKEY
	 * and value is from browser param. This is needed despite of setting ThreadContext in beforeClass and
	 * beforeTest. When methods are run parallel (instead of class or tests) then the threadpool executing 
	 * methods in parallel does not have/inherit the context map of parent thread. Hence each method
	 * 
	 * @param browser
	 * 
	 */

	@BeforeMethod(alwaysRun = true)
	public void verifyloginPage(Method tsName) {

		String testCaseName=tsName.getName();
		das.createTest(testCaseName);

		LoginPage lp=new LoginPage(das)	;
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		lp.inputEmail_signInPage(loginAndSignUp.getJSONObject("gauravLogin").getString("Email"));
		lp.inputPassword_signInPage(loginAndSignUp.getJSONObject("gauravLogin").getString("Password"));


		lp.clickOnLogin_signInPage();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String url=das.getBrowserURL();
		System.out.println(url);
//		Assert.assertEquals(url,"https://projectmanagement-uat.thewitslab.com/");
		
		ProjectAndBoardDashboard project=new ProjectAndBoardDashboard(das);
		das.webDriverWait(project.projectHeader);



	}

	@AfterMethod(alwaysRun = true)
	public void logout(ITestResult result,Method m) {
		int status=result.getStatus();
		if(status==ITestResult.FAILURE) {
			String imgPath=das.takeScreenShot(m.getName());
			das.attachsnapshotToextentReport(imgPath);

		}

		LoginPage lp=new LoginPage(das)	;
		lp.clickOnLogoutBtn("Logout");
		try {
		das.isDisplayed(lp.loginPageHeader);
		}catch (Exception e) {
			lp.clickOnLogoutBtn("Logout");
		}
		
		das.generateReport();
	}
}
