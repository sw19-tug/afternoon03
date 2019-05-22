package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotSame;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LANG_004_Test2 {
    @Rule
    public ActivityTestRule<LearningListActivity> mActivityRule = new ActivityTestRule<LearningListActivity>(LearningListActivity.class,false,false);

    @Before
    public void setUp() {
        VocabularManager vocabulary = VocabularManager.getInstance();
        Word word1 = new Word("first", "en");
        Word word2 = new Word("first_trans", "fi");
        Word word3 = new Word("second", "en");
        Word word4 = new Word("second_trans", "fi");
        Word word5 = new Word("third", "en");
        Word word6 = new Word("third_trans", "fi");
        vocabulary.addVocab(word1, word2);
        vocabulary.addVocab(word3, word4);
        vocabulary.addVocab(word5, word6);

        Intent intent = new Intent();
        intent.putExtra("current_lang", "en");
        intent.putExtra("position", 0);

        mActivityRule.launchActivity(intent);
    }

    @Test
    public void testFilterSpinner()
    {
        onView(withId(R.id.spinner)).check(matches(isDisplayed()));
        onView(withId(R.id.spinner)).check(matches((isClickable())));
    }

    @Test
    public void testSortSpinner()
    {
        onView(withId(R.id.spinnerSort)).check(matches(isDisplayed()));
        onView(withId(R.id.spinnerSort)).check(matches((isClickable())));
    }

    @Test
    public void testChangeLanguage()
    {
        onView(withId(R.id.btn_changeLanguage)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_changeLanguage)).check(matches((isClickable())));
        String language = withId(R.id.languageTitle).toString();
        onView(withId(R.id.btn_changeLanguage)).perform(ViewActions.click());
        String languageAfterClick = withId(R.id.languageTitle).toString();
        assertNotSame(language, languageAfterClick);
    }

    @Test
    public void testListHasItems() {
        ListView listview = (ListView) mActivityRule.getActivity().findViewById(R.id.vocabList);
        assertThat(listview.getCount(), is(3));
    }

}