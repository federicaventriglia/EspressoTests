package com.zola.bmi.C6;


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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InputDataC6 {

    @Rule
    public ActivityTestRule<BMIMain> mActivityTestRule = new ActivityTestRule<>(BMIMain.class);

    @Test
    public void inputDataC6() {
        ViewInteraction appCompatEditText = onView(
                allOf(ViewMatchers.withId(R.id.weightNum), isDisplayed()));
        appCompatEditText.perform(replaceText("130"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.heightNum), isDisplayed()));
        appCompatEditText2.perform(replaceText("155"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.calcBMI), withText("Calculate BMI"), isDisplayed()));
        appCompatButton.perform(click());
    }
}
