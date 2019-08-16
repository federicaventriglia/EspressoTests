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
public class GuessAssertC2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void guessAssertC2() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.riddle_title), withText("The Sphinx's Riddle"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.guess), isDisplayed()));
        appCompatEditText.perform(replaceText("me"), closeSoftKeyboard());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.btnGuess), isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("My Dearest Vexxed..."), isDisplayed()));
        textView.check(matches(withText("My Dearest Vexxed...")));

        ViewInteraction editText = onView(
                allOf(withId(R.id.guess), withText("me"), isDisplayed()));
        editText.check(matches(withText("me")));
    }
}
