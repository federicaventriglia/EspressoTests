package com.quaap.primary.C3;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.quaap.primary.MainActivity;
import com.quaap.primary.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MathMenuC3 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mathMenuC3() {
        ViewInteraction linearLayout = onView(
                allOf(ViewMatchers.withId(R.id.subject_view_layout),
                        childAtPosition(
                                allOf(withId(R.id.items_list_area),
                                        childAtPosition(
                                                allOf(withId(R.id.horz_list_scroller),
                                                        childAtPosition(
                                                                allOf(withId(R.id.subject_horz_list),
                                                                        childAtPosition(
                                                                                allOf(withId(R.id.steps2and3),
                                                                                        childAtPosition(
                                                                                                withClassName(is("android.widget.LinearLayout")),
                                                                                                5)),
                                                                                1)),
                                                                0)),
                                                0)),
                                1)));
        linearLayout.perform(scrollTo(), click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login_button), withText("Go!"),
                        childAtPosition(
                                allOf(withId(R.id.steps2and3),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.login_layout),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.decor_content_parent),
                                                                                        0)),
                                                                        0)),
                                                        0),
                                                5)),
                                4)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.button_layout),
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.subject_menu_layout),
                                                        childAtPosition(
                                                                allOf(withId(android.R.id.content),
                                                                        childAtPosition(
                                                                                withId(R.id.decor_content_parent),
                                                                                1)),
                                                                0)),
                                                0),
                                        1)),
                        0),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                allOf(withId(R.id.button_layout),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.subject_menu_layout),
                                                                childAtPosition(
                                                                        withId(android.R.id.content),
                                                                        0)),
                                                        0),
                                                1)),
                                0),
                        0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withText("Times / Max 5. Multiple choice"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.button_layout),
                                                childAtPosition(
                                                        childAtPosition(
                                                                allOf(withId(R.id.subject_menu_layout),
                                                                        childAtPosition(
                                                                                withId(android.R.id.content),
                                                                                0)),
                                                                0),
                                                        1)),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withText("Primary: Math ×÷12 (Default)"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                allOf(withId(R.id.action_bar_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                                                        0),
                                                                                0)),
                                                                0)),
                                                0)),
                                1),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));
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
