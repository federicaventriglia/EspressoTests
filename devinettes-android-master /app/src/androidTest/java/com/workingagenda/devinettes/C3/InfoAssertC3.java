package com.workingagenda.devinettes.C3;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.workingagenda.devinettes.MainActivity;
import com.workingagenda.devinettes.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InfoAssertC3 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void infoAssertC3() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_score), withContentDescription("Score"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.toolbar),
                                                childAtPosition(
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        withId(android.R.id.content),
                                                                        0),
                                                                0),
                                                        0)),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.action_about), withContentDescription("About"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.toolbar),
                                                childAtPosition(
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        withId(android.R.id.content),
                                                                        0),
                                                                0),
                                                        0)),
                                        1),
                                1),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Devinettes"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                childAtPosition(
                                                        childAtPosition(
                                                                allOf(withId(android.R.id.content),
                                                                        childAtPosition(
                                                                                withId(R.id.action_bar_root),
                                                                                0)),
                                                                0),
                                                        0),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Devinettes")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.action_source), withContentDescription("Source"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.toolbar),
                                                childAtPosition(
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        withId(android.R.id.content),
                                                                        0),
                                                                0),
                                                        0)),
                                        2),
                                0),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.action_mail), withContentDescription("Mail"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.toolbar),
                                                childAtPosition(
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        withId(android.R.id.content),
                                                                        0),
                                                                0),
                                                        0)),
                                        2),
                                1),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
