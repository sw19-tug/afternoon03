package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class LANG_015_test_time {
    @Rule
    public ActivityTestRule<TestingActivity> activity = new ActivityTestRule<TestingActivity>(TestingActivity.class, false, false);

    @Test
    public void checkCorrectTime() throws Exception {
        Intent intent = new Intent();
        activity.launchActivity(intent);
        wait(2000);
        activity.getActivity().stopTimer();
        long seconds = activity.getActivity().seconds;
        assert (seconds < 3);
    }
}




