package com.PlanIT.WitsLab.tests;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.PlanIT.WitsLab.core.ConfigReader;
import com.PlanIT.WitsLab.core.ReadEmails;
import com.PlanIT.WitsLab.json.JsonParser;
import com.PlanIT.WitsLab.pages.LoginPage;
import com.PlanIT.WitsLab.pages.ProjectAndBoardDashboard;
import com.PlanIT.WitsLab.ui.selenium.DriverActions;
import com.PlanIT.WitsLab.ui.selenium.DriverFactory;
import com.PlanIT.WitsLab.ui.selenium.SeleniumConfigs;


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


	protected Logger logger = LogManager.getLogger(BaseTestSuite.class.getName());
	protected static final String THREADCONTEXT_ROUTINGKEY = "RoutingKey";
	protected ReadEmails readEmail = new ReadEmails();

	protected static DriverActions das ;

	public JSONObject loginAndSignUp;
	public JSONObject createIssue;
	public JSONObject CreateProjectAndBoard;


	@BeforeSuite
	public void createObject() {
		das=new DriverActions();
		das.createReport();
	}

	/**
	 * Create an instance of webdriver and initiate all page
	 * 
	 * @param browser
	 * @param locale
	 * @throws Exception
	 */
	@Parameters({"browser"})
	@BeforeTest(alwaysRun = true)
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
			//			driver = DriverFactory.getRemoteInstance(browser);
			logger.info("WEBDRIVER IS STARTING ON REMOTE SESSION");
		} else {
			das.getLocalInstance(browser);
			logger.info("WEBDRIVER IS STARTING ON LOCAL SESSION");
			//			das.getWebDriver().get("https://projectmanagement-uat.thewitslab.com/");;
			String url=das.getProperties().getProperty("url");

			das.getWebDriver().get(url);;

		}
	}

	/**
	 * Quit the driver, once the tests are completed
	 */


	/**
	 * This clears the ThreadContextMap
	 */
	@AfterTest(alwaysRun = true)
	public void afterTestThreadContextCleanup() {
		//		ThreadContext.clearMap();
		das.closeBrowser();
		das.generateReport();
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

		createIssue = JsonParser.parse("issue.json");
		CreateProjectAndBoard=JsonParser.parse("projectAndBoard.json");
		loginAndSignUp = JsonParser.parse("loginAndSignUp.json");


		String testCaseName=tsName.getName();
		das.createTest(testCaseName);

		LoginPage lp=new LoginPage(das)	;
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println(lp.loginPageHeader.getText());

		lp.inputEmail_signInPage(loginAndSignUp.getJSONObject("gauravLogin").getString("Email"));
		lp.inputPassword_signInPage(loginAndSignUp.getJSONObject("gauravLogin").getString("Password"));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lp.clickOnLogin_signInPage();
		try {
			Thread.sleep(10000);
			System.out.println("SignIn Successfull");

			//			das.webDriverWait(new ProjectAndBoardDashboard(das).projectHeader);
			System.out.println("Project Header visible");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String url=das.getBrowserURL();
		System.out.println(url);
		Assert.assertEquals(url,"https://projectmanagement-uat.thewitslab.com/");

		//		ProjectAndBoardDashboard project=new ProjectAndBoardDashboard(das);
		//		das.webDriverWait(project.projectHeader);



	}

	@AfterMethod(alwaysRun = true)
	public void logout(ITestResult result,Method m) {
		int status=result.getStatus();
		if(status==ITestResult.FAILURE) {
			String imgPath=das.takeScreenShot(m.getName());
			das.attachsnapshotToextentReport(imgPath);

		}

		LoginPage lp=new LoginPage(das)	;
		lp.clickOnLogoutBtn();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			das.webDriverWait(lp.loginPageHeader);
			boolean bb=das.isDisplayed(lp.loginPageHeader);
			//			System.out.println(lp.loginPageHeader.getText());
			if(bb) {

			}else {
				ProjectAndBoardDashboard pboard=new ProjectAndBoardDashboard(das);
				if(das.isDisplayed(pboard.closePopup)) {
					pboard.popupClose();
					lp.clickOnLogoutBtn();
				}

			}

		}catch (Exception e) {
			das.refresh();
			//			lp.clickOnLogoutBtn("Logout");
		}

		das.generateReport();
	}

	@AfterSuite
	public void generateReport() {
		das.generateReport();
	}
}
