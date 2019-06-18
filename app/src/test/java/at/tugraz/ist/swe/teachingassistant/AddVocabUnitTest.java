package at.tugraz.ist.swe.teachingassistant;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class AddVocabUnitTest {

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



