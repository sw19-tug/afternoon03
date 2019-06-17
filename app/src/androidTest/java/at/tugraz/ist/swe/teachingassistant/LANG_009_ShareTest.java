package at.tugraz.ist.swe.teachingassistant;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;

import static at.tugraz.ist.swe.teachingassistant.Globals.SHARE_CODE;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LANG_009_ShareTest
{

    private String extension = ".tast";

    @Rule
    public IntentsTestRule<MainActivity> activityRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void checkShareInterface()
    {
        onView(withId(R.id.share_button)).check(matches(isDisplayed()));
        onView(withId(R.id.share_button)).check(matches(isClickable()));
    }


    @Test
    public void checkShareActionClick()
    {
        Intent resultData = new Intent(activityRule.getActivity(), FileSelectActivity.class);
        resultData.setType(extension);
        Instrumentation.ActivityResult result =
            new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        intending(not(isInternal())).respondWith(result);

        onView(withId(R.id.share_button)).perform(click());
        assertEquals(result.getResultCode(), Activity.RESULT_OK);
        assertEquals(extension, result.getResultData().getType());
    }

}
