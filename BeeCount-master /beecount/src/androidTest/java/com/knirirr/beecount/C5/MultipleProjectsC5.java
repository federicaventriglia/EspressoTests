package com.knirirr.beecount.C5;


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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MultipleProjectsC5 {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Test
    public void multipleProjectsC5() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withClassName(is("android.widget.LinearLayout")),
                                                childAtPosition(
                                                        allOf(withClassName(is("android.widget.LinearLayout")),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.FrameLayout")),
                                                                                        0)),
                                                                        0)),
                                                        3)),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(ViewMatchers.withId(R.id.viewProjectButton), withText("View Projects"),
                        childAtPosition(
                                allOf(withId(R.id.welcomeButtons),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.baseLayout),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.decor_content_parent),
                                                                                        0)),
                                                                        0)),
                                                        0),
                                                2)),
                                1)));
        appCompatButton2.perform(scrollTo(), click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Create new project"),
                        childAtPosition(
                                childAtPosition(
                                        withParent(childAtPosition(
                                                withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                                                0)),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.newprojName),
                        childAtPosition(
                                allOf(withId(R.id.newprojScreen),
                                        childAtPosition(
                                                allOf(withId(android.R.id.content),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.LinearLayout")),
                                                                                        1),
                                                                                0)),
                                                                0)),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.addNewCountButton), withText("Add Count"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.newprojScreen),
                                                childAtPosition(
                                                        allOf(withId(android.R.id.content),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.FrameLayout")),
                                                                                        0)),
                                                                        0)),
                                                        0)),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction newCount = onView(
                childAtPosition(
                        allOf(withId(R.id.newCountLayout),
                                childAtPosition(
                                        allOf(withId(R.id.newCountList),
                                                childAtPosition(
                                                        allOf(withId(R.id.newprojScreen),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.decor_content_parent),
                                                                                        0)),
                                                                        0)),
                                                        2)),
                                        0)),
                        0));
        newCount.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.newprojSaveButton), withText("Save Project"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.newprojScreen),
                                                childAtPosition(
                                                        allOf(withId(android.R.id.content),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.FrameLayout")),
                                                                                        0)),
                                                                        0)),
                                                        0)),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.title), withText("Create new project"),
                        childAtPosition(
                                childAtPosition(
                                        withParent(childAtPosition(
                                                withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                                                0)),
                                        0),
                                0),
                        isDisplayed()));
        textView2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.newprojName),
                        childAtPosition(
                                allOf(withId(R.id.newprojScreen),
                                        childAtPosition(
                                                allOf(withId(android.R.id.content),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.LinearLayout")),
                                                                                        1),
                                                                                0)),
                                                                0)),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.addNewCountButton), withText("Add Count"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.newprojScreen),
                                                childAtPosition(
                                                        allOf(withId(android.R.id.content),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.FrameLayout")),
                                                                                        0)),
                                                                        0)),
                                                        0)),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction newCount2 = onView(
                childAtPosition(
                        allOf(withId(R.id.newCountLayout),
                                childAtPosition(
                                        allOf(withId(R.id.newCountList),
                                                childAtPosition(
                                                        allOf(withId(R.id.newprojScreen),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.decor_content_parent),
                                                                                        0)),
                                                                        0)),
                                                        2)),
                                        0)),
                        0));
        newCount2.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.newprojSaveButton), withText("Save Project"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.newprojScreen),
                                                childAtPosition(
                                                        allOf(withId(android.R.id.content),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.FrameLayout")),
                                                                                        0)),
                                                                        0)),
                                                        0)),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.txtTitle), withText("2"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(android.R.id.list),
                                                childAtPosition(
                                                        allOf(withId(R.id.list_view),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                                                        1)),
                                                                        0)),
                                                        0)),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("2")));
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
