package com.zola.bmi.C3;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.zola.bmi.BMIMain;
import com.zola.bmi.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UIAssertC3 {

    @Rule
    public ActivityTestRule<BMIMain> mActivityTestRule = new ActivityTestRule<>(BMIMain.class);

    @Test
    public void uIAssertC3() {
        ViewInteraction viewGroup = onView(
                allOf(ViewMatchers.withId(R.id.action_bar),
                        childAtPosition(
                                allOf(withId(R.id.action_bar_container),
                                        childAtPosition(
                                                allOf(withId(R.id.decor_content_parent),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                0),
                                                                        0),
                                                                0)),
                                                0)),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction spinner = onView(
                allOf(withId(R.id.weightSpinner),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.container),
                                                childAtPosition(
                                                        allOf(withId(android.R.id.content),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                        0)),
                                                                        1)),
                                                        0)),
                                        0),
                                0),
                        isDisplayed()));
        spinner.check(matches(isDisplayed()));

        ViewInteraction spinner2 = onView(
                allOf(withId(R.id.heightSpinner),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.container),
                                                childAtPosition(
                                                        allOf(withId(android.R.id.content),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                        0)),
                                                                        1)),
                                                        0)),
                                        0),
                                2),
                        isDisplayed()));
        spinner2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.calcBMI),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.container),
                                                childAtPosition(
                                                        allOf(withId(android.R.id.content),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                        0)),
                                                                        1)),
                                                        0)),
                                        0),
                                4),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.calcBMI),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.container),
                                                childAtPosition(
                                                        allOf(withId(android.R.id.content),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                        0)),
                                                                        1)),
                                                        0)),
                                        0),
                                4),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
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
