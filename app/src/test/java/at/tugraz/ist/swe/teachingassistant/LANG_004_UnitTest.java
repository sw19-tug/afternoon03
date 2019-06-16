package at.tugraz.ist.swe.teachingassistant;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class LANG_004_UnitTest {

    @Mock
    Context mockContext;

    @Before
    public void setUp() {
        VocabularManager vocabulary = VocabularManager.getInstance();
        vocabulary.clear();

        Word word1 = new Word("first", "en");
        Word word2 = new Word("first_trans", "fi");
        Word word3 = new Word("second", "en");
        Word word4 = new Word("second_trans", "fi");
        Word word5 = new Word("third", "en");
        Word word6 = new Word("third_trans", "fi");
        vocabulary.addVocab(word1, word2,null);
        vocabulary.addVocab(word3, word4,null);
        vocabulary.addVocab(word5, word6,null);
    }

    @Test
    public void testSetRating(){
        VocabularManager vocabulary = VocabularManager.getInstance();
        Vector<Vocab> vocabs = vocabulary.getVocabs();

        ArrayList<String> testListBeforeRating = vocabulary.getWordsFromLanguageRatingString("fi", 1);
        assertEquals(testListBeforeRating.size(), 3);
        for (Vocab vocab : vocabs)
        {
            vocab.setRating(2);
        }
        ArrayList<String> testListChangedRating = vocabulary.getWordsFromLanguageRatingString("fi", 2);
        assertEquals(testListChangedRating.size(), 3);
    }

    @Test
    public void testSorting(){
        VocabularManager vocabulary = VocabularManager.getInstance();
        Vector<Vocab> vocabs = vocabulary.getVocabs();
        vocabs.get(0).setRating(0);
        vocabs.get(2).setRating(2);
        ArrayList<String> sortedList = vocabulary.getSortedWords("en", 0);
        ArrayList<String> compareTestList = new ArrayList<String>(Arrays.asList("third", "second", "first"));
        assertEquals(sortedList, compareTestList);
    }

    @Test
    public void testSorting2(){
        VocabularManager vocabulary = VocabularManager.getInstance();
        Vector<Vocab> vocabs = vocabulary.getVocabs();
        vocabs.get(0).setRating(2);
        vocabs.get(2).setRating(0);
        ArrayList<String> sortedList = vocabulary.getSortedWords("en", 1);
        ArrayList<String> compareTestList = new ArrayList<String>(Arrays.asList("third", "second", "first"));
        assertEquals(sortedList, compareTestList);
    }
}

//
