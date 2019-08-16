package com.workingagenda.devinettes.C2;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.workingagenda.devinettes.MainActivity;
import com.workingagenda.devinettes.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListAssertC2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void listAssertC2() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.riddle_title), withText("Recursion Here"), isDisplayed()));
        appCompatTextView.perform(click());

        pressBack();

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.riddle_title), withText("When There Is One"), isDisplayed()));
        appCompatTextView2.perform(click());

        pressBack();

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.riddle_title), withText("Name Your Heroine After Me"), isDisplayed()));
        appCompatTextView3.perform(click());

        pressBack();

        ViewInteraction textView = onView(
                allOf(withId(R.id.riddle_title), withText("Recursion Here"), isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.riddle_title), withText("When There Is One"), isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.riddle_title), withText("Name Your Heroine After Me"), isDisplayed()));
        textView3.check(matches(isDisplayed()));
    }
}
