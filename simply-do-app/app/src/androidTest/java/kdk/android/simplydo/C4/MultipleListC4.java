package kdk.android.simplydo.C4;


import android.support.test.espresso.DataInteraction;
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

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MultipleListC4 {

    @Rule
    public ActivityTestRule<SimplyDoActivity> mActivityTestRule = new ActivityTestRule<>(SimplyDoActivity.class);

    @Test
    public void multipleListC4() {
        ViewInteraction editText = onView(
                allOf(ViewMatchers.withId(R.id.AddListEditText),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.listsView),
                                                0)),
                                0),
                        isDisplayed()));
        editText.perform(replaceText("one"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.AddListButton), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.listsView),
                                                0)),
                                1),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.AddListEditText),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.listsView),
                                                0)),
                                0),
                        isDisplayed()));
        editText2.perform(replaceText("two"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.AddListButton), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.listsView),
                                                0)),
                                1),
                        isDisplayed()));
        button2.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.ListsListView),
                        childAtPosition(
                                withId(R.id.listsView),
                                1)))
                .atPosition(0);
        linearLayout.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.AddItemEditText),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.itemsView),
                                                0)),
                                0),
                        isDisplayed()));
        editText3.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.AddItemButton), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.itemsView),
                                                0)),
                                1),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.AddItemEditText),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.itemsView),
                                                0)),
                                0),
                        isDisplayed()));
        editText4.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.AddItemButton), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.itemsView),
                                                0)),
                                1),
                        isDisplayed()));
        button4.perform(click());

        DataInteraction linearLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.ItemsListView),
                        childAtPosition(
                                withId(R.id.itemsView),
                                1)))
                .atPosition(1);
        linearLayout2.perform(click());

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
