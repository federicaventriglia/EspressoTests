package com.quaap.primary.C6;


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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BrowseSubjectsC6 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void browseSubjectsC6() {
        ViewInteraction appCompatButton = onView(
                allOf(ViewMatchers.withId(R.id.login_button), withText("Go!")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton = onView(
                allOf( isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction linearLayout = onView(
                withId(R.id.subject_view_layout));
        linearLayout.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.login_button), withText("Go!")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf( isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction linearLayout2 = onView(
                withId(R.id.subject_view_layout));
        linearLayout2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.login_button), withText("Go!")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf( isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction linearLayout3 = onView(
                withId(R.id.subject_view_layout));
        linearLayout3.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.login_button), withText("Go!")));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf( isDisplayed()));
        appCompatImageButton4.perform(click());
    }
}
