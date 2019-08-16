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
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InfoAssertC2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void infoAssertC2() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_score), withContentDescription("Score"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.action_about), withContentDescription("About"), isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Devinettes"), isDisplayed()));
        textView.check(matches(withText("Devinettes")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.action_source), withContentDescription("Source"), isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.action_mail), withContentDescription("Mail"), isDisplayed()));
        textView3.check(matches(isDisplayed()));
    }
}
