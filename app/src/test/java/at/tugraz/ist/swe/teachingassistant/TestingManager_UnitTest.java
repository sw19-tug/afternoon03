package at.tugraz.ist.swe.teachingassistant;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestingManager_UnitTest {


    TestingManager testingManager = TestingManager.getInstance();
    VocabularManager vocabulary = VocabularManager.getInstance();

    @Mock
    Context mockContext;

    @Before
    public void setUp() {
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
        testingManager.setTestingVocabs(vocabulary.getVocabs());
    }

    @Test
    public void testRandomTest(){
        testingManager.buildRandomTest();
        assertEquals(0, testingManager.getScore());
        assertEquals(0, testingManager.getCurrentPosition());
        assertEquals(3, testingManager.getActiveSize());
    }

}

//