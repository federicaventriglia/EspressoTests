package com.zola.bmi.C4;


import android.support.test.espresso.DataInteraction;
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MixedMetricsC4 {

    @Rule
    public ActivityTestRule<BMIMain> mActivityTestRule = new ActivityTestRule<>(BMIMain.class);

    @Test
    public void mixedMetricsC4() {
        ViewInteraction appCompatSpinner = onView(
                allOf(ViewMatchers.withId(R.id.weightSpinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                4),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView),
                        0))
                .atPosition(1);
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.weightNum),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.weightNum),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("130"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.heightNum),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("160"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.calcBMI), withText("Calculate BMI"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.calcBMI), withText("Calculate BMI"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());
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
