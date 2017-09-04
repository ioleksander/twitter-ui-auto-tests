package com.twitter.helpers;

import com.twitter.models.Tweet;

public class TweetHelper extends BaseHelper {

    public Tweet createTextTweet() {
        return new Tweet(getRandomTweetText());
    }

    private String getRandomTweetText() {
        String chuckNorrisFact = faker.chuckNorris().fact().replace("'", "’");
        return chuckNorrisFact.substring(0, Math.min(chuckNorrisFact.length(), 115)) + " - " + getCurrentDateAndTime();
    }
}
