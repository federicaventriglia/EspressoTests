/*
Test registrato per mostrare come, settando gli attributi id dei Buttons mediante la regola lint
creata, essi siano univocamente identificati da Espresso Test Recorder e il replay del test non
fallisca, anche se i suddetti Buttons vengono spostati in una posizione diversa del relativo layout.
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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ButtonsWithID1Test {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void buttonsWithID1Test() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.showSheetButton), withText("Clicca qui"), isDisplayed()));
        appCompatButton.perform(click());

        /*
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.fragment_bottom_sheet_Button_8ghN), withText("ciao"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.fragment_bottom_sheet_Button_35Oz), withText("ciao"), isDisplayed()));
        appCompatButton3.perform(click());
        */

    }
}
