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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        onView(withId(R.id.share_button)).check(matches(isDisplayed()));

        Intent resultData = new Intent(Intent.ACTION_SEND);
        resultData.setType(extension);
        Instrumentation.ActivityResult result =
            new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        intending(not(isInternal())).respondWith(result);

        onView(withId(R.id.share_button)).perform(click());
        assertEquals(extension, result.getResultData().getType());
        assertTrue(activityRule.getActivity().isFinishing());
    }
}
