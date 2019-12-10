package com.knirirr.beecount.C6;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.knirirr.beecount.R;
import com.knirirr.beecount.WelcomeActivity;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
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
public class NewListCheckC6 {

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    @Test
    public void newListCheckC6() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(ViewMatchers.withId(R.id.newProjectButton), withText("New Project")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.newprojName), isDisplayed()));
        appCompatEditText.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.addNewCountButton), withText("Add Count"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.addNewCountButton), withText("Add Count"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.newprojSaveButton), withText("Save Project"), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction newCount3 = onView(
                withClassName(is("com.knirirr.beecount.NewCount")));
        newCount3.perform(scrollTo(), replaceText("2"), closeSoftKeyboard());

        ViewInteraction newCount4 = onView(
                withClassName(is("com.knirirr.beecount.NewCount")));
        newCount4.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.newprojSaveButton), withText("Save Project"), isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class), isDisplayed()));
        linearLayout.check(matches(isDisplayed()));
    }
}
