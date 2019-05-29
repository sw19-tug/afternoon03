package at.tugraz.ist.swe.teachingassistant;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.*;

import static org.hamcrest.Matchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class LANG_005 {
    @Rule
    public ActivityTestRule<TestingActivity> activityRule = new ActivityTestRule<>(TestingActivity.class);

    public ActivityTestRule<TestingActivity> getActivityRule()
    {
        return activityRule;
    }

    @Test
    public void setupTest() throws Exception {

    }

    @Test
    public void correctResponseTest() throws Exception {

    }

    @Test
    public void incorrectResponseTest() throws Exception {

    }

    @Test
    public void endingTest() throws Exception {

    }


}
