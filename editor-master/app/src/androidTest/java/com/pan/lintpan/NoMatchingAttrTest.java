/*
Test registrato per mostrare come un elemento venga identificato da Espresso Test Recorder
semplicemente con il ClassName, se esso non ha nessuno dei tre attributi utilizzati per l'element
matching. Questo porta ovviamente nel replay, se sono presenti altri elementi dello stesso tipo,
ad un fallimento della riesecuzione per "matching ambiguo".
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
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NoMatchingAttrTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void noMatchingAttributesTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.showSheetButton), withText("Clicca qui"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatButton")), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatButton")), isDisplayed()));
        appCompatButton3.perform(click());
    }
}
