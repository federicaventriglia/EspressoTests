package org.liberty.android.fantastischmemo.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.liberty.android.fantastischmemo.R;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PlayAnyMemoTest {

    @Rule
    public ActivityTestRule<AnyMemo> mActivityTestRule = new ActivityTestRule<>(AnyMemo.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void playAnyMemoTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction frameLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recent_open_list),
                                childAtPosition(
                                        withId(R.id.page_fragment_container),
                                        0)),
                        0),
                        isDisplayed()));
        frameLayout.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.card_text_view), withText("?\nMostra la risposta"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.root),
                                        0),
                                0)));
        appCompatTextView.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.grade_button_0), withText("Ancora\n0.0 min"),
                        childAtPosition(
                                allOf(withId(R.id.grade_buttons_anki),
                                        childAtPosition(
                                                withId(R.id.buttons_root),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.root),
                        withParent(allOf(withId(R.id.field2),
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2))),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.grade_button_0), withText("Ancora\n0.0 min"),
                        childAtPosition(
                                allOf(withId(R.id.grade_buttons_anki),
                                        childAtPosition(
                                                withId(R.id.buttons_root),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.root),
                        withParent(allOf(withId(R.id.field2),
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2))),
                        isDisplayed()));
        linearLayout2.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.grade_button_2), withText("Difficile\n1.0 giorno"),
                        childAtPosition(
                                allOf(withId(R.id.grade_buttons_anki),
                                        childAtPosition(
                                                withId(R.id.buttons_root),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.card_text_view), withText("?\nMostra la risposta"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.root),
                                        0),
                                0)));
        appCompatTextView2.perform(scrollTo(), click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.grade_button_5), withText("Facile\n5.0 giorno"),
                        childAtPosition(
                                allOf(withId(R.id.grade_buttons_anki),
                                        childAtPosition(
                                                withId(R.id.buttons_root),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.card_text_view), withText("?\nMostra la risposta"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.root),
                                        0),
                                0)));
        appCompatTextView3.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Vai in alto"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());
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
