package org.billthefarmer.editor.C5;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.billthefarmer.editor.Editor;
import org.billthefarmer.editor.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
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
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewTxtC5 {

    @Rule
    public ActivityTestRule<Editor> mActivityTestRule = new ActivityTestRule<>(Editor.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void newTxtC5() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction actionMenuItemView = onView(
                Matchers.allOf(ViewMatchers.withId(R.id.edit),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withClassName(is("android.widget.Toolbar")),
                                                childAtPosition(
                                                        allOf(withClassName(is("com.android.internal.widget.ActionBarContainer")),
                                                                childAtPosition(
                                                                        withClassName(is("com.android.internal.widget.ActionBarOverlayLayout")),
                                                                        1)),
                                                        0)),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.text), withText("text\nnew\n he\n"),
                        childAtPosition(
                                allOf(withId(R.id.hscroll),
                                        childAtPosition(
                                                allOf(withId(R.id.vscroll),
                                                        childAtPosition(
                                                                allOf(withId(android.R.id.content),
                                                                        childAtPosition(
                                                                                withClassName(is("com.android.internal.widget.ActionBarOverlayLayout")),
                                                                                0)),
                                                                0)),
                                                0)),
                                0)));
        editText.perform(scrollTo(), click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.text), withText("text\nnew\n he\n"),
                        childAtPosition(
                                allOf(withId(R.id.hscroll),
                                        childAtPosition(
                                                allOf(withId(R.id.vscroll),
                                                        childAtPosition(
                                                                allOf(withId(android.R.id.content),
                                                                        childAtPosition(
                                                                                withClassName(is("com.android.internal.widget.ActionBarOverlayLayout")),
                                                                                0)),
                                                                0)),
                                                0)),
                                0)));
        editText2.perform(scrollTo(), replaceText("blank"));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.text), withText("blank"),
                        childAtPosition(
                                allOf(withId(R.id.hscroll),
                                        childAtPosition(
                                                allOf(withId(R.id.vscroll),
                                                        childAtPosition(
                                                                allOf(withId(android.R.id.content),
                                                                        childAtPosition(
                                                                                withClassName(is("com.android.internal.widget.ActionBarOverlayLayout")),
                                                                                0)),
                                                                0)),
                                                0)),
                                0),
                        isDisplayed()));
        editText3.perform(closeSoftKeyboard());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.save),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withClassName(is("android.widget.Toolbar")),
                                                childAtPosition(
                                                        allOf(withClassName(is("com.android.internal.widget.ActionBarContainer")),
                                                                childAtPosition(
                                                                        withClassName(is("com.android.internal.widget.ActionBarOverlayLayout")),
                                                                        1)),
                                                        0)),
                                        1),
                                1),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.text), withText("blank"),
                        childAtPosition(
                                allOf(withId(R.id.hscroll),
                                        childAtPosition(
                                                allOf(withId(R.id.vscroll),
                                                        childAtPosition(
                                                                allOf(withId(android.R.id.content),
                                                                        childAtPosition(
                                                                                allOf(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                                                        childAtPosition(
                                                                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                                                                0)),
                                                                                1)),
                                                                0)),
                                                0)),
                                0),
                        isDisplayed()));
        editText4.check(matches(withText("blank")));
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
