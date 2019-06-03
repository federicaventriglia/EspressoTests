package me.tsukanov.counter.ui.C2;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.tsukanov.counter.R;
import me.tsukanov.counter.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewCounterAssertC2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void newCounterAssertC2() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation"), isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.add_counter), isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.edit_name));
        appCompatEditText.perform(scrollTo(), replaceText("New"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.edit_value));
        appCompatEditText2.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                withId(android.R.id.button1));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation"), isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class), isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));
    }
}
