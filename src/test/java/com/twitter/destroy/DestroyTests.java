package com.twitter.destroy;

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
@Stories("Destroy")
@Listeners(AllureVideoListener.class)
public class DestroyTests extends Launcher {

    @Title("It's possible to remove Tweet")
    @Test(priority = 10)
    public static void removeTweetTest() {
        ApiHelper.addNewTweetWithApi();
        openAuthorizationPage().signIn(user1);
        homeTimelinePage().deleteTopTweet();
        assertTrue(homeTimelinePage().ensureTheTweetIsNotPresent());
    }
}
