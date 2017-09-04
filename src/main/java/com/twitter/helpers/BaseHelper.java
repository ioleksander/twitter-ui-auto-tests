package com.twitter.helpers;

import com.github.javafaker.Faker;
import com.twitter.Launcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseHelper extends Launcher {

    // Random text generator
    Faker faker = new Faker();

    static String getCurrentDateAndTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }
}
