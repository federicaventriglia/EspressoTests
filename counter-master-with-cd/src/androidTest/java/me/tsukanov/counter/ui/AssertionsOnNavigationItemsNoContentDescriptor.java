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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AssertionsOnNavigationItemsNoContentDescriptor {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void assertionsOnNavigationItemsNoContentDescriptor() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.menu_reset), isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.menu_edit), isDisplayed()));
        textView2.check(matches(isDisplayed()));

       /* ViewInteraction imageView = onView(
                allOf(, isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction imageButton = onView(
                allOf(, isDisplayed()));
        imageButton.check(matches(isDisplayed()));*/
    }
}
