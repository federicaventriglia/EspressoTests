/*
Test registrato per mostrare come Espresso Test Recorder utilizzi contemporaneamente tutti e tre
gli attributi previsti per l'element matching, se essi sono presenti e se la relativa opzione è
abilitata nelle opzioni dello strumento.
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
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AllMatchingAttrTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void cumulativeMatchingTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.showSheetButton), withText("Clicca qui"), withContentDescription("showSheetButton"), isDisplayed()));
        appCompatButton.perform(click());
    }
}
