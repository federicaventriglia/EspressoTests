package com.knirirr.beecount.C1;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.knirirr.beecount.R;
import com.knirirr.beecount.WelcomeActivity;

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
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewListCheckC1 {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Test
    public void newListCheckC1() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(ViewMatchers.withId(R.id.newProjectButton), withText("New Project"),
                        childAtPosition(
                                allOf(withId(R.id.welcomeButtons),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                0)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.newprojName),
                        childAtPosition(
                                allOf(withId(R.id.newprojScreen),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.addNewCountButton), withText("Add Count"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.newprojScreen),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.addNewCountButton), withText("Add Count"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.newprojScreen),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.newprojSaveButton), withText("Save Project"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.newprojScreen),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction newCount = onView(
                childAtPosition(
                        allOf(withId(R.id.newCountLayout),
                                childAtPosition(
                                        withId(R.id.newCountList),
                                        0)),
                        1));
        newCount.perform(scrollTo(), click());

        ViewInteraction newCount2 = onView(
                childAtPosition(
                        allOf(withId(R.id.newCountLayout),
                                childAtPosition(
                                        withId(R.id.newCountList),
                                        0)),
                        1));
        newCount2.perform(scrollTo(), replaceText("2"), closeSoftKeyboard());

        ViewInteraction newCount3 = onView(
                childAtPosition(
                        allOf(withId(R.id.newCountLayout),
                                childAtPosition(
                                        withId(R.id.newCountList),
                                        0)),
                        0));
        newCount3.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.newprojSaveButton), withText("Save Project"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.newprojScreen),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        withId(R.id.list_view),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));
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
