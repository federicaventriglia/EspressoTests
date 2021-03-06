/*
Test registrato per mostrare come, settando gli attributi id dei Buttons mediante la regola lint
creata, essi siano univocamente identificati da Espresso Test Recorder. Il replay del test, però,
fallisce se i suddetti Buttons vengono spostati in una posizione diversa del relativo layout,
poiché è stata impostata una MAX Uidepth pari a 2 e, quindi, essi sono stati identificati anche
con la loro posizione all'interno degli elementi parent.
 */

package com.pan.lintpan;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
public class ButtonsWithID2Test {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void buttonsWithID2Test() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.showSheetButton), withText("Clicca qui"),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        /*
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.fragment_bottom_sheet_Button_8ghN), withText("ciao"),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());


        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.fragment_bottom_sheet_Button_35Oz), withText("ciao"),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());
        */

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
