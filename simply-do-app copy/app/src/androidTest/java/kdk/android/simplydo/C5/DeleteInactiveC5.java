package kdk.android.simplydo.C5;


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

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DeleteInactiveC5 {

    @Rule
    public ActivityTestRule<SimplyDoActivity> mActivityTestRule = new ActivityTestRule<>(SimplyDoActivity.class);

    @Test
    public void deleteInactiveC5() {
        ViewInteraction editText = onView(
                allOf(ViewMatchers.withId(R.id.AddListEditText),
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
                allOf(withId(R.id.AddListButton), withText("Add"),
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

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.ListsListView),
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
                                1)))
                .atPosition(0);
        linearLayout.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.AddItemEditText),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                allOf(withId(R.id.itemsView),
                                                        childAtPosition(
                                                                allOf(withId(R.id.ListsItemsSwitcher),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withId(android.R.id.content),
                                                                                        0),
                                                                                0)),
                                                                1)),
                                                0)),
                                0),
                        isDisplayed()));
        editText2.perform(replaceText("item"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.AddItemButton), withText("Add"),
                        childAtPosition(
                                allOf(withId(R.id.LinearLayout01),
                                        childAtPosition(
                                                allOf(withId(R.id.itemsView),
                                                        childAtPosition(
                                                                allOf(withId(R.id.ListsItemsSwitcher),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withId(android.R.id.content),
                                                                                        0),
                                                                                0)),
                                                                1)),
                                                0)),
                                1),
                        isDisplayed()));
        button2.perform(click());

        DataInteraction linearLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.ItemsListView),
                        childAtPosition(
                                allOf(withId(R.id.itemsView),
                                        childAtPosition(
                                                allOf(withId(R.id.ListsItemsSwitcher),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        withId(android.R.id.content),
                                                                        0),
                                                                0)),
                                                1)),
                                1)))
                .atPosition(0);
        linearLayout2.perform(click());

        pressBack();

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        DataInteraction linearLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.ListsListView),
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
                                1)))
                .atPosition(0);
        linearLayout3.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Delete All Inactive"),
                        childAtPosition(
                                childAtPosition(
                                        withParent(childAtPosition(
                                                withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                                                0)),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withClassName(is("android.widget.ScrollView")),
                                                childAtPosition(
                                                        allOf(withClassName(is("com.android.internal.widget.AlertDialogLayout")),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.FrameLayout")),
                                                                                        0)),
                                                                        0)),
                                                        3)),
                                        0),
                                3)));
        button3.perform(scrollTo(), click());

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
