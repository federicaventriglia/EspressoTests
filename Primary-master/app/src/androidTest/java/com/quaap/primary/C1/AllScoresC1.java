package com.quaap.primary.C1;


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

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
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
public class AllScoresC1 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void allScoresC1() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction linearLayout = onView(
                allOf(ViewMatchers.withId(R.id.content),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                childAtPosition(
                                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                        0),
                                                0),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.title), withText("All Scores"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.content),
                                                childAtPosition(
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                        0),
                                                                0),
                                                        0)),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("All Scores")));

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("All Scores"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.content),
                                                childAtPosition(
                                                        withParent(childAtPosition(
                                                                withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                                                                0)),
                                                        1)),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction imageView = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.action_bar),
                                                childAtPosition(
                                                        allOf(withId(R.id.action_bar_container),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                        0)),
                                                                        0)),
                                                        0)),
                                        1),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withText("Primary"),
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
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Primary")));

        ViewInteraction textView3 = onView(
                allOf(withText("☺ Default: 0"),
                        childAtPosition(
                                allOf(withId(R.id.scores_list),
                                        childAtPosition(
                                                allOf(withId(R.id.scores_scroll),
                                                        childAtPosition(
                                                                allOf(withId(R.id.activity_scores),
                                                                        childAtPosition(
                                                                                allOf(withId(android.R.id.content),
                                                                                        childAtPosition(
                                                                                                withId(R.id.decor_content_parent),
                                                                                                1)),
                                                                                0)),
                                                                0)),
                                                0)),
                                0),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        pressBack();
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
