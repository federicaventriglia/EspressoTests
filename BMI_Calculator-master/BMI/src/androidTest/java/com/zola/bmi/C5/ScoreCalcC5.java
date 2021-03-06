package com.zola.bmi.C5;


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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
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
public class ScoreCalcC5 {

    @Rule
    public ActivityTestRule<BMIMain> mActivityTestRule = new ActivityTestRule<>(BMIMain.class);

    @Test
    public void scoreCalcC5() {
        ViewInteraction appCompatEditText = onView(
                allOf(ViewMatchers.withId(R.id.weightNum),
                        childAtPosition(
                                allOf(
                                        childAtPosition(
                                                allOf(withId(R.id.container),
                                                        childAtPosition(
                                                                allOf(withId(android.R.id.content),
                                                                        childAtPosition(
                                                                                allOf(withId(R.id.decor_content_parent),
                                                                                        childAtPosition(
                                                                                                withClassName(is("android.widget.FrameLayout")),
                                                                                                0)),
                                                                                0)),
                                                                0)),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("100"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.heightNum),
                        childAtPosition(
                                allOf(
                                        childAtPosition(
                                                allOf(withId(R.id.container),
                                                        childAtPosition(
                                                                allOf(withId(android.R.id.content),
                                                                        childAtPosition(
                                                                                allOf(withId(R.id.decor_content_parent),
                                                                                        childAtPosition(
                                                                                                withClassName(is("android.widget.FrameLayout")),
                                                                                                0)),
                                                                                0)),
                                                                0)),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("190"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.calcBMI), withText("Calculate BMI"),
                        childAtPosition(
                                allOf(
                                        childAtPosition(
                                                allOf(withId(R.id.container),
                                                        childAtPosition(
                                                                allOf(withId(android.R.id.content),
                                                                        childAtPosition(
                                                                                allOf(withId(R.id.decor_content_parent),
                                                                                        childAtPosition(
                                                                                                withClassName(is("android.widget.FrameLayout")),
                                                                                                0)),
                                                                                0)),
                                                                0)),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.weightNum), withText("100"),
                        childAtPosition(
                                allOf(
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
                                                0)),
                                1),
                        isDisplayed()));
        editText.check(matches(withText("100")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.heightNum), withText("190"),
                        childAtPosition(
                                allOf(
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
                                                0)),
                                3),
                        isDisplayed()));
        editText2.check(matches(withText("190")));

        ViewInteraction textView = onView(
                allOf(withId(R.id.resultLabel), withText("BMI Score = 27.7\nYou are overweight"),
                        childAtPosition(
                                allOf(
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
                                                0)),
                                5),
                        isDisplayed()));
        textView.check(matches(withText("BMI Score = 27.7 You are overweight")));
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
