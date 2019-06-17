package at.tugraz.ist.swe.teachingassistant;

import android.support.test.runner.AndroidJUnit4;


import android.util.Log;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class LANG_001 {

    @Test
    public void addVocabCorrect() throws Exception
    {
        VocabularManager vocabulary = VocabularManager.getInstance();
        Word word1 = new Word("en_test", "en");
        Word word2 = new Word("fi_test", "fi");
        vocabulary.addVocab(word1, word2, null);

        assertTrue(vocabulary.getWordsFromLanguage("en").contains("en_test"));
        assertTrue(vocabulary.getWordsFromLanguage("fi").contains("fi_test"));
        vocabulary.clear();
    }

    @Test
    public void addVocabIncorrect() throws Exception
    {
        VocabularManager vocabulary = VocabularManager.getInstance();
        Word word1 = new Word("en_test", "en");
        Word word2 = new Word("fi_te", "fi");
        vocabulary.addVocab(word1, word2, null);

        assertTrue(vocabulary.getWordsFromLanguage("en").contains("en_test"));
        assertFalse(vocabulary.getWordsFromLanguage("fi").contains("fi_test"));
        vocabulary.clear();
    }
    @Test
    public void addVocabWrongLanguageCode() throws Exception
    {
        VocabularManager vocabulary = VocabularManager.getInstance();
        Word word1 = new Word("en_test", "en");
        Word word2 = new Word("fi_test", "fi");
        vocabulary.addVocab(word1, word2, null);

        assertFalse(vocabulary.getWordsFromLanguage("f").contains("fi_test"));
        assertFalse(vocabulary.getWordsFromLanguage("e").contains("en_test"));
        vocabulary.clear();
    }

    @After
    public  void clearVocab()
    {
        VocabularManager vocabulary = VocabularManager.getInstance();
        vocabulary.clearVocabs();
    }

}



