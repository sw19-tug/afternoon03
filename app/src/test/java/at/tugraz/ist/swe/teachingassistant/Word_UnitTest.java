package at.tugraz.ist.swe.teachingassistant;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class Word_UnitTest
{
    @Mock
    Context mockContext;

    public Word wordToTest = new Word("Hello", "EN");

    @Test
    public void testGetText()
    {
        String text = wordToTest.getText();
        assertEquals(text, "Hello");
    }

    @Test
    public void testSetName()
    {
        wordToTest.setText("New");
        assertEquals(wordToTest.getText(), "New");
        wordToTest.setText("Hello");
        assertEquals(wordToTest.getText(), "Hello");
    }

    @Test
    public void testGetLang()
    {
        String lang = wordToTest.getLang();
        assertEquals(lang, "en");
    }

    @Test
    public void testSetLang()
    {
        wordToTest.setLang("DE");
        assertEquals(wordToTest.getLang(), "DE");
        wordToTest.setLang("EN");
        assertEquals(wordToTest.getLang(), "EN");
    }

}