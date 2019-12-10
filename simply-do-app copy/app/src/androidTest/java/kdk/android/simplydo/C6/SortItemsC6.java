package kdk.android.simplydo.C6;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import kdk.android.simplydo.R;
import kdk.android.simplydo.SimplyDoActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SortItemsC6 {

    @Rule
    public ActivityTestRule<SimplyDoActivity> mActivityTestRule = new ActivityTestRule<>(SimplyDoActivity.class);

    @Test
    public void sortItemsC6() {
        ViewInteraction editText = onView(
                allOf(ViewMatchers.withId(R.id.AddListEditText), isDisplayed()));
        editText.perform(replaceText("List"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.AddListButton), withText("Add"), isDisplayed()));
        button.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withClassName(is("android.widget.LinearLayout")), isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.AddItemEditText), isDisplayed()));
        editText2.perform(replaceText("A"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.AddItemButton), withText("Add"), isDisplayed()));
        button2.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.AddItemEditText), isDisplayed()));
        editText3.perform(replaceText("C"), closeSoftKeyboard());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.AddItemButton), withText("Add"), isDisplayed()));
        button3.perform(click());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.AddItemEditText), isDisplayed()));
        editText4.perform(click());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.AddItemEditText), isDisplayed()));
        editText5.perform(replaceText("D"), closeSoftKeyboard());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.AddItemButton), withText("Add"), isDisplayed()));
        button4.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Sort Now"), isDisplayed()));
        textView.perform(click());
    }
}
