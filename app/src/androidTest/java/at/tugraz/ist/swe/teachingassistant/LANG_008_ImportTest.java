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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LANG_008_ImportTest
{

    private String stringToBetyped;

    @Rule
    public ActivityTestRule<ImportActivity> activityRule = new ActivityTestRule<>(ImportActivity.class);

    @Before
    public void initValidString() {
        stringToBetyped = "test";
    }

    private ImportActivity importActivity = null;


    @Before
    public void setup() {
        importActivity = activityRule.getActivity();
    }

    @Test
    public void checkImportInterface() {
        onView(withId(R.id.import_ok_btn)).check(matches(isClickable()));
        onView(withId(R.id.import_ok_btn)).check(matches(withText("Import")));
        onView(withId(R.id.import_cancel_btn)).check(matches(isClickable()));
        onView(withId(R.id.import_cancel_btn)).check(matches(withText("Cancel")));
    }


    @Test
    public void checkCancelImport()
    {
        onView(withId(R.id.import_cancel_btn))
            .perform(click());
    }

    @Test
    public void checkImportButton()
    {
        onView(withId(R.id.import_ok_btn))
            .perform(click());
    }
}
