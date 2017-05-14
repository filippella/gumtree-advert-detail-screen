package com.gumtree.mobile.android.app.activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.gumtree.mobile.android.app.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AdvertDetailActivityTest {

    @Rule
    public ActivityTestRule<AdvertDetailActivity> mActivityTestRule = new ActivityTestRule<>(AdvertDetailActivity.class);

    @Test
    public void advertDetailActivityTest() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.actionShare), withContentDescription("Share"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerView), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.actionCall), withText("CALL"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.actionSMS), withText("SMS"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.actionEmail), withText("MESSAGE"), isDisplayed()));
        appCompatButton3.perform(click());

        pressBack();

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.advertHistory), withText("Check This car's History"), isDisplayed()));
        appCompatButton4.perform(click());

        pressBack();

    }

}
