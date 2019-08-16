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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
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
public class MultipleListsC3 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void multipleListsC3() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Create new list"),
                        childAtPosition(
                                childAtPosition(
                                        withParent(childAtPosition(
                                                withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                                                0)),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.dialog_text_field),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(android.R.id.content),
                                                childAtPosition(
                                                        withClassName(is("android.widget.LinearLayout")),
                                                        1)),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("A"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button_dialog_ok), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(android.R.id.content),
                                                childAtPosition(
                                                        withClassName(is("android.widget.LinearLayout")),
                                                        1)),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText("Create new list"),
                        childAtPosition(
                                childAtPosition(
                                        withParent(childAtPosition(
                                                withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                                                0)),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.dialog_text_field),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(android.R.id.content),
                                                childAtPosition(
                                                        withClassName(is("android.widget.LinearLayout")),
                                                        1)),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("B"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.button_dialog_ok), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(android.R.id.content),
                                                childAtPosition(
                                                        withClassName(is("android.widget.LinearLayout")),
                                                        1)),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_main),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.drawer_layout),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.action_bar_root),
                                                                                        1)),
                                                                        0)),
                                                        0),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("A"),
                        childAtPosition(
                                allOf(withId(R.id.nav_drawer_content),
                                        childAtPosition(
                                                allOf(withId(R.id.nav_drawer_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.drawer_layout),
                                                                        childAtPosition(
                                                                                allOf(withId(android.R.id.content),
                                                                                        childAtPosition(
                                                                                                withId(R.id.action_bar_root),
                                                                                                0)),
                                                                                0)),
                                                                1)),
                                                1)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("A")));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("B"),
                        childAtPosition(
                                allOf(withId(R.id.nav_drawer_content),
                                        childAtPosition(
                                                allOf(withId(R.id.nav_drawer_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.drawer_layout),
                                                                        childAtPosition(
                                                                                allOf(withId(android.R.id.content),
                                                                                        childAtPosition(
                                                                                                withId(R.id.action_bar_root),
                                                                                                0)),
                                                                                0)),
                                                                1)),
                                                1)),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("B")));
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
