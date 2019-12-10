package com.knirirr.beecount.C3;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.knirirr.beecount.R;
import com.knirirr.beecount.WelcomeActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TopMenuAssertC3 {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Test
    public void topMenuAssertC3() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withClassName(is("android.widget.LinearLayout")),
                                                childAtPosition(
                                                        allOf(withClassName(is("android.widget.LinearLayout")),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.FrameLayout")),
                                                                                        0)),
                                                                        0)),
                                                        3)),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(ViewMatchers.withId(R.id.newProject), withContentDescription("New Project"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.action_bar),
                                                childAtPosition(
                                                        allOf(withId(R.id.action_bar_container),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                        0)),
                                                                        0)),
                                                        0)),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.viewProjects), withContentDescription("View Projects"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.action_bar),
                                                childAtPosition(
                                                        allOf(withId(R.id.action_bar_container),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                        0)),
                                                                        0)),
                                                        0)),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.action_bar),
                                                childAtPosition(
                                                        allOf(withId(R.id.action_bar_container),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                        0)),
                                                                        0)),
                                                        0)),
                                        0),
                                2),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.viewProjectButton), withText("View Projects"),
                        childAtPosition(
                                allOf(withId(R.id.welcomeButtons),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.baseLayout),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.decor_content_parent),
                                                                                        0)),
                                                                        0)),
                                                        0),
                                                2)),
                                1)));
        appCompatButton2.perform(scrollTo(), click());
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
