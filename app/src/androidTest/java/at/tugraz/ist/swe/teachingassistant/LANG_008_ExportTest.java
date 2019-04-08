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
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LANG_008_ExportTest
{

    private String stringToBetyped;

    @Rule
    public ActivityTestRule<ExportActivity> activityRule = new ActivityTestRule<>(ExportActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "test.txt";
    }

    private ExportActivity exportActivity = null;


    @Before
    public void setup() {
        exportActivity = activityRule.getActivity();
    }


    @Test
    public void checkExportInterface() {

        //button is clickable
        onView(withId(R.id.export_ok_btn)).check(matches(isClickable()));
        onView(withId(R.id.export_cancel_btn)).check(matches(isClickable()));
        onView(withId(R.id.export_filename)).check(matches(isDisplayed()));

    }

    @Test
    public void checkInputTextField()
    {
        onView(withId(R.id.export_filename))
            .perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.export_ok_btn)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.export_filename))
            .check(matches(withText(stringToBetyped)));

        onView(withText(R.string.TOAST_EXPORT_SUCCESS)).inRoot(withDecorView(not(is(exportActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));


    }

    @Test
    public void checkCancelImport()
    {
        onView(withId(R.id.import_cancel_btn))
            .perform(click());

        onView(withText(R.string.TOAST_EXPORT_CANCEL)).inRoot(withDecorView(not(is(exportActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
