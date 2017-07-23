package com.example.pccc.utillityapp;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<ListMakerHome> rule = new ActivityTestRule<>(ListMakerHome.class);


    @Test
    public void appTest() throws Exception {
        onView(withId(R.id.itemInput)).perform(typeText("eggs"));
        onView(withId(R.id.addItemButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(withText("eggs")));
        onView(withId(R.id.listSizeNotice)).check(matches(withText("You have 1 item(s) in your list")));

        onView(withId(R.id.itemInput)).perform(typeText("bread"));
        onView(withId(R.id.addItemButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).check(matches(withText("bread")));
        onView(withId(R.id.listSizeNotice)).check(matches(withText("You have 2 item(s) in your list")));

        onView(withId(R.id.itemInput)).perform(typeText("bread" +
                ""));
        onView(withId(R.id.addItemButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).check(matches(withText("bread")));
        onView(withId(R.id.listSizeNotice)).check(matches(withText("You have 2 item(s) in your list")));

        onView(withId(R.id.itemInput)).perform(typeText("bread"));
        onView(withId(R.id.addItemButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).check(matches(withText("bread")));
        onView(withId(R.id.listSizeNotice)).check(matches(withText("You have 2 item(s) in your list")));

        onView(withId(R.id.itemInput)).perform(typeText("apples"));
        onView(withId(R.id.addItemButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(2).check(matches(withText("apples")));
        onView(withId(R.id.listSizeNotice)).check(matches(withText("You have 3 item(s) in your list")));

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.listSizeNotice)).check(matches(withText("You have 2 item(s) in your list")));

        onView(withId(R.id.action_settings)).perform(click());
        onView(withId(R.id.sortListButton)).perform(click());
        onView(withId(R.id.backButton)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(withText("apples")));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(1).check(matches(withText("bread")));

        onView(withId(R.id.action_settings)).perform(click());
        onView(withId(R.id.clearButton)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.listSizeNotice)).check(matches(withText("You have 0 item(s) in your list")));

    }
}
