package com.zola.bmi.C6;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.zola.bmi.BMIMain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UITextAssertC6 {

    @Rule
    public ActivityTestRule<BMIMain> mActivityTestRule = new ActivityTestRule<>(BMIMain.class);

    @Test
    public void uITextAssertC6() {
        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("Kilograms"), isDisplayed()));
        textView.check(matches(withText("Kilograms")));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("Centimetres"), isDisplayed()));
        textView2.check(matches(withText("Centimetres")));

        ViewInteraction textView3 = onView(
                allOf(withText("BMI Calculator"), isDisplayed()));
        textView3.check(matches(withText("BMI Calculator")));
    }
}
