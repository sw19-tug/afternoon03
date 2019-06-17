package at.tugraz.ist.swe.teachingassistant;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class LANG_010_word_search {

    @Rule
    public ActivityTestRule<LearningListActivity> activityRule = new ActivityTestRule<>(LearningListActivity.class);

    public ActivityTestRule<LearningListActivity> getActivityRule() {
        return activityRule;
    }

    @Before
    public void setUp() {
        VocabularManager vocabularManager = VocabularManager.getInstance();
        vocabularManager.clearVocabs();
        Word word1 = new Word("tt", "en");
        Word word2 = new Word("t", "fi");
        vocabularManager.addVocab(word1, word2, "a");
        word1 = new Word("at", "en");
        word2 = new Word("a", "fi");
        vocabularManager.addVocab(word1, word2, "b");
        word1 = new Word("z", "en");
        word2 = new Word("z", "fi");
        vocabularManager.addVocab(word1, word2, "c");
    }

    @Test
    public void filterByWordsTest() throws Exception {
        ArrayList<String> filteredVocabs = activityRule.getActivity().filterByWord("tt");
        Assert.assertEquals(1, filteredVocabs.size());
        Assert.assertEquals("tt", filteredVocabs.get(0));

        filteredVocabs = activityRule.getActivity().filterByWord("zzzzz");
        Assert.assertEquals(0, filteredVocabs.size());

        filteredVocabs = activityRule.getActivity().filterByWord("t");
        Assert.assertEquals(2, filteredVocabs.size());
    }
}
