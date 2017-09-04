package com.twitter.helpers;

import com.twitter.pages.AuthorizationPage;
import com.twitter.pages.HomeTimelinePage;
import ru.yandex.qatools.allure.annotations.Step;

import static com.twitter.managers.PageManager.*;

public class NavHelper extends BaseHelper {

    @Step("Open: {0}")
    public static void open(String url) {
        if (!driver.getCurrentUrl().equals(url)) driver.get(url);
    }

    @Step("Open 'Authorization' page")
    public static AuthorizationPage openAuthorizationPage() {
        open(AuthorizationPage.URL);
        return authorizationPage();
    }

    @Step("Open 'Authorization' page")
    public static HomeTimelinePage openHomeTimelinePage() {
        open(HomeTimelinePage.URL);
        return homeTimelinePage();
    }
}
