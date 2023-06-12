package com.PlanIT.WitsLab.ui.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.PlanIT.WitsLab.core.ConfigReader;
import com.PlanIT.WitsLab.util.Util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * WebDriver related functions to start various driverss
 *
 */
public class DriverFactory {
//	private static Logger  logger = LogManager.getLogger(DriverFactory.class.getName());
	private static final String WIN_USER = "sync";
	
    /**
     * Constructs Application URL to load home page by WebDriver Session
     * @return - application URL
     */
//    private static String getAppUrl() {
//        String app_url = "http://"
//                + ConfigReader.getConfiguration(SeleniumConfigs.APP_URL_PROPERTY, SeleniumConfigs.APP_URL_DEFAULT_VALUE);
//        logger.info("The URL is " + app_url);
//        return app_url;
//    }
   
	
    /**
     * Get instance of WebDriver interface  -- Now Used on Jenkins
     * @param browser
     * @param locale
     * @return
     */
    
    
    
    
    
    
    
    
    
    /**
     * Get GridHub for Testing
     * @return
     */
    private static String getGridHubUrl() {
        String gridHost;
        String gridPort;
        gridHost = ConfigReader.getConfiguration(SeleniumConfigs.HUB_HOST_PROPERTY, SeleniumConfigs.HUB_HOST_DEFAULT_VALUE);
        gridPort = ConfigReader.getConfiguration(SeleniumConfigs.HUB_PORT_PROPERTY, SeleniumConfigs.HUB_PORT_DEFAULT_VALUE);
        String url = "http://" + gridHost + ":" + gridPort + "/wd/hub";
//        logger.info("Grid URL is: " + url);
        return url;
    }

    /**
     * Get RemoteDriver to get instance of Webdriver interface 
     * @param browser
     * @return
     * @throws Exception
     */
    public static WebDriver getRemoteInstance(String browser) throws Exception {
        WebDriver driver = null;

        // Setting up Desired Capabilities
        DesiredCapabilities capability = new DesiredCapabilities();
//        String website = openUrl();

        if (browser.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-fullscreen");
            capability = DesiredCapabilities.chrome();
            capability.setCapability(ChromeOptions.CAPABILITY, options);
        } else if (browser.equals("Edge")) {
            capability = DesiredCapabilities.edge();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            capability = DesiredCapabilities.firefox();
            capability.setCapability(FirefoxDriver.PROFILE, getFirefoxProfileForAutomaticDownload());
            capability.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, 1);
        } else if (browser.equalsIgnoreCase("ie")) {
            capability = DesiredCapabilities.internetExplorer();
        }

        // Create Remote WebDriver
        String gridHubUrl = getGridHubUrl();
        try {
            driver = new RemoteWebDriver(new URL(gridHubUrl), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Setting Driver Implicit Time out for An Element
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        // loading Browser with App URL
//        driver.get(website);

        // Maximize Window
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.moveTo(0,0);");
        js.executeScript("window.resizeTo(screen.width,screen.height);");

        return driver;
    }

    /**
     * Set firefox Profile for automatic download
     * @return
     */
    private static FirefoxProfile getFirefoxProfileForAutomaticDownload() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", "/Users/" + WIN_USER + "/Downloads/temp");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "image/jpeg,text/plain,application/zip;application/octet-stream;application/x-zip;application/x-zip-compressed");
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.download.folderList", 1);
        profile.setPreference("browser.download.panel.shown", false);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        profile.setPreference("browser.download.manager.closeWhenDone", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
        return profile;
    }
}
