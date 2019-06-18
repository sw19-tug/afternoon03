package at.tugraz.ist.swe.teachingassistant;

import android.content.Context;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Vector;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class Vocab_Test {


    Vector<Vocab> vocabs = new Vector<>();

    @Mock
    Context mockContext;

    @Before
    public void setUp() {
        Word word1 = new Word("first", "en");
        Word word2 = new Word("first_trans", "fi");
        Word word3 = new Word("second", "en");
        Word word4 = new Word("second_trans", "fi");
        Word word5 = new Word("third", "en");
        Word word6 = new Word("third_trans", "fi");

        Vocab vocab1 = new Vocab();
        vocab1.setTags("A");
        Vector<Word> words = new Vector<Word>();
        words.add(word1);
        words.add(word2);
        vocab1.setTranslation_table(words);
        vocabs.add(vocab1);
        Vocab vocab2 = new Vocab();
        words = new Vector<Word>();
        words.add(word3);
        words.add(word4);
        vocab2.setTranslation_table(words);
        vocab2.setTags("B");
        vocabs.add(vocab2);
        Vocab vocab3 = new Vocab();
        words = new Vector<Word>();
        words.add(word5);
        words.add(word6);
        vocab3.setTranslation_table(words);
        vocab3.setTags("C");
        vocabs.add(vocab3);
    }

    @Test
    public void testTranslationByLanguage(){
        assertEquals(vocabs.elementAt(2).getTranslationByLanguage("en"), "third");
        assertEquals(vocabs.elementAt(0).getTranslationByLanguage("fi"), "first_trans");
        assertEquals(vocabs.elementAt(1).getTranslationByLanguage("en"), "second");
    }


    @Test
    public void testTags(){
        assertEquals(vocabs.elementAt(2).getTags(), "C");
        assertEquals(vocabs.elementAt(0).getTags(), "A");
        assertEquals(vocabs.elementAt(1).getTags(), "B");
    }
    @Test
    public void testTranslationTable(){
        Vector<Word> words_ = vocabs.elementAt(0).getTranslation_table();
        assertEquals("first", words_.elementAt(0).getText());
        assertEquals("first_trans", words_.elementAt(1).getText());
    }

}

//
