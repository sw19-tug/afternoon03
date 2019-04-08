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
public class LANG_008_ImportTest
{

    private String stringToBetyped;

    @Rule
    public ActivityTestRule<ImportActivity> activityRule = new ActivityTestRule<>(ImportActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "test";
    }

    private ImportActivity importActivity = null;


    @Before
    public void setup() {
        importActivity = activityRule.getActivity();
    }


    @Test
    public void checkImportInterface() {

        //button is clickable
        onView(withId(R.id.import_ok_btn)).check(matches(isClickable()));
        onView(withId(R.id.import_cancel_btn)).check(matches(isClickable()));
        // field is visible
        onView(withId(R.id.import_filename)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void checkInputTextField()
    {
        onView(withId(R.id.import_filename))
            .perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.import_ok_btn)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.import_filename))
            .check(matches(withText(stringToBetyped)));
    }

    @Test
    public void checkCancelImport()
    {
        onView(withId(R.id.import_cancel_btn))
            .perform(click());

        onView(withText(R.string.TOAST_IMPORT_CANCEL)).inRoot(withDecorView(not(is(importActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void checkImportButton()
    {
        onView(withId(R.id.import_ok_btn))
            .perform(click());
        //
        onView(withText(R.string.TOAST_IMPORT_SUCCESS)).inRoot(withDecorView(not(is(importActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
