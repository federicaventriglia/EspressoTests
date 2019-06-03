package me.tsukanov.counter.ui.C2;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.tsukanov.counter.R;
import me.tsukanov.counter.ui.MainActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddRemoveCntC2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addRemoveCntC2() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation"), isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.add_counter), isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.edit_name));
        appCompatEditText.perform(scrollTo(), replaceText("One"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.edit_value));
        appCompatEditText2.perform(scrollTo(), replaceText("0"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                withId(android.R.id.button1));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation"), isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.add_counter), isDisplayed()));
        linearLayout2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.edit_name));
        appCompatEditText3.perform(scrollTo(), replaceText("Two"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                withId(R.id.edit_value));
        appCompatEditText4.perform(scrollTo(), replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                withId(android.R.id.button1));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Open navigation"), isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction linearLayout3 = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class), isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

        ViewInteraction linearLayout4 = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class), isDisplayed()));
        linearLayout4.check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction linearLayout5 = onView(
                allOf(withClassName(is("android.widget.LinearLayout")), isDisplayed()));
        linearLayout5.perform(click());

        ViewInteraction appCompatButton3 = onView(
                withId(android.R.id.button1));
        appCompatButton3.perform(scrollTo(), click());

        pressBack();

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withContentDescription("Open navigation"), isDisplayed()));
        appCompatImageButton4.perform(click());
    }
}
