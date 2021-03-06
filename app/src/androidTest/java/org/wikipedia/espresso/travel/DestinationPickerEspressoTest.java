package org.wikipedia.espresso.travel;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.wikipedia.R;
import org.wikipedia.travel.TravelPlannerActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by amawai on 13/03/18.
 */

@SdkSuppress(minSdkVersion = 18)
public class DestinationPickerEspressoTest {
    private UiDevice mDevice;

    @Rule
    public ActivityTestRule<TravelPlannerActivity> dActivityTestRule = new ActivityTestRule<>(TravelPlannerActivity.class);

    @Before
    public void setUp() {
        //Allows testing of components outside of testing context, in this case: Google Android Places Autocomplete
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        //Navigate to the Destinations fragment
        ViewInteraction planNewTripButton = onView(
                allOf(withId(R.id.trip_button_new)));
        planNewTripButton.perform(click());
    }

    @Test
    public void allUiComponentsShouldExist() {
        //Ensures that all relevant components are rendered
        ViewInteraction destination = onView(
                allOf(withId(R.id.planner_title)));
        destination.check(matches(withText(R.string.fragment_travel_destination_title)));

        ViewInteraction nextButton = onView(
                allOf(withId(R.id.planner_next)));
        nextButton.check(matches(isDisplayed()));

        ViewInteraction searchInput = onView(
                allOf(withId(R.id.place_autocomplete_search_input),
                        isDisplayed()));
        searchInput.check(matches(isDisplayed()));
    }

    @Test
    public void selectedDestinationShouldAppearOnScreen() throws UiObjectNotFoundException {
        ViewInteraction destinationInput = onView(withId(R.id.fragment_place_autocomplete));

        destinationInput.perform(click());

        UiObject searchBar = mDevice.findObject(new UiSelector()
                .className("android.widget.EditText"));

        try {
            searchBar.setText("Montreal");
            closeSoftKeyboard();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        //Select one of the options from the dropdpown
        mDevice.findObject(new UiSelector()
                .text("QC, Canada")).click();

        ViewInteraction editText = onView(
                allOf(withId(R.id.place_autocomplete_search_input),
                        isDisplayed()));

        //Check that the destination selected from the dropdown appears
        editText.check(matches(withText("Montreal")));
    }
}
