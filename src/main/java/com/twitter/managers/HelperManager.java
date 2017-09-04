package com.twitter.managers;

import com.twitter.helpers.*;

public class HelperManager {

    private static TweetHelper tweetHelper;
    private static NavHelper navHelper;
    private static ApiHelper apiHelper;

    public static TweetHelper tweetHelper() {
        if (tweetHelper != null) return tweetHelper;
        else {
            return tweetHelper = new TweetHelper();
        }
    }

    public static NavHelper navHelper() {
        if (navHelper != null) return navHelper;
        else {
            return navHelper = new NavHelper();
        }
    }

    public static ApiHelper apiHelper() {
        if (apiHelper != null) return apiHelper;
        else {
            return apiHelper = new ApiHelper();
        }
    }
}
