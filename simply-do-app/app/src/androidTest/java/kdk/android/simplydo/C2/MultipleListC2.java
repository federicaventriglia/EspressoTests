package kdk.android.simplydo.C2;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import kdk.android.simplydo.R;
import kdk.android.simplydo.SimplyDoActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MultipleListC2 {

    @Rule
    public ActivityTestRule<SimplyDoActivity> mActivityTestRule = new ActivityTestRule<>(SimplyDoActivity.class);

    @Test
    public void multipleListC2() {
        ViewInteraction editText = onView(
                allOf(ViewMatchers.withId(R.id.AddListEditText), withContentDescription("addlistedittext"), isDisplayed()));
        editText.perform(replaceText("one"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.AddListButton), withText("Add"), withContentDescription("Add"), isDisplayed()));
        button.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.AddListEditText), withContentDescription("addlistedittext"), isDisplayed()));
        editText2.perform(replaceText("two"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.AddListButton), withText("Add"), withContentDescription("Add"), isDisplayed()));
        button2.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withClassName(is("android.widget.LinearLayout")), isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.AddItemEditText), withContentDescription("addItemEditText"), isDisplayed()));
        editText3.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.AddItemButton), withText("Add"), withContentDescription("Add"), isDisplayed()));
        button3.perform(click());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.AddItemEditText), withContentDescription("addItemEditText"), isDisplayed()));
        editText4.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.AddItemButton), withText("Add"), withContentDescription("Add"), isDisplayed()));
        button4.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(withClassName(is("android.widget.LinearLayout")), isDisplayed()));
        linearLayout2.perform(click());

        pressBack();

    }
}
