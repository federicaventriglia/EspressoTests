package com.zola.bmi.C2;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.zola.bmi.BMIMain;
import com.zola.bmi.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ScoreCalcC2 {

    @Rule
    public ActivityTestRule<BMIMain> mActivityTestRule = new ActivityTestRule<>(BMIMain.class);

    @Test
    public void scoreCalcC2() {
        ViewInteraction appCompatEditText = onView(
                allOf(ViewMatchers.withId(R.id.weightNum), withContentDescription("weightNum"), isDisplayed()));
        appCompatEditText.perform(replaceText("100"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.heightNum), withContentDescription("heightNum"), isDisplayed()));
        appCompatEditText2.perform(replaceText("190"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.calcBMI), withText("Calculate BMI"), withContentDescription("calcBMI"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.weightNum), withText("100"), withContentDescription("weightNum"), isDisplayed()));
        editText.check(matches(withText("100")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.heightNum), withText("190"), withContentDescription("heightNum"), isDisplayed()));
        editText2.check(matches(withText("190")));

        ViewInteraction textView = onView(
                allOf(withId(R.id.resultLabel), withText("BMI Score = 27.7\nYou are overweight"), withContentDescription("resultLbl"), isDisplayed()));
        textView.check(matches(withText("BMI Score = 27.7 You are overweight")));
    }
}
