package at.tugraz.ist.swe.teachingassistant;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LANG_008_ExportDialogTest
{

    private String stringToBetyped;

    @Rule
    public ActivityTestRule<ExportDialog> activityRule = new ActivityTestRule<>(ExportDialog.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "test.txt";
    }

    private ExportDialog exportDialog = null;


    @Before
    public void setup() {
        exportDialog = activityRule.getActivity();
    }


    @Test
    public void changeText_sameActivity() {

        //button is clickable
        onView(withId(R.id.export_ok_btn)).check(matches(isClickable()));
        onView(withId(R.id.export_cancel_btn)).check(matches(isClickable()));

        onView(withId(R.id.export_filename))
            .perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.export_ok_btn)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.export_filename))
            .check(matches(withText(stringToBetyped)));
    }
}
