package me.tsukanov.counter.ui;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.tsukanov.counter.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RenameCounterTextMatchNoChildAtPosition {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void renameCounterTextMatchNoChildAtPosition() {
        /*ViewInteraction textView = onView(
                allOf(,isDisplayed()));
        textView.check(matches(withText("New counter")));*/

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.menu_edit), withContentDescription("Edit counter"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edit_name), withContentDescription("dialog_edit_text")));
        appCompatEditText.perform(scrollTo(), replaceText("Test"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                withId(android.R.id.button1));
        appCompatButton.perform(scrollTo(), click());

        /*ViewInteraction textView2 = onView(
                allOf(, isDisplayed()));
        textView2.check(matches(withText("Test")));*/
    }
}
