package com.PlanIT.WitsLab.ui.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.PlanIT.WitsLab.util.Util;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import cucumber.runtime.Timeout;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class has wrapper functions for any Browser actions that can be taken on a web page like click, sendKeys, openUrl, etc,.
 * 
 * @author rmathews
 *
 */

public class DriverActions{

	//	private static Logger  logger = LogManager.getLogger(DriverFactory.class.getName());
	//	
	private static Logger  logger1 = LogManager.getLogger(DriverFactory.class.getName());
	protected WebDriver driver;
	protected Wait<WebDriver> webDriverWait;
	public Logger logger = LogManager.getLogger();
	private ArrayList<String> tabs;
	public static final long DEFAULT_WAIT_TIME = 20;
	public static final long POOLING_WAIT_TIME = 2;
	protected Actions actions;

	private ExtentReports er;
	public ExtentTest etest;
	private Properties prop;

		public DriverActions(WebDriver webDriver) {
			this.driver = webDriver;
			actions = new Actions(webDriver);
			this.webDriverWait = new FluentWait<WebDriver>(driver)                            
					.withTimeout(Duration.ofSeconds(DEFAULT_WAIT_TIME))          
					.pollingEvery(Duration.ofSeconds(POOLING_WAIT_TIME))          
					.ignoring(NoSuchElementException.class);
		}


	public  String openUrl() {


		String url= getProperties().getProperty("url");
		try {
			driver.get(url);
			//		    	String url="https://projectmanagement-uat.thewitslab.com/";
			logger1.info("The URL is " + url);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	//headless browser 
	//		    public static WebDriver getChromeDriver() {
	//		        ChromeOptions chromeOptions = new ChromeOptions();
	//		        
	////		        chromeOptions.addArguments("--headless");
	//		        chromeOptions.addArguments("--incognito");
	//		        chromeOptions.addArguments("--start-fullscreen");
	//		        return new ChromeDriver(chromeOptions);
	//		    }

	public static WebDriver getFirefoxDriver() {
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setLegacy(false); 
		//		        firefoxOptions.setProfile(getFirefoxProfileForAutomaticDownload());
		return new FirefoxDriver(firefoxOptions);
	}

	public static WebDriver getEdgeChromiumDriver() {
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver();
	}






	public void getLocalInstance(String browser) throws Exception {

		if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = getFirefoxDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			//            driver = getChromeDriver();
			ChromeOptions chromeOptions = new ChromeOptions();
//			chromeOptions.setBinary("/Applications/Brave.app");
			
//	        chromeOptions.addArguments("--headless");
	        
	        
//			chromeOptions.addArguments("--incognito");
//			chromeOptions.addArguments("--start-fullscreen");

			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--window-size=1936,1080");
//			chromeOptions.addArguments("--headless");
			
			//            chromeOptions.addArguments("--headless"); //for headless mode
			//            chromeOptions.addArguments("--window-size=800,600");//The invisible browser window is only 800x600 in size
			//            chromeOptions.addArguments("--start-fullscreen");
//			            chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
//			            chromeOptions.addArguments("disable-infobars"); // disabling infobars
//			            chromeOptions.addArguments("--disable-extensions"); // disabling extensions
//			            chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
//			            chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//			            chromeOptions.addArguments("--no-sandbox");


			driver= new ChromeDriver(chromeOptions);

		} else if (browser.equalsIgnoreCase("EdgeC") || browser.equalsIgnoreCase("EdgeChromium")) {
			driver = getEdgeChromiumDriver();
		}else if(browser.equalsIgnoreCase("safari")){
			 driver = new SafariDriver();
		}
		

		driver.manage().window().maximize();

		// Setting Driver Implicit Time out for An Element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
		//        String url=openUrl();
		//        driver.navigate().to("https://projectmanagement-uat.thewitslab.com/");
		//        driver.get("https://projectmanagement-uat.thewitslab.com/");
		//        

	}



	public WebDriver getWebDriver() {
		return driver;
	}
	public Properties getProperties() {
		return prop;
	}

	public ExtentTest etest() {
		return etest;
	}

	public void createReport() {

		ExtentHtmlReporter ereport=new ExtentHtmlReporter("extentReport//report.html");
		ereport.config().setReportName("PlanIT");
		ereport.config().setTheme(Theme.DARK);
		er=new ExtentReports();
		er.attachReporter(ereport);

	}

	public void createTest(String tsName) {
		etest=er.createTest(tsName);

	}
	public void generateReport() {
		er.flush();
	}

	public void moveToElementAndClick(WebElement we) {
		actions = new Actions(driver);
		actions.moveToElement(we).click().build().perform();

	}

	public String takeScreenShot(String snapshotName) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String dateAndTime=dateAndTime();
		//		File destination=new File("extentReport/"+snapshotName+dateAndTime+".png");
		//		File destination=new File("/Users/gauravdubey/Documents/git/abc.png");
		File destination=new File(System.getProperty("user.dir")+"/extentReport/"+snapshotName+".png");


		try {
			Files.copy(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destination.getAbsolutePath();
	}

	public void attachsnapshotToextentReport(String imgPath) {

		try {
			etest.addScreenCaptureFromPath(imgPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String dateAndTime() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();  
		String dateTime= formatter.format(date);  
		return dateTime;

	}

	public void uiText_validation(String actualText,String expectedText) {
		try {
			if(actualText.equals(expectedText)) {
				etest.log(Status.PASS, "actualText "+actualText+" and expectedText "+expectedText+" matched");
			}else {
				etest.log(Status.FAIL, "actualText "+actualText+" and expectedText "+expectedText+" not matched");
			}
		}catch(Exception e) {
			etest.log(Status.FAIL, "actualText "+actualText+" and expectedText "+expectedText+" not matched");

		}
	}

	public void dragAndDropByHold(WebElement source,WebElement target) {
		actions.clickAndHold(source).moveToElement(target).release(target).build().perform();
	}

	public String getAlertMessage() {

		return driver.switchTo().alert().getText();
	}

	public void webDriverWait(WebElement we) {

		WebDriverWait wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(we));

	}

	public void inputData(WebElement we,String data,String elementName) {

		try{
//			we.clear();
			we.sendKeys(data);
			etest.log(Status.INFO, data+" is inputed in "+elementName);


		}catch (ElementNotInteractableException e) {
			Actions ac =new Actions(driver);
			ac.sendKeys(we,data).build().perform();
			etest.log(Status.INFO, data+" is inputed in "+elementName);

		}catch (Exception e) {

			JavascriptExecutor js= (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value["+data+"]", we);
			etest.log(Status.INFO, data+" is inputed in "+elementName);
		}

	}
	
	public void javaScriptSendKey(WebElement we,String data,String elementName) {
		JavascriptExecutor js= (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].value["+data+"]", we);
		js.executeScript("arguments[0].value='"+ data +"';", we);
		etest.log(Status.INFO, data+" is inputed in "+elementName);
	}
	
	public String getAttribute(WebElement we,String attributeName) {
		return	we.getAttribute(attributeName);
	}
	public int getRandomNo() {
		Random rand = new Random();

		int rand_int1 = rand.nextInt(1000);
		return rand_int1;
	}

	public void invisibilityOfElement(WebElement we) {

		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOf(we));

	}

	public Properties loadPropertiesfile() {

		InputStream inputStream=null;
		try {
			inputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		prop=new Properties();

		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * Open the given Url
	 * 
	 * @param url
	 */
	public void openUrl(String url) {
		driver.get(url);
		Util.sleepInSec(Util.M_WAIT, "Wait for page to load");
		logger.info("Opened the url :"+ url);
		etest.log(Status.INFO, url+" is open successfully");
	}

	/**
	 * Refresh the current browser session
	 * 
	 */
	public void refresh() {
		driver.navigate().refresh();
		Util.sleepInSec(Util.M_WAIT, "Wait for page load");
		logger.info("Refreshed the page");
		etest.log(Status.INFO, " Page Refreshed");
	}

	/**
	 * Returns the Current Page's Title
	 * 
	 * @return browserTitle
	 */
	public String getBrowserTitle() {
		String title = driver.getTitle();
		logger.info("The browser title is " + title);
		etest.log(Status.INFO, title+" is extrect");
		return title;
	}

	/**
	 * Returns the Current Page's URL
	 * 
	 * @return browserURL
	 */
	public String getBrowserURL() { 												 
		String url = null;
		url = driver.getCurrentUrl();
		logger.info("Browser URL is " + url);
		etest.log(Status.INFO, url+" is extract");
		return url;
	}

	/**
	 * Get the Browser Name
	 * 
	 * @return browserName - example firefox, chrome
	 */
	public String getBrowser() {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		return cap.getBrowserName();
	}

	public void ClickElementJavaScript(WebElement we,String Element) {

		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", we);

	}

	/**
	 * Click wrapper to click on an element based on its WebElement
	 * 
	 * 
	 * @param locator - WebElement to perform click operation
	 */

	public boolean clickElement(WebElement element,String elementName) {
		webDriverWait(element);
		//		waitForElementPresent(element, DEFAULT_WAIT_TIME);    //////////commented
		return clickElement(element, true,elementName);
	}
	public void doubleclickElement(WebElement element,String elementName) {
		actions=new Actions(driver);
		actions.doubleClick(element).build().perform();
		//		return clickElement(element, true,elementName);
	}



	/**
	 * This is not supposed to be used outside of this class
	 * 
	 * If the Grid refreshes, the element does not remain in the DOM, we get a
	 * StaleElementReference exception. So, we extract the xpath and click on the
	 * element again. This is handled in the catch block
	 * 
	 * @param element
	 * @param retry - To avoid infinite recursion, only retry if retry is true & only retry once, else exit
	 * @return
	 */
	private boolean clickElement(WebElement element, boolean retry,String elementName) {
		boolean result = false;
		if (element != null) {
			try {
				element.click();
				logger.info("Clicked On the element: " + element);
				etest.log(Status.INFO, "clicked on "+elementName);
				result = true;
			} catch (ElementNotInteractableException e) {
				if(retry) {
					Actions ac=new Actions(driver);
					ac.moveToElement(element).click(element).build().perform();
					etest.log(Status.INFO, "clicked on "+elementName);
				} else {
					logger.error("Cannot click on element :" + element);
					logger.info("Stacktrace: \n" + e);
				}				
				return result;
			}catch (NoSuchElementException | StaleElementReferenceException f) {
				String xpath = element.toString().split("xpath:")[1]; 
				String xPath = xpath.substring(0, xpath.length() - 1);
				if (retry) {
					// sometimes javascript takes a while to attach so element is not yet interactable
					Util.sleepInSec(Util.S_WAIT);
					result = clickElement(getElementByXpath(xPath), false,elementName);
				} else {
					logger.error("Cannot click on element :" + element);
					logger.info("Stacktrace: \n" + f);
					f.printStackTrace();
				}				
				return result;
			} 
			catch (Exception e) {

				logger.error("Cannot click on element :" + element);
				logger.info("Stacktrace: \n", e);

				return false;

			}
		}
		return result;

	}

	/**
	 * Click an Element with its locator
	 * 
	 * @param locator
	 * @return true/false based on if click was successful or not
	 */
	public boolean clickElement(String locator,String elementName) {
		return clickElement(locator, DEFAULT_WAIT_TIME,elementName);
	}

	/**
	 * Click an Element with its locator and maxWaitTime
	 * 
	 * @param locator
	 * @return true/false based on if click was successful or not
	 */
	public boolean clickElement(String locator, long maxWaitTime,String elementName) {
		WebDriverWait wait = new WebDriverWait(driver, maxWaitTime);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(getByFromLocator(locator)));
		return clickElement(element,elementName);
	}

	/**
	 * Function to execute a JavaScript
	 * 
	 * @param script
	 * @return long
	 */
	public int executeJavaScript(String script) {
		long itemNum = -1;
		try {
			itemNum = (Long) ((JavascriptExecutor) driver).executeScript("{" + script + "}");
			logger.info("Executed the Javascript successfully");
		} catch (Throwable t) {
			logger.error("Exception occurred while executing JavaScript, returning itemNum=-1");
			return (int) itemNum;
		}
		return (int) itemNum;
	}

	/**
	 * Wrapper function to execute a JavaScript
	 * 
	 * @param script
	 * @return WebElement
	 */
	public WebElement executeJavaScriptForWebElement(String script) {
		WebElement element = null;
		try {
			element = (WebElement) ((JavascriptExecutor) driver).executeScript("{" + script + "}");
			logger.info("Executed the Javascript successfully");
		} catch (Throwable t) {
			logger.info("Exception occurred on executing JavaScript, returning WebElement = NULL");
			return element;
		}
		return element;
	}

	/**
	 * Click on a WebElement using JavaScriptExecutor
	 * 
	 * @param WebElement
	 */
	public void clickJavaScript(WebElement element,String elementName) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		logger.info("Clicked on the WebElement" +element);
		etest.log(Status.INFO, "clicked on"+elementName);

	}

	/**
	 * Verify if checkbox is Checked or not, using JavaScript
	 * 
	 * @param WebElement
	 * @return true/false
	 */
	public Boolean isCheckBoxChecked(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Boolean checkBox = (Boolean) executor.executeScript("return arguments[0].checked", element);
		return checkBox;
	}

	/**
	 * Right Click a WebElement and select the option
	 * 
	 * @param WebElement
	 * @param option     to click as String
	 */
	public void selectItemRightClick(WebElement element, String optionToClick,String elementName) {
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
		WebElement webElementToClick = getElement(optionToClick);
		clickElement(webElementToClick,elementName);
	}

	/**
	 * Right Click a WebElement
	 * 
	 * @param element
	 * @param Item
	 */
	public void rightClick(WebElement element,String elementName) {
		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.contextClick(element).build().perform();
		logger.info("Right clicked on the WebElement "+element);
		etest.log(Status.INFO, "Right clicked on"+elementName);
	}

	/**
	 * sendKeys
	 *
	 * @param key - example KEYS.CONTROL, KEYS.DELETE
	 */
	public void keyTouch(Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(key);
		action.perform();
	}

	/**
	 * Press key multiple times
	 * 
	 * @param key   - example KEYS.CONTROL, KEYS.DELETE
	 * @param count - count of how many times you want the key to be pressed
	 */
	public void keyTouch(Keys key, int count) {
		for (int i = 0; i < count; i++) {
			keyTouch(key);
		}
	}

	/**
	 * Wrapper function to enter text in a webElement (aka sendKeys), clearing existing data if needed
	 * 
	 * @param element           - WebElement to perform Click operation
	 * @param data              - data to be sent
	 * @param clearExistingData - true/false
	 * @return
	 */
	public void sendKeys(WebElement element, String data, Boolean clearExistingData,String elementName) {
		Util.sleepInSec(Util.S_WAIT);
		//		this.webDriverWait.until(ExpectedConditions.visibilityOf(element));
		int retryCount = 0;
		try {
			if (clearExistingData) {
				element.clear();
			}
			element.sendKeys(data);
			logger.info("Send Keys On Element :: " + element + " With Data : " + data);
			etest.log(Status.INFO, data+" is inputed on"+elementName);
		} catch (StaleElementReferenceException e) {
			String xpath = element.toString().split("xpath:")[1]; // TODO: replace this code with wrapper (get locator from WebElement)
			String xPath = xpath.substring(0, xpath.length() - 1);
			if (retryCount<2) {
				retryCount++;
				sendKeys(getElementByXpath(xPath), data, clearExistingData,elementName);
			} else {
				logger.error("Cannot enter data on element :" + element);
				logger.info("Stacktrace: \n" + e);
			}				
		} catch (Exception e) {
			logger.error("Cannot enter data on element :" + element);
			logger.info("Stacktrace: \n", e);
			e.getMessage();
		}
	}

	/**
	 * Sendkeys (or enter text) Wrapper using locator, clearing existing data if needed
	 * 
	 * @param locator
	 * @param data
	 * @param clearExistingData - true/false : if the existing data on the field
	 *                          needs to be cleared or not ?
	 * @return
	 */
	public void sendKeys(String locator, String data, Boolean clearExistingData,String elementName) {
		WebElement element = this.getElement(locator);
		sendKeys(element, data, clearExistingData,elementName);
	}



	/**
	 * Sendkeys (or enter text) Wrapper using WebElement 
	 * 
	 * @param WebElement
	 * @param data
	 * @return
	 */
	public void sendKeys(WebElement element, String data,String elementName) {
		this.sendKeys(element, data,true,elementName);
	}
	public void sendKeysWithoutClearingExistingData(WebElement element, String data,String elementName) {
		this.sendKeys(element, data,false,elementName);
	}
	/**
	 * Sendkeys (or enter text) Wrapper using locator 
	 * 
	 * @param locator
	 * @param data
	 * @return
	 */
	public void sendKeys(String locator, String data,String elementName) {
		WebElement element = this.getElement(locator);
		this.sendKeys(element, data, false,elementName);
	}

	/**
	 * Send keys with element
	 * 
	 * @param element
	 * @param key     - example : Keys.BACK_SPACE, KEYS.CONTROL
	 */
	public void sendKeys(WebElement element, Keys key) {
		element.sendKeys(key);
	}

	/**
	 * Send keys with locator
	 * 
	 * @param locator
	 * @param key     - example : Keys.BACK_SPACE, KEYS.CONTROL
	 */
	public void sendKeys(String locator, Keys key) {
		WebElement element = this.getElement(locator);
		element.sendKeys(key);
	}

	/**
	 * Scrolls such that element is in the view.
	 * 
	 * @param WebElement
	 * @param isElementAlignedToTopOfView If isElementAlignedToTopOfView is true,
	 *                                    method tries to align the element to the
	 *                                    top of the view If
	 *                                    isElementAlignedToTopOfView is false,
	 *                                    method tries to align the element to the
	 *                                    to bottom of view.
	 */

	public void scrollToElement(WebElement element, boolean isElementAlignedToTopOfView,String elementName) {
		String js = "arguments[0].scrollIntoView(" + isElementAlignedToTopOfView + ");";
		executeScript(js, element);
		logger.info("Scrolled to Element "+element);
		etest.log(Status.INFO, "scrolled on"+elementName);
	}

	/**
	 * Scroll to the WebElement
	 *
	 * @param element
	 */

	public void scrollToElement(WebElement element,String elementName) {
		scrollToElement(element, true,elementName);
		logger.info("Scrolled to Element "+element);
	}

	/**
	 * Scroll down
	 * 
	 * @param element
	 */
	public void scrollDown(WebElement element) {
		executeScript("arguments[0].lastChild.scrollIntoView(true);", element);
		etest.log(Status.INFO, "scroll down");
	}

	/**
	 * Execute javascript
	 * 
	 * @param script
	 * @param args
	 */
	public void executeScript(String script, Object... args) {
		((JavascriptExecutor) driver).executeScript(script, args);
	}

	/**
	 * Scrolling element by pixels along x axis. 
	 * 		Positive x means right, 
	 * 		Negative x means left 
	 * 		Positive y means down
	 * 		Negative y means up
	 * 
	 * @param element
	 * @param x_axis
	 * @param y_axis
	 */
	public void scrollElementByPixels(WebElement element, int x_axis, int y_axis) {
		String jString = String.format("arguments[0].scrollBy(%d, %d)", x_axis, y_axis);
		executeScript(jString, element);
		etest.log(Status.INFO, "scrolled on"+x_axis+" and"+ y_axis);
	}

	/**
	 * 
	 * Gets the text from the WebElement passed
	 * 
	 * @param element
	 * @return
	 */
	public String getText(WebElement element) {
		String text = null;
		if (element != null) {
			text = element.getText();
			if (text.length() != 0)
				System.out.println(text);
			logger.info("Getting Text On Element :: " + element + " The Text is : " + text);
			etest.log(Status.INFO, element.getText()+" is extract");
		} else {
			logger.error("");
			return null;
		}
		return text.trim();
	}

	/**
	 * 
	 * Gets the text from the locator passed
	 * 
	 * @param locator
	 * @return
	 */
	public String getText(String locator) {
		WebElement element = this.getElement(locator);
		return this.getText(element);
	}

	/**
	 * Returns the state of the element, if the element is Enabled or not, using locator
	 * 
	 * @param locator
	 * @return true or false
	 */

	public boolean isEnabled(String locator) {
		return (getElement(locator)).isEnabled() ? true : false;
	}

	/**
	 * Returns the state of the element, if the element is Enabled or not, using WebElement
	 * 
	 * @param locator
	 * @return
	 */

	public Boolean isEnabled(WebElement element) {
		return element.isEnabled() ? true : false;
	}

	/**
	 * Returns the state of the element, if the element is displayed or not, based on WebElement
	 * 
	 * @param element
	 * @return
	 */

	public boolean isDisplayed(WebElement element) {
		return (element).isDisplayed() ? true : false;
	}

	/**
	 * Returns the state of the element, if the element is displayed or not, based on locator
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isDisplayed(String locator) {
		return (getElement(locator)).isDisplayed() ? true : false;
	}

	/**
	 * Returns the state of the element, if the element is selected or not, using Locator
	 * 
	 * @param locator
	 * @return boolean
	 */
	public boolean isSelected(String locator) {
		return (getElement(locator).isSelected()) ? true : false;
	}

	/**
	 * Returns the state of the element, if the element is selected or not, using WebElement
	 * 
	 * @param locator
	 * @return boolean
	 */

	public boolean isSelected(WebElement element) {
		return (element.isSelected()) ? true : false;
	}

	/**
	 * select a checkbox based on webElement
	 * 
	 * @param element
	 */
	public void check(WebElement element) {
		this.webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
		if (element.isSelected()) {
			logger.info("The checkbox " + element + " is already checked");
		} else {
			element.click();
			logger.info("The checkbox " + element + " is checked");
		}
	}

	/**
	 * select a checkbox based on locator
	 * 
	 * @param locator
	 */
	public void check(String locator) {
		WebElement element = getElement(locator);
		check(element);
	}

	/**
	 * Unselect a checkbox
	 * 
	 * @param element
	 */
	public void unCheck(WebElement element) {
		this.webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
		if (element.isSelected()) {
			element.click();
			logger.info("The checkbox " + element + " is Unchecked");
		} else {
			logger.info("The checkbox " + element + " is already Unchecked");
		}
	}

	/**
	 * Unselect a checkbox
	 * 
	 * @param locator
	 */
	public void unCheck(String locator) {
		WebElement element = getElement(locator);
		unCheck(element);
	}

	/**
	 * Check or uncheck checkbox
	 * 
	 * @param checkBox, enable
	 */
	public void checkUncheckCheckBox(WebElement checkBox, boolean enable,String elementName) {
		if (enable && !isCheckBoxChecked(checkBox)) {
			clickJavaScript(checkBox,elementName);
		} else if (!enable && isCheckBoxChecked(checkBox)) {
			clickJavaScript(checkBox,elementName);
		}
	}

	/**
	 * Builds WebElement for any locator. Will return the WebElement when the element becomes available in the DOM
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator 
	 * locators can be based of xpath, class, css, name, linkText, tagName
	 * Will wait for a max of DEFAULT_WAIT_TIME defined in this class
	 * 
	 * @return WebElement
	 */

	public WebElement getElement(String locator) {
		return getElement(locator, DEFAULT_WAIT_TIME);
	}

	/**
	 * Builds WebElement with any locator. Will return the WebElement when the element becomes available in the DOM
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param maxTimeOut
	 * @return
	 */
	public WebElement getElement(String locator, long maxTimeOut) {
		return getElement(getByFromLocator(locator), maxTimeOut);
	}

	/**
	 * private method not for use, internally used within this class
	 * for getElement, please use {@link #getElement(String) or #getElement(String, int)}
	 * 
	 * @param by
	 * @param timeOut
	 * @return
	 */
	private WebElement getElement(By by, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (TimeoutException e) {
			logger.info("getElement wait timed out for locator " + by.toString());
			return null;
		} catch (Exception e) {
			logger.error("Exception in finding element with locator " + by.toString());
			logger.info("Stacktrack:\n", e);
			return null;
		}
		logger.info("Element found with locator " + by.toString());
		return element; 
	}

	/**
	 * Builds The List of WebElements By given locator strategy
	 * 
	 * @param locator - locator strategy, id, name=sample, tag=div, link=sample
	 * @return WebElement
	 */
	public List<WebElement> getElements(String locator) {
		return getElements(locator, DEFAULT_WAIT_TIME);
	}

	/**
	 * Builds The List of WebElements By given locator strategy, and with a defined timeout
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */

	public List<WebElement> getElements(String locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		List<WebElement> elementList = null;
		try {
			elementList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByFromLocator(locator)));
		} catch(TimeoutException te) {
			logger.info("getElements timed out for locator " + locator);
			return null;
		} catch(Exception e) {
			logger.error("Exception occured in getElements for locator " + locator);
			e.printStackTrace();
			return null;
		}
		logger.info("Elements found with locator " + locator);
		return elementList;
	}

	/**
	 * Returns the WebElement By id
	 * 
	 * @param id - Element HTML id
	 * @return WebElement whose HTML is id
	 */
	public WebElement getElementById(String id) {
		WebElement e;
		try {
			e = this.webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return e;
	}

	/**
	 * Returns list of Elements by id
	 * 
	 * @param id
	 * @return WebElement
	 */
	public List<WebElement> getElementsById(String id) {
		List<WebElement> webElements = this.webDriverWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(id)));
		return webElements;
	}

	/**
	 * Returns Element by name
	 * 
	 * @param name
	 * @return WebElement
	 */
	public WebElement getElementByName(String name) {
		WebElement webElement = this.webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
		return webElement;
	}

	/**
	 * Returns Element by LinkText
	 * 
	 * @param LinkText
	 * @return WebElement
	 */
	public WebElement getElementByLinkText(String linkText) {
		WebElement webElement = this.webDriverWait
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
		return webElement;
	}

	/**
	 * Returns the WebElement By Xpath
	 * 
	 * @param xpath - xpath of the element
	 * @return
	 */
	public WebElement getElementByXpath(String xpath) {
		WebElement e = null;
		try {
			e = this.webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			return e;
		} catch (TimeoutException ex) {
			return null;
		}
	}

	/**
	 * Returns list of WebElements By Xpath
	 * 
	 * @param xpath - xpath of the element
	 * @return
	 */

	public List<WebElement> getElementsByXpath(String xpath) {
		List<WebElement> webElements = this.webDriverWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		return webElements;
	}

	/**
	 * Returns the WebElement By ClassName
	 * 
	 * @param className
	 * @return
	 */
	public WebElement getElementByClassName(String className) {
		WebElement e = null;
		try {
			e = this.webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
			return e;
		} catch (TimeoutException ex) {
			return null;
		}
	}

	/**
	 * Returns the WebElement By TagName
	 * 
	 * @param tagName
	 * @return
	 */
	public WebElement getElementByTagName(String tagName) {
		WebElement e = null;
		try {
			e = this.webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(tagName)));
		} catch (TimeoutException ex) {
			logger.error("Element Was not presented...");
		}
		return e;
	}

	/**
	 * Returns the WebElement By CSS
	 * 
	 * @param cssSelector
	 * @return
	 */
	public WebElement getElementByCSS(String cssSelector) {
		return this.webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
	}

	/**
	 * Get Div Element by text
	 * 
	 * @param text
	 * @return
	 */
	public WebElement getElementByText(String text) {
		WebElement e = getElement("//div[text()='" + text + "']");
		return e;
	}

	/**
	 * Selects an element by double clicking on it
	 * 
	 * @param elem - The element that needs to be selected
	 * browser - For Edge browser, there's a special way to doubleClick
	 * 
	 */
	public void selectElement(WebElement elem, String browser) {
		if (browser.equals("Edge")) {
			String script = "var x = document.querySelector(\"#editor-content-editable > div > span\");"
					+ "if (x == null) x = document.querySelector(\"#editor-content-editable > div span\");"
					+ "window.getSelection().selectAllChildren(x);";
			executeScript(script);
		} else {
			Actions action = new Actions(driver);
			action.moveToElement(elem);
			action.doubleClick(elem).build().perform();
		}
	}

	/**
	 * MouseOver with webElement
	 * 
	 * @param elem
	 */

	public void mouseOver(WebElement elem) {
		String script = "var obj = document.createEvent('MouseEvents'); "
				+ "obj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(obj);";
		executeScript(script, elem);
	}

	/**
	 * MouseOver using locator
	 * 
	 * @param elem
	 */
	public void mouseOver(String locator) {
		WebElement element = getElement(locator);
		mouseOver(element);
	}

	/**
	 * mouse over using Actions class
	 * 
	 * @param locator
	 */
	public void mouseOverByAction(String locator) {
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.moveToElement(element).build().perform();
	}


	/**
	 * 
	 * To find the element attribute. Attribute examples - is-disabled, active, checked
	 * 
	 * @param element
	 * @param attribute
	 * @return
	 */
	public String getElementAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	/**
	 * Wait for a particular Page Title 
	 * 
	 * @param title - expected title
	 */
	public void waitForPageTitle(String title) {
		this.webDriverWait.until(ExpectedConditions.titleContains(title));
	}

	/**
	 * Wait for a particular Page URL
	 * 
	 * @param url
	 */
	public void waitForPageURL(String url) {
		this.webDriverWait.until(ExpectedConditions.urlContains(url));
	}

	/**
	 * Wait For Element to disappear
	 * 
	 * @param element
	 */
	public void waitForElementToDisappear(String locator) {
		this.webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(getByFromLocator(locator)));
	}

	/**
	 * Wait for element to disappear with locator, with a max timeout
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void waitForElementToDisappear(String locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getByFromLocator(locator)));
	}

	/**
	 * Wait for element to disappear with locator, with a max timeout
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void waitForElementToDisappear(WebElement element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getByFromElement(element)));
	}

	/**
	 * Waits for element to be clickable using WebElement
	 * 
	 * @param element
	 */
	public void waitForElementToBeClickAble(WebElement element) {
		this.webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Waits for element to be clickable using locator
	 * 
	 * @param locator
	 */
	public void waitForElementToBeClickAble(String locator) {
		this.webDriverWait.until(ExpectedConditions.elementToBeClickable(getByFromLocator(locator)));
	}

	/**
	 * Waits for element to be clickable using locator and a timeout defined
	 * 
	 * @param locator
	 * @param maxTimeout - max time out till which the wait should happen
	 */
	public void waitForElementToBeClickAble(String locator, int maxTimeOut) {
		WebDriverWait driverWait = new WebDriverWait(driver, maxTimeOut);
		driverWait.until(ExpectedConditions.elementToBeClickable(getByFromLocator(locator)));
	}

	/**
	 * Waits for element to be displayed and visible
	 * Visibility means that the element is not only present on the DOM but also has a height and width that is greater than 0.
	 * 
	 * @param locator
	 */

	public void waitForElementToBeDisplayed(String locator) {
		this.webDriverWait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	/**
	 * Waits for element to be displayed or visible
	 * Visibility means that the element is not only present on the DOM but also has a height and width that is greater than 0.
	 * 
	 * @param element - WebElement
	 */
	public void waitForElementToBeDisplayed(WebElement element) {
		this.webDriverWait.until(ExpectedConditions.visibilityOf(element)); 
	}

	/**
	 * Waits for element to be displayed or visible
	 * Visibility means that the element is not only present on the DOM but also has a height and width that is greater than 0.
	 * 
	 * @param element - WebElement
	 */
	public boolean waitForElementToBeDisplayed(WebElement element, long maxTimeOut) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, maxTimeOut);
		WebElement elementFound = null;
		elementFound = webDriverWait.until(ExpectedConditions.visibilityOf(element));
		return elementFound!=null ? true: false;
	}

	/**
	 * Wait for the Element to be present. This method only checks
	 * if the Element is present, does not validate if the Element is visible
	 * For the Element to be visible use {@link #waitForElementToBeDisplayed(String)}
	 * 
	 * @param locator
	 * @return boolean - if the Element was found or not
	 */

	public boolean waitForElementPresent(String locator) {
		return waitForElementPresent(locator, DEFAULT_WAIT_TIME);
	}

	/**
	 * Wait for the Element to be present. This method only checks
	 * if the Element is present in DOM, does not validate if the Element is visible
	 * For the Element to be visible use {@link #waitForElementToBeDisplayed(WebElement)}
	 * 
	 * @param element
	 * @return boolean - if the Element was found or not
	 */
	public boolean waitForElementPresent(WebElement element) {
		return waitForElementPresent(element, DEFAULT_WAIT_TIME);
	}


	/**
	 * Wait for the Element to be present, with input parameter - maxTimeOut
	 * 
	 * @param locator
	 * @param maxTimeout - max time out till which the wait should happen
	 */

	public boolean waitForElementPresent(String locator, long maxTimeOut) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, maxTimeOut);
		WebElement element = null;
		element = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(getByFromLocator(locator)));
		return element!=null ? true: false;
	}

	/**
	 * Wait for the Element to be present, with input parameter - maxTimeOut
	 * 
	 * @param locator
	 * @param maxTimeout - max time out till which the wait should happen
	 */

	public boolean waitForElementPresent(WebElement element, long maxTimeOut) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, maxTimeOut);
		WebElement elementFound = null;
		elementFound = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(getByFromElement(element)));
		return elementFound!=null ? true: false;
	}

	/**
	 * Returns By using locator
	 * 
	 * @param locator
	 * @return
	 */
	public By getByFromLocator(String locator) {
		try {
			if (locator.startsWith("//")) {
				return By.xpath(locator);
			} else if (locator.startsWith("class=")) {
				locator = locator.split("=")[1];
				return By.className(locator);
			} else if (locator.startsWith("css=")) {
				locator = locator.substring(4);
				return By.cssSelector(locator);
			} else if (locator.startsWith("name=")) {
				locator = locator.split("\\=")[1];
				return By.name(locator);
			} else if (locator.startsWith("link=")) {
				locator = locator.split("\\=")[1];
				return By.linkText(locator);
			} else if (locator.startsWith("tag=")) {
				locator = locator.split("\\=")[1];
				return By.tagName(locator);
			} else {
				return By.id(locator);
			}
		} catch (NoSuchElementException noSuchElementException) {
			return null;
		}
	}

	/**
	 * Returns By using webElement
	 * 
	 * @param locator
	 * @return
	 */
	public By getByFromElement(WebElement element) {
		By by = null;
		//[[ChromeDriver: chrome on XP (d85e7e220b2ec51b7faf42210816285e)] -> xpath: //input[@title='Search']]
		String[] pathVariables = (element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":");

		String selector = pathVariables[0].trim();
		String value = pathVariables[1].trim();

		switch (selector) {
		case "id":
			by = By.id(value);
			break;
		case "class name":
			by = By.className(value);
			break;
		case "tagName":
			by = By.tagName(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css selector":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		default:
			throw new IllegalStateException("locator : " + selector + " not found!!!");
		}
		return by;
	}

	/**
	 * This function creates webElement by combining two web elements
	 * 
	 * @param WebElement - element1
	 * @param WebElement - element2
	 * @return WebElement - element
	 */

	public WebElement createElementByCombo(WebElement element1, WebElement element2) {
		return getElement(createLocatorByCombo(element1, element2));
	}

	/**
	 * This function creates locator by combining two web elements
	 * 
	 * @param WebElement - element1
	 * @param WebElement - element2
	 * @return String - locator
	 */
	public String createLocatorByCombo(WebElement element1, WebElement element2) {
		return getLocatorFromElement(element1) + getLocatorFromElement(element2);
	}

	/**
	 * This function extracts the locator from the web elements
	 * 
	 * @param WebElement - element
	 * @return String - locator
	 */
	public String getLocatorFromElement(WebElement element) {
		String locatorString = null;
		String[] locator = null;
		String selector = null;
		String type = null;
		if (!element.toString().contains("Proxy")) {
			locatorString = element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
			locator = locatorString.split(":", 2);
			selector = locator[1].trim();
			type = locator[0].trim().split(" ")[0];
		} else {
			locator = element.toString().split(":", 3);
			selector = locator[2].trim();
			type = locator[1].trim().split(" ")[1];
		}
		if(type.contains("css")) {
			return "css="+selector;
		}else if (type.contains("class")){
			return "class="+selector;
		}else if (type.contains("iOSAccId")) {
			return "iOSAccId="+selector;
		}else{
			if(selector.endsWith("'")){
				return selector.substring(0, (selector.length() - 1));
			} else {
				return selector;
			}
		}
	}

	/**
	 * Verify if the element is present
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isElementPresent(String locator) {
		return isElementPresent(locator, Util.S_WAIT);
	}

	/**
	 * Verify if the element is present and timeOut defined
	 * 
	 * @param locator
	 * @param timeOutInSeconds - max timeout to wait for the element to be present
	 * @return
	 */

	public boolean isElementPresent(String locator, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(getByFromLocator(locator)));
		} catch(TimeoutException te) {
			logger.error("Wait timed out finding the element \n" + te.toString());
			logger.trace("Stacktrace:\n", te);
			return false;
		} catch (Exception e) {
			logger.error("Unknown Exception occured in finding element " + locator + "\n" + e.toString());
			logger.trace("Stacktrace: \n", e);
			return false;
		}
		if (element == null) {
			logger.error("Element is not present: " + locator);
			return false;
		}
		logger.info("Element is present: " + locator);
		return true;
	}

	/**
	 * Verify if the element is present
	 * 
	 * @param element
	 * @return
	 */
	public boolean isElementPresent(WebElement element) {
		try {
			if (driver.findElement(getByFromElement(element)) != null) {
				logger.info("Element : " + element + " successfully appeared on page");
				return true;
			} else {
				logger.warn("Element : " + element + " is not present on page");
				return false;
			}
		} catch (Exception e) {
			logger.warn("Element : " + element + " is not present on page");
			return false;
		}
	}

	/**
	 * Opens a new tab
	 */
	public String openNewTab(String URL) {
		Util.sleepInSec(Util.M_WAIT, "Attempting to Open a New Tab");

		executeScript("window.open('','_blank');");

		ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
		while (tabList.size() < 2) {
			logger.info("New Window Not Opened");
			executeScript("window.open('','_blank');");
			tabList = new ArrayList<String>(driver.getWindowHandles());
		}
		for (String tab : tabList) {
			driver.switchTo().window(tab);
			logger.info("URL is " + driver.getCurrentUrl());
			if (driver.getCurrentUrl().equals("about:blank")) {
				driver.get(URL);
				return URL;
			}
		}
		return "";
	}

	public void clickOnSVGElement(WebElement elem) {
		Actions builder = new Actions(driver);
		builder.click(elem).build().perform();
	}

	/**
	 * Selects the New Browser Window
	 */
	public void selectBrowserPopUp() {
		// Get all the window handles in a set
		Set<String> handles = driver.getWindowHandles();
		while (handles.size() < 2) {
			Util.sleepInSec(1000); // Sleep for 1 second till browser opens up
			handles = driver.getWindowHandles();
		}

		for (String handle : handles) {
			driver.switchTo().window(handle);
		}
	}

	public void switchToIFrame(String iFrame) {
		waitForElementToBeDisplayed("//iframe[@name='" + iFrame + "']");
		driver.switchTo().frame(iFrame);
	}

	public void switchToIFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}

	/**
	 * Switch to the new Tab
	 */
	public void switchToNewTab() {
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

	/**
	 * Switch to the parent tab
	 */
	public void switchToParentTab() {
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	/**
	 * Closes the Browser Pop up window except if the Tile in ignoreWindowTitle
	 * 
	 * @param windowHandle
	 * @param ignoreWindow
	 */
	public void closeBrowserPopUp(String windowHandle, String ignoreWindowTitle) {
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			driver.switchTo().window(handle);
			if (!handle.equals(windowHandle)) {
				if (!driver.getTitle().contains(ignoreWindowTitle))
					driver.close();
			}
		}
		driver.switchTo().window(windowHandle);

	}

	/**
	 * Select Option By Value
	 * 
	 * @param element
	 * @param optionToSelect
	 */
	public void selectOptionByValue(WebElement element, String optionToSelect) {
		Select sel = new Select(element);
		sel.selectByValue(optionToSelect);
		logger.info("Selected the option : " + optionToSelect);
	}

	/**
	 * Selection Option By Value
	 * 
	 * @param locator
	 * @param optionToSelect
	 */
	public void selectOptionByValue(String locator, String optionToSelect) {
		Select sel = new Select(getElement(locator));
		sel.selectByValue(optionToSelect);
		logger.info("Selected the option : " + optionToSelect);
	}

	/**
	 * Selection Option By Visible Text using locator
	 * 
	 * @param locator
	 * @param optionToSelect
	 */

	public void selectOptionByVisibleText(String locator, String optionToSelect) {
		Select sel = new Select(getElement(locator));
		sel.selectByVisibleText(optionToSelect);
		logger.info("Selected the option : " + optionToSelect);
	}

	/**
	 * Selection Option By Visible Text using webelement
	 * 
	 * @param locator
	 * @param optionToSelect
	 */

	public void selectOptionByVisibleText(WebElement element, String optionToSelect) {
		Select sel = new Select(element);
		sel.selectByVisibleText(optionToSelect);
		logger.info("Selected the option : " + optionToSelect);
	}

	/**
	 * get Selected drop down value
	 * 
	 * @param element
	 * @return
	 */
	public String getSelectedDropDownValue(WebElement element) {
		Select sel = new Select(element);
		return sel.getFirstSelectedOption().getText();
	}

	/**
	 * Verifies if an option exists
	 * 
	 * @param element
	 * @param optionToVerify - String that needs to be verified
	 */
	public boolean isOptionExists(WebElement element, String optionToVerify) {
		Select sel = new Select(element);
		boolean exists = false;
		List<WebElement> optList = sel.getOptions();
		for (int i = 0; i < optList.size(); i++) {
			String text = getText(optList.get(i));
			if (text.matches(optionToVerify)) {
				exists = true;
				break;
			}
		}
		if (exists) {
			logger.info("Option : " + optionToVerify + " exists in the element " + element);
		} else {
			logger.info("Option : " + optionToVerify + " does not exist in the element " + element);
		}
		return exists;
	}

	/**
	 * Take Screenshot
	 * 
	 * @param issueType
	 */
	public void screenShot(String issueType) {
		String fileName = System.currentTimeMillis() + "." + issueType + ".png";
		File screenshotDir = new File("resources/screencapture");
		screenshotDir.mkdir();
		if (fileName.contains("/")) {
			fileName = fileName.replaceAll("/", "-");
			logger.info(fileName);
		}
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotDir + "//" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Moves to location which is x_off, y_off pixel offset from top left corner of
	 * element and clicks on it Used to click outside of the modal typically.
	 * 
	 * @param locator
	 * @param x_off
	 * @param y_off
	 */
	public void clickByElementOffset(String locator, int x_off, int y_off) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator), x_off, y_off).click().build().perform();
	}

	/**
	 * Navigate back
	 */
	public void navigateBack() {
		Util.sleepInSec(Util.M_WAIT, "Wait before navigating back");
		driver.navigate().back();
		Util.sleepInSec(Util.M_WAIT, "Wait for navigating back");
	}

	/**
	 * Navigate forward
	 */
	public void navigateForward() {
		Util.sleepInSec(Util.M_WAIT, "Wait before navigating forward");
		driver.navigate().forward();
		Util.sleepInSec(Util.M_WAIT, "Wait for navigating forward");
	}

	/**
	 * Do refresh the current browser session
	 */
	public void refreshUsingJavaScript() {
		executeJavaScript("window.location.reload(true);");
		Util.sleepInSec(Util.M_WAIT, "Wait for page load");
	}

	/**
	 * Drag and drop source element to target element
	 * 
	 * @param key
	 */
	public void dragAndDrop(String source, String target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(getElement(source), getElement(target)).build().perform();
	}

	/**
	 * Right click on element
	 * 
	 * @param key
	 */
	public void rightClick(String locator) {
		Actions actions = new Actions(driver);
		actions.contextClick(getElement(locator)).build().perform();
	}

	/**
	 * Scroll for element present
	 * 
	 * @param locator
	 */
	public void scrollForElementPresent(String locator, String scrollLocator) { 
		int count = 0;
		while (!isElementPresent(locator) && count <= 20) {
			scrollDown(getElement(scrollLocator));
			Util.sleepInSec(Util.S_WAIT, "Wait for load items");
			count++;
		}
	}

	/**
	 * Verify alert presense
	 * 
	 * @return
	 */
	public boolean isAlertPresents() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Maximizes window size
	 */
	public void maximizeBrowserWindown() {
		driver.manage().window().maximize();
	}

	public String getStringPatternMatcher(String pattern, String attribute) {
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(attribute);
		matcher.find();
		return matcher.group(2);
	}

	/**
	 * Press key combo
	 * 
	 * @param key
	 */
	public void keyTouchCombo(Keys mainKey, Keys supportKey) {
		Actions action = new Actions(driver);
		action.keyDown(mainKey).sendKeys(supportKey).keyUp(mainKey).build().perform();
	}

	/**
	 * Press key combo multiple times
	 * 
	 * @param key
	 */
	public void keyTouchCombo(Keys mainKey, Keys supportKey, int count) {
		for (int i = 0; i < count; i++) {
			keyTouchCombo(mainKey, supportKey);
		}
	}

	/**
	 * Drag and drop source element to target element
	 * 
	 * @param key
	 */
	public void dragAndDrop(WebElement source, WebElement target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).build().perform();
	}


	/**
	 * move one window to another
	 * 
	 * 
	 */

	public static void switchWindow(WebDriver driver) {
		String win=driver.getWindowHandle();
		Set<String> hand=driver.getWindowHandles();
		Iterator<String> it=hand.iterator();
		while(it.hasNext()) {
			String value=it.next();
			if(!win.equals(value)) {
				driver.switchTo().window(value);
				break;
			}
		}
	}

	public void closeBrowser() {
		driver.close();
	}
	public DriverActions() {
		loadPropertiesfile();

		//		this.webDriverWait = new FluentWait<WebDriver>(driver)                         
		//				.withTimeout(Duration.ofSeconds(DEFAULT_WAIT_TIME))          
		//				.pollingEvery(Duration.ofSeconds(POOLING_WAIT_TIME))          
		//				.ignoring(NoSuchElementException.class);
	}
}
