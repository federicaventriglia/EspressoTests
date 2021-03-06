package kdk.android.simplydo.C2;


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
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DeleteInactiveC2 {

    @Rule
    public ActivityTestRule<SimplyDoActivity> mActivityTestRule = new ActivityTestRule<>(SimplyDoActivity.class);

    @Test
    public void deleteInactiveC2() {
        ViewInteraction editText = onView(
                allOf(ViewMatchers.withId(R.id.AddListEditText), withContentDescription("addlistedittext"),
                        isDisplayed()));
        editText.perform(replaceText("new"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.AddListButton), withText("Add"), withContentDescription("Add"),
                        isDisplayed()));
        button.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.ListsListView)));
        linearLayout.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.AddItemEditText), withContentDescription("addItemEditText"),
                        isDisplayed()));
        editText2.perform(replaceText("item"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.AddItemButton), withText("Add"), withContentDescription("Add"),
                        isDisplayed()));
        button2.perform(click());

        DataInteraction linearLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.ItemsListView)));
        linearLayout2.perform(click());

        pressBack();

       openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        DataInteraction linearLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.ListsListView)));
        linearLayout3.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Delete All Inactive"),
                        isDisplayed()));
        textView.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(android.R.id.button1), withText("Yes")));
        button3.perform(scrollTo(), click());

        pressBack();
    }

}
