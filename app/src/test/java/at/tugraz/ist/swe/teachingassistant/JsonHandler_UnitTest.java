package at.tugraz.ist.swe.teachingassistant;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Vector;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class JsonHandler_UnitTest
{
    @Mock
    Context mockContext;

    JsonHandler jsonHandler = new JsonHandler();
    String jsonString = "[{\"translation_table\":[{\"text\":\"Hello\",\"lang\":\"en\"},{\"text\":\"Hallo\",\"lang\":\"fi\"}],\"rating_\":1,\"tags\":\"\"}]";

    @Before
    public void setup()
    {
        VocabularManager vocabulary = VocabularManager.getInstance();
        vocabulary.clear();

        Word word1 = new Word("Hello", "en");
        Word word2 = new Word("Hallo", "fi");
        vocabulary.addVocab(word1,word2, "");
    }

    @Test
    public void vocabularToJsonStringTest()
    {
       String string = jsonHandler.vocabularToJsonString();
       assertEquals(string,jsonString);
    }

    @Test
    public void vocabularyFromJsonString()
    {
        Vector<Vocab> vocabs = jsonHandler.vocabularyFromJsonString(jsonString);
        VocabularManager vocabulary = VocabularManager.getInstance();
        for (int i = 0; i < vocabs.size(); i++)
        {
            assertEquals(vocabs.elementAt(i).getTags(), vocabulary.getVocabs().elementAt(i).getTags());
            assertEquals(vocabs.elementAt(i).getRating(), vocabulary.getVocabs().elementAt(i).getRating());
        }

    }

    @After
    public void clean()
    {
        VocabularManager vocabulary = VocabularManager.getInstance();
        vocabulary.clear();
    }
}