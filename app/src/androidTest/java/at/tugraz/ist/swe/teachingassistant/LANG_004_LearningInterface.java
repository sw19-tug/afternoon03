package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Vector;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LANG_004_LearningInterface {

    @Rule
    public ActivityTestRule<LearningInterfaceActivity> mActivityRule = new ActivityTestRule<LearningInterfaceActivity>(LearningInterfaceActivity.class,false,false);

    @Before
    public void setUpList() {

        VocabularManager vocabulary = VocabularManager.getInstance();
        Word word1 = new Word("first", "en");
        Word word2 = new Word("first_trans", "fi");
        Word word3 = new Word("second", "en");
        Word word4 = new Word("second_trans", "fi");
        Word word5 = new Word("third", "en");
        Word word6 = new Word("third_trans", "fi");
        vocabulary.addVocab(word1, word2,null);
        vocabulary.addVocab(word3, word4,null);
        vocabulary.addVocab(word5, word6,null);

        vocabulary.getWordsFromLanguageRatingString("en",1);

        Intent intent = new Intent();
        intent.putExtra("current_lang", "en");
        intent.putExtra("position", 0);

        mActivityRule.launchActivity(intent);

    }

    @Test
    public void testSeekbarVisible()
    {
        onView(withId(R.id.seekBar)).check(matches(isDisplayed()));
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
        assertEquals(1, rating);
    }

    @Test
    public void testChangeLanguage(){
        onView(withId(R.id.currentLanguage)).check(matches(isDisplayed()));
        String language = withId(R.id.currentLanguage).toString();
        onView(withId(R.id.btn_changeLanguageInterface)).perform(ViewActions.click());
        String langAfterClick = withId(R.id.currentLanguage).toString();
        assertNotSame(language, langAfterClick);
    }

    @Test
    public void testNextButton(){
        onView(withId(R.id.btn_next)).check(matches(isDisplayed()));
        String word = withId(R.id.currentWord).toString();
        onView(withId(R.id.btn_next)).perform(ViewActions.click());
        String wordAfterClick = withId(R.id.currentWord).toString();
        assertNotSame(word, wordAfterClick);
    }

    @Test
    public void testPrevButton(){
        onView(withId(R.id.btn_prev)).check(matches(isDisplayed()));
        String word = withId(R.id.currentWord).toString();
        onView(withId(R.id.btn_prev)).perform(ViewActions.click());
        String wordAfterClick = withId(R.id.currentWord).toString();
        assertNotSame(word, wordAfterClick);
    }



}
