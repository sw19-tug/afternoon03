package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.*;

import static org.hamcrest.Matchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class LANG_005_Testing {
    @Rule
    public ActivityTestRule<TestingActivity> activityRule = new ActivityTestRule<>(TestingActivity.class);

    public ActivityTestRule<TestingActivity> getActivityRule()
    {
        return activityRule;
    }

    @Test
    public void setupTest() throws Exception {
        VocabularManager vocabulary = VocabularManager.getInstance();
        Word word1 = new Word("first", "en");
        Word word2 = new Word("first_trans", "fi");
        Word word3 = new Word("second", "en");
        Word word4 = new Word("second_trans", "fi");
        Word word5 = new Word("third", "en");
        Word word6 = new Word("third_trans", "fi");
        vocabulary.addVocab(word1, word2);
        vocabulary.addVocab(word3, word4);
        vocabulary.addVocab(word5, word6);

        Intent intent = new Intent();
        activityRule.launchActivity(intent);

    }

    @Test
    public void checkInterfaceTest() throws Exception {
        onView(withId(R.id.tv_progress)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_fraction_progress)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_providedLanguage)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_questionLanguage)).check(matches(isDisplayed()));
        onView(withId(R.id.et_requestedWord)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_testing_next)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_testing_next)).check(matches((isClickable())));

    }

    @Test
    public void correctResponseTest() throws Exception {

    }

    @Test
    public void incorrectResponseTest() throws Exception {

    }

    @Test
    public void endingTest() throws Exception {

    }


}
