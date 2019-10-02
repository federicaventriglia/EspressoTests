package com.quaap.primary.C2;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.quaap.primary.MainActivity;
import com.quaap.primary.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AllScoresC2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void allScoresC2() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction linearLayout = onView(
                allOf(ViewMatchers.withId(R.id.content), isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.title), withText("All Scores"), isDisplayed()));
        textView.check(matches(withText("All Scores")));

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("All Scores"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction imageView = onView(
                allOf(withContentDescription("More options"), isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withText("Primary"), isDisplayed()));
        textView2.check(matches(withText("Primary")));

        ViewInteraction textView3 = onView(
                allOf(withText("☺ Default: 0"), isDisplayed()));
        textView3.check(matches(isDisplayed()));

        pressBack();
    }
}
