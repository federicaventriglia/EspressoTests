package me.tsukanov.counter.ui.C3;


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
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditAssertC3 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void editAssertC3() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.menu_edit), withContentDescription("Edit counter"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.action_bar),
                                                childAtPosition(
                                                        allOf(withId(R.id.action_bar_container),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.FrameLayout")),
                                                                                        0)),
                                                                        1)),
                                                        0)),
                                        2),
                                1),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edit_name),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(android.R.id.custom),
                                                        childAtPosition(
                                                                allOf(withClassName(is("android.widget.FrameLayout")),
                                                                        childAtPosition(
                                                                                withClassName(is("com.android.internal.widget.AlertDialogLayout")),
                                                                                2)),
                                                                0)),
                                                0),
                                        0),
                                1)));
        appCompatEditText.perform(scrollTo(), replaceText("Test"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edit_value),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(android.R.id.custom),
                                                        childAtPosition(
                                                                allOf(withClassName(is("android.widget.FrameLayout")),
                                                                        childAtPosition(
                                                                                withClassName(is("com.android.internal.widget.AlertDialogLayout")),
                                                                                2)),
                                                                0)),
                                                0),
                                        0),
                                3)));
        appCompatEditText2.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withClassName(is("android.widget.ScrollView")),
                                                childAtPosition(
                                                        allOf(withClassName(is("com.android.internal.widget.AlertDialogLayout")),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.FrameLayout")),
                                                                                        0)),
                                                                        0)),
                                                        3)),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.incrementButton), withContentDescription("incrementButton"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.content_frame),
                                                childAtPosition(
                                                        allOf(withId(R.id.drawer_layout),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.decor_content_parent),
                                                                                        0)),
                                                                        0)),
                                                        0)),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.incrementButton), withContentDescription("incrementButton"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.content_frame),
                                                childAtPosition(
                                                        allOf(withId(R.id.drawer_layout),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.decor_content_parent),
                                                                                        0)),
                                                                        0)),
                                                        0)),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction textView = onView(
                allOf(childAtPosition(
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
        textView.check(matches(withText("Test")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.counterLabel),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.content_frame),
                                                        childAtPosition(
                                                                allOf(withId(R.id.drawer_layout),
                                                                        childAtPosition(
                                                                                withId(android.R.id.content),
                                                                                0)),
                                                                0)),
                                                0),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("3")));
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
