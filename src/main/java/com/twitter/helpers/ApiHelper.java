package com.twitter.helpers;

import com.twitter.models.Tweet;
import ru.yandex.qatools.allure.annotations.Step;
import io.restassured.response.Response;

import static com.twitter.managers.HelperManager.tweetHelper;
import static io.restassured.RestAssured.given;

public final class ApiHelper extends BaseHelper {

    @Step("Add new Tweet with API")
    public static Response addNewTweetWithApi() {
        Tweet tweet = tweetHelper().createTextTweet();
        return given().
                        queryParam("status", tweet.getText() + " - API").
                when().
                        post("/statuses/update.json").
                then().
                        log().ifError().
                and().
                        extract().response();
    }

    @Step("Retweet by id with API")
    private static void retweetByIdWithApi(long id) throws NullPointerException {
        given().
                queryParam("id", id).
        when().
                post("/statuses/retweet/" + id + ".json").
        then().
                log().ifError();
    }

    @Step("Add new Tweet & retweet it with API")
    public static void addNewTweetAndRetweetWithApi() {
        long tweetId = addNewTweetWithApi().path("id");
        retweetByIdWithApi(tweetId);
    }
}
