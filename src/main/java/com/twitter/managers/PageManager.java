package com.twitter.managers;

import com.twitter.pages.*;
import ru.yandex.qatools.allure.annotations.Step;

public class PageManager {

    private static AuthorizationPage authorizationPage;
    private static HomeTimelinePage homeTimelinePage;

    @Step("Ensure 'Authorization' page loaded")
    public static AuthorizationPage authorizationPage() {
        if (authorizationPage != null) return authorizationPage.ensureLoaded();
        else {
            authorizationPage = new AuthorizationPage();
            return authorizationPage.ensureLoaded();
        }
    }

    @Step("Ensure 'Home Timeline' page loaded")
    public static HomeTimelinePage homeTimelinePage() {
        if (homeTimelinePage != null) return homeTimelinePage.ensureLoaded();
        else {
            homeTimelinePage = new HomeTimelinePage();
            return homeTimelinePage.ensureLoaded();
        }
    }
}
