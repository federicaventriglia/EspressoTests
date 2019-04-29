package org.catrobat.catroid.ui;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.catrobat.catroid.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddTextFieldProject {

    @Rule
    public ActivityTestRule<MainMenuActivity> mActivityTestRule = new ActivityTestRule<>(MainMenuActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE");

    @Test
    public void addTextFieldProject() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.accept), withText("Acconsento"),
                        childAtPosition(
                                allOf(withId(R.id.bottom_bar),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(android.R.id.content),
                                                                childAtPosition(
                                                                        allOf(withId(R.id.action_bar_root),
                                                                                childAtPosition(
                                                                                        withClassName(is("android.widget.FrameLayout")),
                                                                                        0)),
                                                                        0)),
                                                        0),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.fragment_container),
                                                childAtPosition(
                                                        childAtPosition(
                                                                withId(android.R.id.content),
                                                                0),
                                                        2)),
                                        0),
                                2)));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.fragment_container),
                                                childAtPosition(
                                                        childAtPosition(
                                                                withClassName(is("android.widget.LinearLayout")),
                                                                1),
                                                        1)),
                                        0),
                                2)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.fragment_container),
                                                childAtPosition(
                                                        childAtPosition(
                                                                withClassName(is("android.widget.LinearLayout")),
                                                                1),
                                                        1)),
                                        0),
                                2)));
        recyclerView3.perform(actionOnItemAtPosition(0, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(android.R.id.content),
                                                        childAtPosition(
                                                                withId(R.id.action_bar_root),
                                                                0)),
                                                0),
                                        1),
                                0)));
        recyclerView4.perform(actionOnItemAtPosition(1, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView5 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.fragment_container),
                                                childAtPosition(
                                                        childAtPosition(
                                                                withClassName(is("android.widget.LinearLayout")),
                                                                1),
                                                        1)),
                                        0),
                                2)));
        recyclerView5.perform(actionOnItemAtPosition(0, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.pocketpaint_tools_eraser),
                        childAtPosition(
                                allOf(withId(R.id.pocketpaint_tools_layout),
                                        childAtPosition(
                                                allOf(withId(R.id.pocketpaint_bottom_bar_scroll_view),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        allOf(withId(R.id.pocketpaint_main_bottom_bar),
                                                                                childAtPosition(
                                                                                        withId(R.id.pocketpaint_main_layout),
                                                                                        2)),
                                                                        0),
                                                                0)),
                                                0)),
                                1),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.pocketpaint_tools_pipette),
                        childAtPosition(
                                allOf(withId(R.id.pocketpaint_tools_layout),
                                        childAtPosition(
                                                allOf(withId(R.id.pocketpaint_bottom_bar_scroll_view),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        allOf(withId(R.id.pocketpaint_main_bottom_bar),
                                                                                childAtPosition(
                                                                                        withId(R.id.pocketpaint_main_layout),
                                                                                        3)),
                                                                        0),
                                                                0)),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.pocketpaint_tools_text),
                        childAtPosition(
                                allOf(withId(R.id.pocketpaint_tools_layout),
                                        childAtPosition(
                                                allOf(withId(R.id.pocketpaint_bottom_bar_scroll_view),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        allOf(withId(R.id.pocketpaint_main_bottom_bar),
                                                                                childAtPosition(
                                                                                        withId(R.id.pocketpaint_main_layout),
                                                                                        3)),
                                                                        0),
                                                                0)),
                                                0)),
                                7),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.pocketpaint_text_tool_dialog_input_text),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.pocketpaint_layout_tool_specific_options),
                                                        childAtPosition(
                                                                allOf(withId(R.id.pocketpaint_layout_tool_options),
                                                                        childAtPosition(
                                                                                withId(R.id.pocketpaint_main_tool_options),
                                                                                0)),
                                                                1)),
                                                0),
                                        0),
                                6)));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.pocketpaint_text_tool_dialog_input_text),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.pocketpaint_layout_tool_specific_options),
                                                        childAtPosition(
                                                                allOf(withId(R.id.pocketpaint_layout_tool_options),
                                                                        childAtPosition(
                                                                                withId(R.id.pocketpaint_main_tool_options),
                                                                                0)),
                                                                1)),
                                                0),
                                        0),
                                6)));
        appCompatEditText2.perform(scrollTo(), replaceText("hi\nv"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.pocketpaint_text_tool_dialog_input_text), withText("hi\nv"),
                        childAtPosition(
                                childAtPosition(
                                        childAtPosition(
                                                allOf(withId(R.id.pocketpaint_layout_tool_specific_options),
                                                        childAtPosition(
                                                                allOf(withId(R.id.pocketpaint_layout_tool_options),
                                                                        childAtPosition(
                                                                                withId(R.id.pocketpaint_main_tool_options),
                                                                                0)),
                                                                1)),
                                                0),
                                        0),
                                6)));
        appCompatEditText3.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.pocketpaint_tools_text),
                        childAtPosition(
                                allOf(withId(R.id.pocketpaint_tools_layout),
                                        childAtPosition(
                                                allOf(withId(R.id.pocketpaint_bottom_bar_scroll_view),
                                                        childAtPosition(
                                                                childAtPosition(
                                                                        allOf(withId(R.id.pocketpaint_main_bottom_bar),
                                                                                childAtPosition(
                                                                                        withId(R.id.pocketpaint_main_layout),
                                                                                        3)),
                                                                        0),
                                                                0)),
                                                0)),
                                7),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(56);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withContentDescription("Apri il menù di navigazione"),
                        childAtPosition(
                                allOf(withId(R.id.pocketpaint_toolbar),
                                        childAtPosition(
                                                allOf(withId(R.id.pocketpaint_layout_top_bar),
                                                        childAtPosition(
                                                                allOf(withId(R.id.pocketpaint_main_layout),
                                                                        childAtPosition(
                                                                                allOf(withId(R.id.pocketpaint_drawer_layout),
                                                                                        childAtPosition(
                                                                                                withId(android.R.id.content),
                                                                                                0)),
                                                                                0)),
                                                                0)),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        allOf(withId(R.id.pocketpaint_nav_view),
                                                childAtPosition(
                                                        allOf(withId(R.id.pocketpaint_drawer_layout),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.action_bar_root),
                                                                                        1)),
                                                                        0)),
                                                        1)),
                                        0)),
                        1),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("Salva"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.buttonPanel),
                                                childAtPosition(
                                                        allOf(withId(R.id.parentPanel),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        withId(R.id.action_bar_root),
                                                                                        0)),
                                                                        0)),
                                                        3)),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());
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
