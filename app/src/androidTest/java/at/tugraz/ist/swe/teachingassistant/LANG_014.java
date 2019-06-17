package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Timer;
import java.util.TimerTask;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertEquals;

@RunWith(AndroidJUnit4.class)
public class LANG_014 {

    @Rule
    public ActivityTestRule<LeaderboardActivity> activity = new ActivityTestRule<LeaderboardActivity>(LeaderboardActivity.class, false, false);

    @Test
    public void checkListCount() throws Exception {
        DatabaseHelper db = new DatabaseHelper(InstrumentationRegistry.getTargetContext());
        int totalCount = db.getTotalCount();
        Intent intent = new Intent();
        activity.launchActivity(intent);
        ListView listview = (ListView) activity.getActivity().findViewById(R.id.leaderboardList);
        int listCount = listview.getAdapter().getCount();
        assertEquals(listCount, totalCount);
    }
}