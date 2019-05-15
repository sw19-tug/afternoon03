package at.tugraz.ist.swe.teachingassistant;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Vector;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LANG_004_Test {



    @Rule
    public ActivityTestRule<LearningInterfaceActivity> mActivityRule = new ActivityTestRule<LearningInterfaceActivity>(LearningInterfaceActivity.class,false,false);

    @Before
    public void setUpList() {
        //There seems to be an error
        VocabularManager vocabulary = VocabularManager.getInstance();
        Word word1 = new Word("hello", "en");
        Word word2 = new Word("shppp", "fi");
        vocabulary.addVocab(word1, word2);

        Intent intent = new Intent();
        intent.putExtra("current_lang", "en");
        intent.putExtra("position", 0);

        mActivityRule.launchActivity(intent);

    }

    @Test
    public void testSeekbarVisible()
    {
        onView(withId(R.id.currentLanguage)).check(matches(isDisplayed()));
    }
    @Test
    public void testSeekbarMoving()
    {
        onView(withId(R.id.seekBar)).perform(ViewActions.swipeRight());
        onView(withId(R.id.seekBar)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.seekBar)).perform(ViewActions.click());
    }
    @Test
    public void testChangeRating()
    {
        onView(withId(R.id.seekBar)).perform(ViewActions.swipeRight());
        VocabularManager vocabulary = VocabularManager.getInstance();
        Vector<Vocab> vocabs = vocabulary.getVocabs();
        int rating = vocabs.get(0).getRating();
        assertEquals(4, rating);
    }


}
