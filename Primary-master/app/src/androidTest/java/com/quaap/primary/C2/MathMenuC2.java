package com.quaap.primary.C2;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.quaap.primary.MainActivity;
import com.quaap.primary.R;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MathMenuC2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mathMenuC2() {
        ViewInteraction linearLayout = onView(
                ViewMatchers.withId(R.id.subject_view_layout));
        linearLayout.perform(scrollTo(), click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login_button), withText("Go!")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction linearLayout2 = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class), isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.Button.class), isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withText("Times / Max 5. Multiple choice"), isDisplayed()));
        textView.check(matches(withText("Times / Max 5. Multiple choice")));

        ViewInteraction textView2 = onView(
                allOf(withText("Primary: Math ×÷12 (Default)"), isDisplayed()));
        textView2.check(matches(isDisplayed()));
    }
}
