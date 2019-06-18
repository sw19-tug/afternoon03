package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertNotSame;

@RunWith(AndroidJUnit4.class)
public class LearningListUI {
    @Rule
    public ActivityTestRule<LearningListActivity> mActivityRule = new ActivityTestRule<LearningListActivity>(LearningListActivity.class,false,false);

    @Before
    public void setUp() {
        VocabularManager vocabulary = VocabularManager.getInstance();
        vocabulary.clearVocabs();
        Word word1 = new Word("t", "en");
        Word word2 = new Word("t", "fi");
        Word word3 = new Word("a", "en");
        Word word4 = new Word("a", "fi");
        Word word5 = new Word("z", "en");
        Word word6 = new Word("z", "fi");
        vocabulary.addVocab(word1, word2,"a");
        vocabulary.addVocab(word3, word4,"b");
        vocabulary.addVocab(word5, word6,"c");

        Intent intent = new Intent();
        intent.putExtra("current_lang", "en");
        intent.putExtra("position", 0);

        mActivityRule.launchActivity(intent);
    }
    @Test
    public void testFilterSpinnerReset()
    {

        onView(allOf(withId(R.id.spinner), withSpinnerText("Filter"))).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());
        onView(withId(R.id.btn_alphabetical)).perform(click());
        onView(allOf(withId(R.id.spinner), withSpinnerText("Filter"))).perform(click());

    }

    @Test
    public void testFilterSpinner()
    {
        onView(withId(R.id.spinner)).check(matches(isDisplayed()));
        onView(withId(R.id.spinner)).check(matches((isClickable())));
    }

    @Test
    public void testSortSpinner()
    {
        onView(withId(R.id.spinnerSort)).check(matches(isDisplayed()));
        onView(withId(R.id.spinnerSort)).check(matches((isClickable())));
    }

    @Test
    public void testChangeLanguage()
    {
        onView(withId(R.id.btn_changeLanguage)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_changeLanguage)).check(matches((isClickable())));
        String language = withId(R.id.languageTitle).toString();
        onView(withId(R.id.btn_changeLanguage)).perform(click());
        String languageAfterClick = withId(R.id.languageTitle).toString();
        assertNotSame(language, languageAfterClick);
    }

    @Test
    public void testListHasItems() {
        ListView listview = (ListView) mActivityRule.getActivity().findViewById(R.id.vocabList);
        assertThat(listview.getCount(), is(3));
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
        ArrayList<String> sortedVocabs = mActivityRule.getActivity().sortByAlphabetical();
        Assert.assertEquals("a", sortedVocabs.get(0));
        Assert.assertEquals("t", sortedVocabs.get(1));
        Assert.assertEquals("z", sortedVocabs.get(2));

        sortedVocabs = mActivityRule.getActivity().sortByAlphabetical();
        Assert.assertEquals("z", sortedVocabs.get(0));
        Assert.assertEquals("t", sortedVocabs.get(1));
        Assert.assertEquals("a", sortedVocabs.get(2));

        sortedVocabs = mActivityRule.getActivity().sortByAlphabetical();
        Assert.assertEquals("t", sortedVocabs.get(0));
        Assert.assertEquals("a", sortedVocabs.get(1));
        Assert.assertEquals("z", sortedVocabs.get(2));

    }
    @Test
    public void sortTagsTest() throws Exception {
        ArrayList<String> sortedVocabs = mActivityRule.getActivity().sortByTags();
        Assert.assertEquals( "t", sortedVocabs.get(0));
        Assert.assertEquals( "a", sortedVocabs.get(1));
        Assert.assertEquals( "z", sortedVocabs.get(2));

        sortedVocabs = mActivityRule.getActivity().sortByTags();
        Assert.assertEquals( "z", sortedVocabs.get(0));
        Assert.assertEquals("a", sortedVocabs.get(1));
        Assert.assertEquals( "t", sortedVocabs.get(2));

        sortedVocabs = mActivityRule.getActivity().sortByTags();
        Assert.assertEquals("t", sortedVocabs.get(0));
        Assert.assertEquals("a", sortedVocabs.get(1));
        Assert.assertEquals("z", sortedVocabs.get(2));
    }

    @Test
    public void filterTagsTest() throws Exception {
        ArrayList<String> filterVocabs = mActivityRule.getActivity().filterByTags("b");
        Assert.assertEquals(1, filterVocabs.size());
        Assert.assertEquals("a", filterVocabs.get(0));
        filterVocabs = mActivityRule.getActivity().filterByTags("k");
        Assert.assertEquals(0, filterVocabs.size());
        filterVocabs = mActivityRule.getActivity().filterByTags("");
        Assert.assertEquals(3, filterVocabs.size());
        Assert.assertEquals("t", filterVocabs.get(0));
        Assert.assertEquals("a", filterVocabs.get(1));
        Assert.assertEquals("z", filterVocabs.get(2));
    }

}