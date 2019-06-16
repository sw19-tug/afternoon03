package at.tugraz.ist.swe.teachingassistant;


import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;

import static org.hamcrest.CoreMatchers.anything;

import static android.support.test.espresso.matcher.ViewMatchers.withId; //is needed..


import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Vector;

@RunWith(AndroidJUnit4.class)
public class LANG_017_DeleteEntry {
    @Rule
    public ActivityTestRule<VocabularyActivity> activity = new ActivityTestRule<VocabularyActivity>(VocabularyActivity.class, false, false);

    @Before
    public void setup() {
        VocabularManager vocabularManager = VocabularManager.getInstance();
        vocabularManager.clear();
        Word word1 = new Word("first", "en");
        Word word2 = new Word("first_trans", "fi");
        Word word3 = new Word("second", "en");
        Word word4 = new Word("second_trans", "fi");
        Word word5 = new Word("third", "en");
        Word word6 = new Word("third_trans", "fi");
        vocabularManager.addVocab(word1, word2, null);
        vocabularManager.addVocab(word3, word4, null);
        vocabularManager.addVocab(word5, word6, null);
        Intent intent = new Intent();
        activity.launchActivity(intent);

    }

    @Test
    public void testDeleteAll() {
        VocabularManager manager = VocabularManager.getInstance();
        manager.clear();
        Assert.assertEquals(manager.getVocabs().size(), 0);
    }


    @Test
    public void deleteAllInView()
    {

        VocabularManager manager = VocabularManager.getInstance();
        onData(anything()).inAdapterView(withId(R.id.first_language_list)).atPosition(0).onChildView(withId(R.id.delete_btn)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.first_language_list)).atPosition(0).onChildView(withId(R.id.delete_btn)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.first_language_list)).atPosition(0).onChildView(withId(R.id.delete_btn)).perform(click());
        Assert.assertEquals(0,manager.getVocabs().size());

    }

    @Test
    public void testDeleteOneWord() {
        VocabularManager manager = VocabularManager.getInstance();
        manager.deleteVocabByWord("second","second_trans");
        Vector<Vocab> vocabs = manager.getVocabs();

        Assert.assertEquals(vocabs.size(), 2);
        Assert.assertEquals(vocabs.elementAt(0).getTranslationByLanguage("en"), "first");
        Assert.assertEquals(vocabs.elementAt(0).getTranslationByLanguage("fi"), "first_trans");
        Assert.assertEquals(vocabs.elementAt(1).getTranslationByLanguage("en"), "third");
        Assert.assertEquals(vocabs.elementAt(1).getTranslationByLanguage("fi"), "third_trans");
    }
}
