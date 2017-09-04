package com.twitter.pages;

import com.twitter.Launcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public abstract class BasePage extends Launcher {

    public abstract boolean isLoaded();

    public abstract BasePage ensureLoaded();

    // Checks

    boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }

    boolean isVisible(By locator) {
        try {
            wait.until(visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    boolean isNotVisible(By locator) {
        try {
            wait.until(invisibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    boolean isClickable(By locator) {
        try {
            return find(locator).isEnabled();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }

    // Actions

    void clear(By locator) {
        find(locator).clear();
    }

    void input(By locator, String text) {
        find(locator).sendKeys(text);
    }

    void click(By locator) {
        waitVisibilityOf(locator).click();
    }

    static WebElement find(By locator) {
        return driver.findElement(locator);
    }

    void waitFor(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    WebElement waitVisibilityOf(By locator) {
        return wait.until(visibilityOfElementLocated(locator));
    }
}
