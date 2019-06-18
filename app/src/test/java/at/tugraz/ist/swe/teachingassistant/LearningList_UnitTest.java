package at.tugraz.ist.swe.teachingassistant;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class LearningList_UnitTest {

    @Before
    public void setUp()
    {
        VocabularManager vocabularManager = VocabularManager.getInstance();
        vocabularManager.clearVocabs();
        Word word1 = new Word("t", "en");
        Word word2 = new Word("t", "fi");
        vocabularManager.addVocab(word1, word2, "a");
        word1 = new Word("a", "en");
        word2 = new Word("a", "fi");
        vocabularManager.addVocab(word1, word2, "b");
        word1 = new Word("z", "en");
        word2 = new Word("z", "fi");
        vocabularManager.addVocab(word1, word2, "c");
    }

    @After
    public void tearDown()
    {
        VocabularManager vocabularManager = VocabularManager.getInstance();
        vocabularManager.clearVocabs();
    }

    @Test
    public void sortAlphabeticalTest() throws Exception
    {
        ArrayList<String> sortedVocabs = activityRule.getActivity().sortByAlphabetical();
        Assert.assertEquals("a", sortedVocabs.get(0));
        Assert.assertEquals("t", sortedVocabs.get(1));
        Assert.assertEquals("z", sortedVocabs.get(2));

        sortedVocabs = activityRule.getActivity().sortByAlphabetical();
        Assert.assertEquals("z", sortedVocabs.get(0));
        Assert.assertEquals("t", sortedVocabs.get(1));
        Assert.assertEquals("a", sortedVocabs.get(2));

        sortedVocabs = activityRule.getActivity().sortByAlphabetical();
        Assert.assertEquals("t", sortedVocabs.get(0));
        Assert.assertEquals("a", sortedVocabs.get(1));
        Assert.assertEquals("z", sortedVocabs.get(2));

    }
    @Test
    public void sortTagsTest() throws Exception {
        ArrayList<String> sortedVocabs = activityRule.getActivity().sortByTags();
        Assert.assertEquals( "t", sortedVocabs.get(0));
        Assert.assertEquals( "a", sortedVocabs.get(1));
        Assert.assertEquals( "z", sortedVocabs.get(2));

        sortedVocabs = activityRule.getActivity().sortByTags();
        Assert.assertEquals( "z", sortedVocabs.get(0));
        Assert.assertEquals("a", sortedVocabs.get(1));
        Assert.assertEquals( "t", sortedVocabs.get(2));

        sortedVocabs = activityRule.getActivity().sortByTags();
        Assert.assertEquals("t", sortedVocabs.get(0));
        Assert.assertEquals("a", sortedVocabs.get(1));
        Assert.assertEquals("z", sortedVocabs.get(2));
    }

    @Test
    public void filterTagsTest() throws Exception {
        ArrayList<String> filterVocabs = activityRule.getActivity().filterByTags("b");
        Assert.assertEquals(1, filterVocabs.size());
        Assert.assertEquals("a", filterVocabs.get(0));
        filterVocabs = activityRule.getActivity().filterByTags("k");
        Assert.assertEquals(0, filterVocabs.size());
        filterVocabs = activityRule.getActivity().filterByTags("");
        Assert.assertEquals(3, filterVocabs.size());
        Assert.assertEquals("t", filterVocabs.get(0));
        Assert.assertEquals("a", filterVocabs.get(1));
        Assert.assertEquals("z", filterVocabs.get(2));
    }
}