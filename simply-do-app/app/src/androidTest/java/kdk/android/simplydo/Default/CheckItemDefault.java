package kdk.android.simplydo.Default;


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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CheckItemDefault {

    @Rule
    public ActivityTestRule<SimplyDoActivity> mActivityTestRule = new ActivityTestRule<>(SimplyDoActivity.class);

    @Test
    public void checkItemDefault() {
        ViewInteraction editText = onView(
                allOf(ViewMatchers.withId(R.id.AddListEditText), withContentDescription("addlistedittext"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.listsView),
                                                0)),
                                0),
                        isDisplayed()));
        editText.perform(replaceText("List"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.AddListButton), withText("Add"), withContentDescription("Add"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.listsView),
                                                0)),
                                1),
                        isDisplayed()));
        button.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.ListsListView),
                        childAtPosition(
                                withId(R.id.listsView),
                                1)))
                .atPosition(0);
        linearLayout.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.AddItemEditText), withContentDescription("addItemEditText"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.itemsView),
                                                0)),
                                0),
                        isDisplayed()));
        editText2.perform(replaceText("Item"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.AddItemButton), withText("Add"), withContentDescription("Add"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                withId(R.id.itemsView),
                                                0)),
                                1),
                        isDisplayed()));
        button2.perform(click());

        DataInteraction linearLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.ItemsListView),
                        childAtPosition(
                                withId(R.id.itemsView),
                                1)))
                .atPosition(0);
        linearLayout2.perform(click());
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
