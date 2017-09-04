package com.twitter.update;

import com.twitter.Launcher;
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
@Stories("Update")
@Listeners(AllureVideoListener.class)
public class UpdateTests extends Launcher {

    @Title("It's possible to add new Tweet")
    @Test(priority = 10)
    public void addNewTweetTest() {
        openAuthorizationPage().signIn(user1);
        homeTimelinePage().addNewTweet();
        assertTrue(homeTimelinePage().ensureTheTweetPresent());
    }

    @Title("It's impossible to add blank Tweet")
    @Test(priority = 20)
    public void itsImpossibleToAddBlankTweetTest() {
        homeTimelinePage().setWhatsHappeningField("");
        assertTrue(homeTimelinePage().ensureTweetButtonIsNotClickable());
    }

    @Title("Tweet duplication causes error notification")
    @Test(priority = 30)
    public void notificationTweetAlreadySentTest() {
        homeTimelinePage().addDuplicatedTweets();
        assertTrue(homeTimelinePage().ensureTweetAlreadySentNotificationShown());
    }
}
