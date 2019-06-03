package me.tsukanov.counter.ui.C5;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
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

import me.tsukanov.counter.R;
import me.tsukanov.counter.ui.MainActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddRemoveCntC5 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addRemoveCntC5() {
        ViewInteraction appCompatImageButton = onView(
                allOf(
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                allOf(withId(R.id.action_bar_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.LinearLayout")),
                                                                                        1),
                                                                                0)),
                                                                1)),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.add_counter),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.menu_frame),
                                                childAtPosition(
                                                        allOf(withId(R.id.drawer_layout),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.decor_content_parent),
                                                                                        0)),
                                                                        0)),
                                                        1)),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edit_name),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(android.R.id.custom),
                                                        childAtPosition(
                                                                allOf(withClassName(is("android.widget.FrameLayout")),
                                                                        childAtPosition(
                                                                                withClassName(is("com.android.internal.widget.AlertDialogLayout")),
                                                                                2)),
                                                                0)),
                                                0),
                                        0),
                                1)));
        appCompatEditText.perform(scrollTo(), replaceText("One"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edit_value),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(android.R.id.custom),
                                                        childAtPosition(
                                                                allOf(withClassName(is("android.widget.FrameLayout")),
                                                                        childAtPosition(
                                                                                withClassName(is("com.android.internal.widget.AlertDialogLayout")),
                                                                                2)),
                                                                0)),
                                                0),
                                        0),
                                3)));
        appCompatEditText2.perform(scrollTo(), replaceText("0"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1),
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
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                allOf(withId(R.id.action_bar_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.LinearLayout")),
                                                                                        1),
                                                                                0)),
                                                                1)),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.add_counter),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.menu_frame),
                                                childAtPosition(
                                                        allOf(withId(R.id.drawer_layout),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.decor_content_parent),
                                                                                        0)),
                                                                        0)),
                                                        1)),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout2.perform(click());
        linearLayout2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.edit_name),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(android.R.id.custom),
                                                        childAtPosition(
                                                                allOf(withClassName(is("android.widget.FrameLayout")),
                                                                        childAtPosition(
                                                                                withClassName(is("com.android.internal.widget.AlertDialogLayout")),
                                                                                2)),
                                                                0)),
                                                0),
                                        0),
                                1)));
        appCompatEditText3.perform(scrollTo(), replaceText("Two"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.edit_value),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(android.R.id.custom),
                                                        childAtPosition(
                                                                allOf(withClassName(is("android.widget.FrameLayout")),
                                                                        childAtPosition(
                                                                                withClassName(is("com.android.internal.widget.AlertDialogLayout")),
                                                                                2)),
                                                                0)),
                                                0),
                                        0),
                                3)));
        appCompatEditText4.perform(scrollTo(), replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1),
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
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                allOf(withId(R.id.action_bar_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.LinearLayout")),
                                                                                        1),
                                                                                0)),
                                                                1)),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction linearLayout4 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.menu_frame),
                                                        childAtPosition(
                                                                allOf(withId(R.id.drawer_layout),
                                                                        childAtPosition(
                                                                                withId(android.R.id.content),
                                                                                0)),
                                                                1)),
                                                0),
                                        1)),
                        1),
                        isDisplayed()));
        linearLayout4.check(matches(isDisplayed()));

        ViewInteraction linearLayout5 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.menu_frame),
                                                        childAtPosition(
                                                                allOf(withId(R.id.drawer_layout),
                                                                        childAtPosition(
                                                                                withId(android.R.id.content),
                                                                                0)),
                                                                1)),
                                                0),
                                        1)),
                        2),
                        isDisplayed()));
        linearLayout5.check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.content),
                                                childAtPosition(
                                                        withParent(childAtPosition(
                                                                withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                                                                0)),
                                                        1)),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        DataInteraction linearLayout7 = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                allOf(withId(android.R.id.list_container),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(android.R.id.content),
                                                                childAtPosition(
                                                                        withId(R.id.decor_content_parent),
                                                                        0)),
                                                        1),
                                                0)),
                                0)))
                .atPosition(9);
        linearLayout7.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1),
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
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                allOf(withId(R.id.action_bar_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.LinearLayout")),
                                                                                        1),
                                                                                0)),
                                                                1)),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                allOf(withId(R.id.action_bar_container),
                                                        childAtPosition(
                                                                allOf(withId(R.id.decor_content_parent),
                                                                        childAtPosition(
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.LinearLayout")),
                                                                                        1),
                                                                                0)),
                                                                1)),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton5.perform(click());
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
