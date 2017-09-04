package com.twitter;

import com.automation.remarks.video.enums.RecordingMode;
import com.automation.remarks.video.recorder.VideoRecorder;
import com.twitter.models.User;
import com.twitter.utils.DriverUtil;
import com.twitter.utils.PropertyLoaderUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.concurrent.TimeUnit;

import static com.twitter.utils.PropertyLoaderUtil.loadProperty;
import static io.restassured.RestAssured.*;

public class Launcher {

    protected static String baseUrl;
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static User user1;

    @BeforeSuite
    public void setUp() {
        setupBrowser();
        setupRecord();
        setupUsers();
        setupRestAssured();
    }

    @BeforeClass
    @Step("[@BeforeClass: Logout]")
    public void clean() {
        logout();
    }

    @AfterSuite
    @Step("[@AfterSuite: Close browser]")
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Step("[setup browser]")
    private void setupBrowser() {
        // Driver
        driver = DriverUtil.getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // Explicit wait
        wait = new WebDriverWait(driver, 15);
        // Base url
        baseUrl = loadProperty("site.url");
    }

    @Step("[setup recording]")
    private void setupRecord() {
        VideoRecorder.conf()
                .videoEnabled(true)
                .withRecordMode(RecordingMode.ALL);
    }

    @Step("[setup users]")
    private void setupUsers() {
        user1 = new User("+380631152552", "12345678t");
    }

    private void setupRestAssured() {
        baseURI = PropertyLoaderUtil.loadProperty("base.uri");
        basePath = PropertyLoaderUtil.loadProperty("base.path");
        authentication = oauth(
                PropertyLoaderUtil.loadProperty("api.consumer_key"),
                PropertyLoaderUtil.loadProperty("api.consumer_secret"),
                PropertyLoaderUtil.loadProperty("api.token"),
                PropertyLoaderUtil.loadProperty("api.token_secret"));
    }

    @Step("[log out]")
    private void logout() throws NullPointerException {
        driver.manage().deleteCookieNamed("auth_token");
    }
}
