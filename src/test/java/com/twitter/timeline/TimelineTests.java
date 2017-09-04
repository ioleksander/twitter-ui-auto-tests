package com.twitter.timeline;

import com.twitter.Launcher;
import com.twitter.helpers.ApiHelper;
import com.twitter.listeners.AllureVideoListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import static com.twitter.helpers.NavHelper.openAuthorizationPage;
import static com.twitter.managers.PageManager.homeTimelinePage;
import static org.testng.Assert.assertTrue;

@Features("Home Timeline")
@Stories("Timeline")
@Listeners(AllureVideoListener.class)
public class TimelineTests extends Launcher {

    @Title("Retweets count is displayed")
    @Test(priority = 10)
    public void retweetsCounterIsDisplayedTest() {
        ApiHelper.addNewTweetAndRetweetWithApi();
        openAuthorizationPage().signIn(user1);
        assertTrue(homeTimelinePage().ensureRetweetsCounterIsDisplayed());
    }

    @Title("Creation date is displayed")
    @Test(priority = 20)
    public void createdAtDateIsDisplayedTest() {
        assertTrue(homeTimelinePage().ensureCreatedAtDateIsDisplayed());
    }

    @Title("Tweet item text is displayed")
    @Test(priority = 30)
    public void tweetItemTextIsDisplayedTest() {
        assertTrue(homeTimelinePage().ensureTweetItemTextIsDisplayed());
    }
}
