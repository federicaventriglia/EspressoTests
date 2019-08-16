package com.woefe.shoppinglist.C3;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.woefe.shoppinglist.R;
import com.woefe.shoppinglist.activity.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SortItemsC3 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sortItemsC3() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab_add),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.content_frame),
                                                childAtPosition(
                                                        childAtPosition(
                                                                allOf(withId(R.id.drawer_layout),
                                                                        childAtPosition(
                                                                                withId(android.R.id.content),
                                                                                0)),
                                                                0),
                                                        1)),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.new_item_description),
                        childAtPosition(
                                allOf(withId(R.id.layout_add_item),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.content_frame),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(R.id.drawer_layout),
                                                                                0),
                                                                        1)),
                                                        0),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Test"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.new_item_quantity),
                        childAtPosition(
                                allOf(withId(R.id.layout_add_item),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.content_frame),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(R.id.drawer_layout),
                                                                                0),
                                                                        1)),
                                                        0),
                                                2)),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.button_add_new_item), withContentDescription("Done"),
                        childAtPosition(
                                allOf(withId(R.id.layout_add_item),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.content_frame),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(R.id.drawer_layout),
                                                                                0),
                                                                        1)),
                                                        0),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.new_item_description),
                        childAtPosition(
                                allOf(withId(R.id.layout_add_item),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.content_frame),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(R.id.drawer_layout),
                                                                                0),
                                                                        1)),
                                                        0),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Test1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.new_item_quantity),
                        childAtPosition(
                                allOf(withId(R.id.layout_add_item),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.content_frame),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(R.id.drawer_layout),
                                                                                0),
                                                                        1)),
                                                        0),
                                                2)),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.button_add_new_item), withContentDescription("Done"),
                        childAtPosition(
                                allOf(withId(R.id.layout_add_item),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.content_frame),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(R.id.drawer_layout),
                                                                                0),
                                                                        1)),
                                                        0),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.new_item_description),
                        childAtPosition(
                                allOf(withId(R.id.layout_add_item),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.content_frame),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(R.id.drawer_layout),
                                                                                0),
                                                                        1)),
                                                        0),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("Test2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.new_item_quantity),
                        childAtPosition(
                                allOf(withId(R.id.layout_add_item),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.content_frame),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(R.id.drawer_layout),
                                                                                0),
                                                                        1)),
                                                        0),
                                                2)),
                                3),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.button_add_new_item), withContentDescription("Done"),
                        childAtPosition(
                                allOf(withId(R.id.layout_add_item),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.content_frame),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(R.id.drawer_layout),
                                                                                0),
                                                                        1)),
                                                        0),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withContentDescription("Sort"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.toolbar_main),
                                                childAtPosition(
                                                        childAtPosition(
                                                                allOf(withId(R.id.drawer_layout),
                                                                        childAtPosition(
                                                                                withId(android.R.id.content),
                                                                                0)),
                                                                0),
                                                        0)),
                                        2),
                                1),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Z-A"),
                        childAtPosition(
                                childAtPosition(
                                        withParent(childAtPosition(
                                                withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                                                0)),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.text_description), withText("Test2"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.shoppingListView),
                                                childAtPosition(
                                                        childAtPosition(
                                                                allOf(withId(R.id.content_frame),
                                                                        childAtPosition(
                                                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                                                1)),
                                                                0),
                                                        0)),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Test2")));
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
