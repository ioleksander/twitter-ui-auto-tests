package com.twitter.utils;

import com.automation.remarks.testng.GridInfoExtractor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.twitter.utils.PropertyLoaderUtil.loadProperty;

public class DriverUtil {

    private static String nodeIp = null;

    // Get Driver
    public static WebDriver getDriver() {
        // Profile properties
        String hubUrl = loadProperty("grid2.hub");
        String browserName = loadProperty("browser.name");
        String browserVersion = loadProperty("browser.version");
        String browserPlatform = loadProperty("browser.platform");
        // Driver
        if (hubUrl.length() != 0) {
            return getRemoteDriver(hubUrl, browserName, browserVersion, browserPlatform);
        } else {
            return getLocalDriver(browserName);
        }
    }

    // Remote driver
    private static WebDriver getRemoteDriver(String hubUrl, String brName, String brVersion, String brPlatform) {
        // Hub
        URL hub = null;
        try {
            hub = new URL(hubUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Set browser
        if (brName.equals("chrome")) {
            capabilities.setBrowserName(brName);
            capabilities = addChromeOptions(capabilities);
        } else if (brName.equals("safari")) capabilities.setBrowserName(brName);
        else if (brName.equals("firefox")) capabilities.setBrowserName(brName);
        // Set version
        if (brVersion.length() != 0) capabilities.setVersion(brVersion);
        // Set platform
        if (brPlatform.equals("mac")) capabilities.setPlatform(Platform.MAC);
        else if (brPlatform.equals("windows")) capabilities.setPlatform(Platform.WINDOWS);
        else if (brPlatform.equals("linux")) capabilities.setPlatform(Platform.LINUX);
        else capabilities.setPlatform(Platform.ANY);
        // Driver
        RemoteWebDriver driver = new RemoteWebDriver(hub, capabilities);
        // NodeIp
        try {
            nodeIp = GridInfoExtractor.getNodeIp(hub, driver.getSessionId().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driver;
    }

    // Local driver
    private static WebDriver getLocalDriver(String brName) {
        WebDriver driver = null;
        DesiredCapabilities capabilities;
        if (brName.equals("chrome")) {
            capabilities = addChromeOptions(DesiredCapabilities.chrome());
            driver = new ChromeDriver(capabilities);
        }
        return driver;
    }

    // Configure Chrome
    public static DesiredCapabilities addChromeOptions(DesiredCapabilities capabilities) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars", "start-fullscreen");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        return capabilities;
    }
}
