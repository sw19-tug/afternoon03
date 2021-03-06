package at.tugraz.ist.swe.teachingassistant;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.Intent.EXTRA_TITLE;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExportUI
{

    private String stringToBetyped;
    private String extension;
    @Rule
    public IntentsTestRule<ExportActivity> activityRule = new IntentsTestRule<>(ExportActivity.class);

    @Before
    public void initValidString() {
        stringToBetyped = "test";
        extension = activityRule.getActivity().getString(R.string.file_extension);
    }


    @Test
    public void checkExportInterface() {
        onView(withId(R.id.export_ok_btn)).check(matches(isClickable()));
        onView(withId(R.id.export_ok_btn)).check(matches(withText("Export")));
        onView(withId(R.id.export_cancel_btn)).check(matches(isClickable()));
        onView(withId(R.id.export_cancel_btn)).check(matches(withText("Cancel")));
        onView(withId(R.id.export_filename)).check(matches(isDisplayed()));
        onView(withId(R.id.export_filename)).check(matches(withHint("File Name")));
    }

    @Test
    public void checkInputTextField() {
        Intent resultData = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        resultData.setType(stringToBetyped + extension);
        resultData.putExtra(EXTRA_TITLE, stringToBetyped + extension);
        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        intending(not(isInternal())).respondWith(result);

        onView(withId(R.id.export_ok_btn)).perform(click());
        assertEquals(stringToBetyped+extension, result.getResultData().getType());
        assertTrue(activityRule.getActivity().isFinishing());
    }

    @Test
    public void checkCancelExport()
    {
        onView(withId(R.id.export_cancel_btn))
            .perform(click());
        assertTrue(activityRule.getActivity().isFinishing());
    }
}
