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
public class AddDeleteUserC5 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addDeleteUserC5() {
        ViewInteraction linearLayout = onView(
                childAtPosition(
                        allOf(ViewMatchers.withId(R.id.items_list_area),
                                childAtPosition(
                                        allOf(withId(R.id.horz_list_scroller),
                                                childAtPosition(
                                                        allOf(withId(R.id.user_horz_list),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(R.id.login_layout),
                                                                                0),
                                                                        2)),
                                                        0)),
                                        0)),
                        0));
        linearLayout.perform(scrollTo(), click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.add_list_item_button),
                        childAtPosition(
                                allOf(withId(R.id.user_horz_list),
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
                                                2)),
                                1)));
        appCompatImageView.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username_input),
                        childAtPosition(
                                allOf(withId(R.id.login_new_user_area),
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
                                                4)),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("user"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.user_added_button), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.login_new_user_area),
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
                                                4)),
                                2)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.items_list_area),
                                childAtPosition(
                                        allOf(withId(R.id.horz_list_scroller),
                                                childAtPosition(
                                                        allOf(withId(R.id.user_horz_list),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(R.id.login_layout),
                                                                                0),
                                                                        1)),
                                                        0)),
                                        0)),
                        1),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.delete_user_link), withText("Delete User"),
                        childAtPosition(
                                allOf(withId(R.id.user_controls_area),
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
                                                3)),
                                1)));
        appCompatTextView.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("Delete"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.buttonPanel),
                                                childAtPosition(
                                                        allOf(withId(R.id.parentPanel),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.action_bar_root),
                                                                                        0)),
                                                                        0)),
                                                        3)),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.items_list_area),
                        childAtPosition(
                                allOf(withId(R.id.horz_list_scroller),
                                        childAtPosition(
                                                allOf(withId(R.id.user_horz_list),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        allOf(withId(R.id.login_layout),
                                                                                childAtPosition(
                                                                                        withId(android.R.id.content),
                                                                                        0)),
                                                                        0),
                                                                1)),
                                                0)),
                                0),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));
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
