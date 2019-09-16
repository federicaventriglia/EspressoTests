/*
Test registrato per mostrare come, disattivando l'element matching tramite attributo text,
Espresso Test Recorder non riesca, per un'asserzione, ad identificare una TextView senza id o
contentDescription.
*/

package com.pan.lintpan;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AssertionMatchTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void assertionMatchTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.showSheetButton), isDisplayed()));
        appCompatButton.perform(click());

        /*
        ViewInteraction textView = onView(
                allOf(, isDisplayed()));
        textView.check(matches(withText("Guarda qui")));
        */
    }
}
