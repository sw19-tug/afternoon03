package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class LANG_006_AdvancedTesting {

    private int vocabSize;

    @Before
    public void setupTest() throws Exception {
        VocabularManager vocabulary = VocabularManager.getInstance();
        Word word1 = new Word("first", "en");
        Word word2 = new Word("first_trans", "fi");
        Word word3 = new Word("second", "en");
        Word word4 = new Word("second_trans", "fi");
        Word word5 = new Word("third", "en");
        Word word6 = new Word("third_trans", "fi");
        vocabulary.addVocab(word1, word2, "tag");
        vocabulary.addVocab(word3, word4, "tags");
        vocabulary.addVocab(word5, word6, null);
        if (vocabulary.getVocabs().size() >= 1) {
            TestingManager testingManager = TestingManager.getInstance();
            testingManager.setTestingVocabs(vocabulary.getVocabs());
            vocabSize = 3;
        }

        ActivityTestRule<AdvancedTestingActivity> activityRule = new ActivityTestRule<>(AdvancedTestingActivity.class);
        Intent intent = new Intent();
        activityRule.launchActivity(intent);
    }

    @Test
    public void checkSelectTestInterface() throws Exception {



        onView(withId(R.id.tv_advanced_info)).check(matches(isDisplayed()));
//        onView(withId(R.id.lv_available_vocabs)).check(matches(isDisplayed()));
//        onView(withId(R.id.btn_start_test)).check(matches(isDisplayed()));
    }
}
