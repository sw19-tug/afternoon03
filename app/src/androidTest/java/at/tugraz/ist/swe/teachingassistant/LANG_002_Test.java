package at.tugraz.ist.swe.teachingassistant;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LANG_002_Test {

public ListView listview;
View child0;

    @Rule
    public ActivityTestRule<LearningListActivity> activityRule = new ActivityTestRule<>(LearningListActivity.class);

    public ActivityTestRule<LearningListActivity> getActivityRule()
    {
        return activityRule;
    }

    @Before
    public void setUpList() {
        VocabularManager vocabulary = VocabularManager.getInstance();
        Word word1 = new Word("en_test", "en");
        Word word2 = new Word("fi_test", "fi");
        Word word3 = new Word("en_test2","en");
        Word word4 = new Word("fi_test2","fi");
        vocabulary.addVocab(word1, word2);
        vocabulary.addVocab(word3, word4);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("at.tugraz.ist.swe.teachingassistant", appContext.getPackageName());
    }

    @Test
    public void testLearningInterfaceVisible(){
        onView(withId(R.id.vocabList)).check(matches(isDisplayed()));
        onView(withId(R.id.learningTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_changeLanguage)).check(matches(isDisplayed()));
        onView(withId(R.id.languageTitle)).check(matches(isDisplayed()));
    }

    @Test
    public void testListHasItems() {

        ListView listview = (ListView) activityRule.getActivity().findViewById(R.id.vocabList);
        assertThat(listview.getCount(), is(1));
    }


    @Test
    public void testChangeLanguageButtonList(){
        String language;
        TextView languageTextView = (TextView) activityRule.getActivity().findViewById(R.id.languageTitle);
        language = ((TextView) languageTextView).getText().toString();
        assertEquals(language, "English");

        onView(withId(R.id.btn_changeLanguage)).perform(ViewActions.click());
        language = ((TextView) languageTextView).getText().toString();
        assertEquals(language, "Finnish");
    }

    @Test
    public void testClickListItem() {
        int mActivePosition = 0;
        listview.performItemClick(
                listview.getAdapter().getView(mActivePosition, null, null),
                mActivePosition,
                listview.getAdapter().getItemId(mActivePosition));
        onView(withId(R.id.btn_changeLanguageInterface)).check(matches(isDisplayed()));
    }
}
