package com.knirirr.beecount.C6;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.knirirr.beecount.R;
import com.knirirr.beecount.WelcomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WelcomeAssertC6 {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Test
    public void welcomeAssertC6() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(ViewMatchers.withId(R.id.welcomeTitle), withText("BeeCount"), isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.newProjectButton), isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.viewProjectButton), isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.welcomeWaffle), withText("Welcome to BeeCount! The purpose of this app is to store count details for several knitting or crochet projects and allow you to count each separately. Each project may have multiple 'counts', each being something such as a pattern repeat or a row which you wish to track. Use the buttons below to create new projects or manage existing ones.\n\n By editing a project after creating it you can also link counts together so that one increments another.\n\nSettings can be changed by accessing the menu on this screen (menu button or 3-dot overflow menu above).\n\nShould you have any questions you may visit the Ravelry group at http://www.ravelry.com/groups/beecount, or email bug reports to knirirr@gmail.com.\n"), isDisplayed()));
        textView2.check(matches(isDisplayed()));
    }
}
