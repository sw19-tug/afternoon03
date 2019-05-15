package at.tugraz.ist.swe.teachingassistant;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Intent.EXTRA_TITLE;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LANG_008_ImportTest
{

    private String stringToBetyped;
    private String extension;

    @Rule
    public IntentsTestRule<ImportActivity> activityRule = new IntentsTestRule<>(ImportActivity.class);

    @Before
    public void initValidString() {
        stringToBetyped = "test";
        extension =  activityRule.getActivity().getString(R.string.file_extension);
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
    public void checkImportButton()
    {
        Intent resultData = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        resultData.setType(extension);
        Instrumentation.ActivityResult result =
            new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        intending(toPackage(Intent.ACTION_OPEN_DOCUMENT)).respondWith(result);

        onView(withId(R.id.import_ok_btn)).perform(click());
        assertEquals(extension, result.getResultData().getType());
        assertTrue(activityRule.getActivity().isFinishing());
    }

    @Test
    public void checkCancelImport()
    {
        onView(withId(R.id.import_cancel_btn))
            .perform(click());
        assertTrue(activityRule.getActivity().isFinishing());
    }


}
