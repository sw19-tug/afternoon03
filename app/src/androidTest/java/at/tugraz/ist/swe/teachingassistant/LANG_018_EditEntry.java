package at.tugraz.ist.swe.teachingassistant;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Vector;

@RunWith(AndroidJUnit4.class)
public class LANG_018_EditEntry
{
    @Rule
    public ActivityTestRule<LearningListActivity> learingListActivity = new ActivityTestRule<LearningListActivity>(LearningListActivity.class, false, false);

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
    }

    @Test
    public void EditVocab()
    {
        VocabularManager manager = VocabularManager.getInstance();
        Word word1 = new Word("first_changed", "en");
        Word word2 = new Word("trans_changed", "fi");
        manager.editVocabByWord("first","first_trans",word1,word2,null);
        Vector<Vocab> vocabs = manager.getVocabs();
        Assert.assertEquals(vocabs.elementAt(vocabs.size()-1).getTranslationByLanguage("en"), "first_changed");
        Assert.assertEquals(vocabs.elementAt(vocabs.size()-1).getTranslationByLanguage("fi"), "trans_changed");

    }
    @Test
    public void EditVocabNotInList()
    {
        VocabularManager manager = VocabularManager.getInstance();
        Word word1 = new Word("first_changed", "en");
        Word word2 = new Word("trans_changed", "fi");
        manager.editVocabByWord("NOT_IN","NOT_IN",word1,word2,"null");
        Vector<Vocab> vocabs = manager.getVocabs();
        Assert.assertEquals(vocabs.elementAt(0).getTranslationByLanguage("en"), "first");
        Assert.assertEquals(vocabs.elementAt(0).getTranslationByLanguage("fi"), "first_trans");
    }
}
