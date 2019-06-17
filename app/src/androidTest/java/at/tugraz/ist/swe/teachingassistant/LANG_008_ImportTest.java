package at.tugraz.ist.swe.teachingassistant;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
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
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static at.tugraz.ist.swe.teachingassistant.Globals.FILE_EXTENSION;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LANG_008_ImportTest
{

    private String stringToBetyped;
    private String extension;

    @Rule
    public IntentsTestRule<MainActivity> activityRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void initValidString() {
        stringToBetyped = "test";
        extension =  FILE_EXTENSION;
    }


    @Test
    public void checkImportInterface() {
        onView(withId(R.id.btn_import)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_import)).check(matches(isClickable()));
    }

    @Test
    public void checkImportButton()
    {
        Intent resultData = new Intent(activityRule.getActivity(), FileSelectActivity.class);
        resultData.setType(extension);
        Instrumentation.ActivityResult result =
            new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        intending(not(isInternal())).respondWith(result);

        onView(withId(R.id.btn_import)).perform(click());
        assertEquals(extension, result.getResultData().getType());
    }
}
