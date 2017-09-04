package com.twitter.pages;

import com.twitter.models.Tweet;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.twitter.managers.HelperManager.*;
import static org.testng.Assert.assertTrue;

public class HomeTimelinePage extends BasePage {

    public static final String URL = baseUrl;
    // Add new tweet block
    private static final By FIELD_WHATS_HAPPENING = By.xpath("//div[@id='tweet-box-home-timeline']");
    private static final By BUTTON_TWEET = By.xpath("//button[contains(@class,'tweet-action EdgeButton EdgeButton--primary')]");
    // Notification messages
    private static final By NOTIFICATION_TWEET_ALREADY_SENT = By.xpath("//span[@class='message-text'][text()='You have already sent this Tweet.']");
    private static final By BUTTON_DISMISS = By.xpath("//a[contains(@class,'dismiss')]");
    // Tweet item
    private static final By TEXT_TOP_TWEET = By.xpath("(//div[@class='content']//p[contains(@class,'tweet-text')])[1]");
    private static final By DATE_CREATED_AT_TOP_TWEET = By.xpath("(//div[@class='content']//span[contains(@class,'relative-timestamp')])[1]");
    private static final By COUNTER_RETWEETS_TOP_TWEET = By.xpath("((//span[@class='ProfileTweet-actionCountForPresentation'])[3])[1]");
    // Tweet item options
    private static final By BUTTON_MORE_OF_TOP_TWEET = By.xpath("//span[contains(@class, 'caretDownLight')][1]");
    private static final By BUTTON_DELETE_OF_TOP_TWEET = By.xpath("(//button[@class='dropdown-link'][text()='Delete Tweet'])[1]");
    private static final By BUTTON_POPUP_DELETE = By.xpath("//button[contains(@class,'delete-action')]");

    @Override
    public boolean isLoaded() {
        return isVisible(FIELD_WHATS_HAPPENING);
    }

    @Override
    public HomeTimelinePage ensureLoaded() {
        assertTrue(isLoaded());
        return this;
    }

    // Checks

    @Step("Ensure Tweet item text is displayed")
    public boolean ensureTweetItemTextIsDisplayed() {
        return isDisplayed(TEXT_TOP_TWEET);
    }

    @Step("Ensure retweets count is displayed")
    public boolean ensureRetweetsCounterIsDisplayed() {
        return isDisplayed(COUNTER_RETWEETS_TOP_TWEET);
    }

    @Step("Ensure created at date is displayed")
    public boolean ensureCreatedAtDateIsDisplayed() {
        return isDisplayed(DATE_CREATED_AT_TOP_TWEET);
    }

    @Step("Ensure the Tweet is not present")
    public boolean ensureTheTweetIsNotPresent() {
        final By itemTweet = By.xpath("//div[@class='content']//p[contains(.,'" + topTweetText + "')]");
        return isNotVisible(itemTweet);
    }

    @Step("Ensure 'Tweet' button is not clickable")
    public boolean ensureTweetButtonIsNotClickable() {
        return !isClickable(BUTTON_TWEET);
    }

    // Actions

    @Step("Set 'What's happening?' field")
    public void setWhatsHappeningField (String text) {
        click(FIELD_WHATS_HAPPENING);
        input(FIELD_WHATS_HAPPENING, text);
    }

    @Step("Click 'Tweet' button")
    public void clickTweetButton() {
        click(BUTTON_TWEET);
    }

    @Step("Click 'More' button")
    private void clickTopTweetMoreButton() {
        click(BUTTON_MORE_OF_TOP_TWEET);
    }

    @Step("Click 'Delete Tweet' button")
    private void clickTopTweetDeleteButton() {
        click(BUTTON_DELETE_OF_TOP_TWEET);
    }

    @Step("Click popup 'Delete' button")
    private void clickPopupDeleteButton() {
        click(BUTTON_POPUP_DELETE);
    }

    @Step("Click notification 'Dismiss' button")
    private void clickNotificationDismissButton() {
        click(BUTTON_DISMISS);
    }

    // Scenarios

    private static String generatedTweetText;

    @Step("Add new tweet")
    public String addNewTweet() {
        Tweet tweet = tweetHelper().createTextTweet();
        generatedTweetText = tweet.getText();
        setWhatsHappeningField(generatedTweetText);
        clickTweetButton();
        return generatedTweetText;
    }

    @Step("Ensure the tweet is present")
    public boolean ensureTheTweetPresent() {
        final By itemTweet = By.xpath("//div[@class='content']//p[contains(.,'" + generatedTweetText + "')]");
        waitVisibilityOf(itemTweet);
        return isVisible(itemTweet);
    }

    @Step("Ensure 'You have already sent this Tweet.' notification shown")
    public boolean ensureTweetAlreadySentNotificationShown() {
        waitVisibilityOf(NOTIFICATION_TWEET_ALREADY_SENT);
        boolean isNotificationVisible = isVisible(NOTIFICATION_TWEET_ALREADY_SENT);
        clickNotificationDismissButton();
        clear(FIELD_WHATS_HAPPENING);
        return isNotificationVisible;
    }

    @Step("Add duplicated tweets")
    public void addDuplicatedTweets() {
        Tweet tweet = tweetHelper().createTextTweet();
        generatedTweetText = tweet.getText();
        for (int i = 0; i < 2; i++) {
            click(FIELD_WHATS_HAPPENING);
            setWhatsHappeningField(generatedTweetText);
            clickTweetButton();
            if (i == 0) waitFor(500);
        }
    }

    private static String topTweetText;

    @Step("Delete top Tweet")
    public String deleteTopTweet() {
        topTweetText = find(TEXT_TOP_TWEET).getText();
        clickTopTweetMoreButton();
        clickTopTweetDeleteButton();
        clickPopupDeleteButton();
        return topTweetText;
    }
}
