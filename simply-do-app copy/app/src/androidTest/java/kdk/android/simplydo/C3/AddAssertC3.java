package kdk.android.simplydo.C3;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import kdk.android.simplydo.R;
import kdk.android.simplydo.SimplyDoActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddAssertC3 {

    @Rule
    public ActivityTestRule<SimplyDoActivity> mActivityTestRule = new ActivityTestRule<>(SimplyDoActivity.class);

    @Test
    public void addAssertC3() {
        ViewInteraction editText = onView(
                allOf(ViewMatchers.withId(R.id.AddListEditText), withContentDescription("addlistedittext"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                allOf(withId(R.id.listsView),
                                                        childAtPosition(
                                                                allOf(withId(R.id.ListsItemsSwitcher),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withId(android.R.id.content),
                                                                                        0),
                                                                                0)),
                                                                0)),
                                                0)),
                                0),
                        isDisplayed()));
        editText.perform(replaceText("new"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.AddListButton), withText("Add"), withContentDescription("Add"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                allOf(withId(R.id.listsView),
                                                        childAtPosition(
                                                                allOf(withId(R.id.ListsItemsSwitcher),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withId(android.R.id.content),
                                                                                        0),
                                                                                0)),
                                                                0)),
                                                0)),
                                1),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.AddListButton), withContentDescription("Add"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                allOf(withId(R.id.listsView),
                                                        childAtPosition(
                                                                allOf(withId(R.id.ListsItemsSwitcher),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withId(android.R.id.content),
                                                                                        0),
                                                                                0)),
                                                                0)),
                                                0)),
                                1),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.ListsListView),
                                childAtPosition(
                                        allOf(withId(R.id.listsView),
                                                childAtPosition(
                                                        allOf(withId(R.id.ListsItemsSwitcher),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withId(android.R.id.content),
                                                                                0),
                                                                        0)),
                                                        0)),
                                        1)),
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
