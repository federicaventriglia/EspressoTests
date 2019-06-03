package com.quaap.primary.C5;


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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BrowseSubjectsC5 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void browseSubjectsC5() {
        ViewInteraction appCompatButton = onView(
                allOf(ViewMatchers.withId(R.id.login_button),
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

        ViewInteraction appCompatImageButton = onView(
                allOf(
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                allOf(withId(R.id.action_bar_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.LinearLayout")),
                                                                                        1),
                                                                                0)),
                                                                1)),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.subject_view_layout),
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

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.login_button),
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
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                allOf(withId(R.id.action_bar_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.LinearLayout")),
                                                                                        1),
                                                                                0)),
                                                                1)),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.subject_view_layout),
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
                                2)));
        linearLayout2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.login_button),
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
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                allOf(withId(R.id.action_bar_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.LinearLayout")),
                                                                                        1),
                                                                                0)),
                                                                1)),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.subject_view_layout),
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
                                3)));
        linearLayout3.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.login_button),
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
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                allOf(withId(R.id.action_bar_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.LinearLayout")),
                                                                                        1),
                                                                                0)),
                                                                1)),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton4.perform(click());
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
