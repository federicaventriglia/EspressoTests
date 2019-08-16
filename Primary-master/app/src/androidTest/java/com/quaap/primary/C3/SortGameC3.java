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
public class SortGameC3 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sortGameC3() {
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
                                5)));
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

        ViewInteraction button = onView(
                allOf(withText("Level 1"),
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
                                                        2)),
                                        0),
                                0)));
        button.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.centercol),
                        childAtPosition(
                                allOf(withId(R.id.meta_center),
                                        childAtPosition(
                                                allOf(withId(R.id.std_game),
                                                        childAtPosition(
                                                                allOf(withId(android.R.id.content),
                                                                        childAtPosition(
                                                                                allOf(withId(R.id.decor_content_parent),
                                                                                        childAtPosition(
                                                                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                                0)),
                                                                                1)),
                                                                0)),
                                                1)),
                                0),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction linearLayout3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.scores_area),
                                childAtPosition(
                                        allOf(withId(R.id.std_game),
                                                childAtPosition(
                                                        allOf(withId(android.R.id.content),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.decor_content_parent),
                                                                                childAtPosition(
                                                                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                        0)),
                                                                        1)),
                                                        0)),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

        ViewInteraction linearLayout4 = onView(
                allOf(withId(R.id.scores_level),
                        childAtPosition(
                                allOf(withId(R.id.scores_area),
                                        childAtPosition(
                                                allOf(withId(R.id.std_game),
                                                        childAtPosition(
                                                                allOf(withId(android.R.id.content),
                                                                        childAtPosition(
                                                                                allOf(withId(R.id.decor_content_parent),
                                                                                        childAtPosition(
                                                                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                                0)),
                                                                                1)),
                                                                0)),
                                                0)),
                                1),
                        isDisplayed()));
        linearLayout4.check(matches(isDisplayed()));
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
