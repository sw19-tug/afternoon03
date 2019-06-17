package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class LANG_015_test_time {
    public int seconds = 0;

    @Rule
    public ActivityTestRule<TestingActivity> activity = new ActivityTestRule<TestingActivity>(TestingActivity.class, false, false);

    @Test
    public void checkCorrectTime() throws Exception {
        Timer timer = new Timer();
        int seconds = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runTimer();
            }
        }, 0, 1000);
        sleep(4000);
        timer.cancel();
        assert (seconds < 3);
    }

    private void runTimer() {
        seconds++;
    }
}




